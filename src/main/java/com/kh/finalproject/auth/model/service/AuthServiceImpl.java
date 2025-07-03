package com.kh.finalproject.auth.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kh.finalproject.auth.model.dao.AuthMapper;
import com.kh.finalproject.auth.model.dto.EmailDTO;
import com.kh.finalproject.auth.model.dto.FindDTO;
import com.kh.finalproject.auth.model.dto.FindResponseDTO;
import com.kh.finalproject.auth.model.dto.LoginDTO;
import com.kh.finalproject.auth.model.dto.LoginResponseDTO;
import com.kh.finalproject.auth.util.emailUtil.EmailUtil;
import com.kh.finalproject.auth.vo.EmailCodeVO;
import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.exception.exceptions.DuplicateUserEmailException;
import com.kh.finalproject.exception.exceptions.EmailCodeException;
import com.kh.finalproject.exception.exceptions.InvaildFindIdException;
import com.kh.finalproject.exception.exceptions.InvaildFindPwException;
import com.kh.finalproject.exception.exceptions.LoginFailedException;
import com.kh.finalproject.token.model.service.TokenService;
import com.kh.finalproject.user.model.dao.UserMapper;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final AuthMapper authMapper;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	private final EmailUtil emailUtil;
	
	@Override
	public LoginResponseDTO login(LoginDTO loginDTO) {
	    String userId = loginDTO.getUserId();
	    String password = loginDTO.getPassword();

	    Authentication authentication = null;
	    try {
	        authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(userId, password)  
	        );
	    } catch (AuthenticationException e) {
	        throw new LoginFailedException("아이디 또는 비밀번호가 틀립니다.");
	    }
		
		NwUserDetails nwUserDetails = (NwUserDetails) authentication.getPrincipal();
		
		String accessToken = tokenService.generateAccessToken(userId);
		String refreshToken = tokenService.generateRefreshToken(nwUserDetails.getUserNo(),userId);
		
	
		return LoginResponseDTO.builder()
							   .userId(nwUserDetails.getUserId())
							   .realName(nwUserDetails.getRealName())
							   .nickName(nwUserDetails.getNickName())
							   .email(nwUserDetails.getEmail())
							   .accessToken(accessToken)
							   .refreshToekn(refreshToken)
							   .build();
	}


	@Override
	public FindResponseDTO findId(FindDTO findIdDTO) {
		
		FindResponseDTO selectByfindId =  authMapper.selectByfindId(findIdDTO);
		if(selectByfindId == null) {
			throw new InvaildFindIdException("해당 정보와 일치하는 아이디가 없습니다. 다시 확인해 주세요.");
		}
		
		String findId = selectByfindId.getUserId();
		
		SimpleMailMessage messageId = new SimpleMailMessage();
		messageId.setTo(selectByfindId.getEmail());
		messageId.setSubject("아이디 안내");
		messageId.setText("가입하신 아이디는 [" + findId + "]입니다 ");
		
		emailUtil.sendMailId(messageId);
		return selectByfindId;
	}

	@Override
	public FindResponseDTO findPw(FindDTO findDTO) {
		
		FindResponseDTO selsectByPw = authMapper.selectByfindPw(findDTO);
		if(selsectByPw == null) {
			throw new InvaildFindPwException("입력하신 정보와 일치하는 계정이 없습니다. 다시 확인해 주세요.");
		}
		String tempPassword = UUID.randomUUID().toString().replace("-", "").substring(0,10);
		String encodedTempPw = passwordEncoder.encode(tempPassword);
		
		 Map<String, String> tempInfo = new HashMap<>();
		 tempInfo.put("userId", findDTO.getUserId());
		 tempInfo.put("encodedTempPw", encodedTempPw);
		 
		 authMapper.tempPassword(tempInfo);

		 FindResponseDTO modifyInfo = authMapper.selectEmail(findDTO);
		 
		  SimpleMailMessage messagePw = new SimpleMailMessage();
		  messagePw.setTo(modifyInfo.getEmail());
		  messagePw.setSubject("임시 비밀번호 안내");
		  messagePw.setText("임시 비밀번호는 ["+ tempPassword + "]\n로그인 후 반드시 변경해주세요");
		 
		  emailUtil.sendMailPw(messagePw);
		 return modifyInfo;
	}


	@Override
	public int sendEmailCode(String email) {
		
		
		
		String code = String.format("%06d", new Random().nextInt(999999));
		
		EmailCodeVO emailCodeInfo = new EmailCodeVO();
		emailCodeInfo.setCode(code);
		emailCodeInfo.setEmail(email);
		
		if(userMapper.existsByUserEmail(email) > 0 ) {
			throw new DuplicateUserEmailException("이미 사용중인 이메일 입니다");
		}
		
			
		int response = authMapper.sendEmailCode(emailCodeInfo);
		
		// 이메일 보내기 시작
		
		SimpleMailMessage message = new SimpleMailMessage();
		System.out.println(email);
		message.setTo(email);
		message.setSubject("이메일 인증번호 안내");
		message.setText("인증번호는 ["+ code +"] 입니다. ");
		
		emailUtil.sendMail(message);
		
		return response;
	}

	@Override
	public EmailDTO verifyCode(EmailDTO emailDTO) {
		
		EmailCodeVO emailCodeInfoVO = authMapper.verifyCode(emailDTO);
		
		if(emailCodeInfoVO == null) {
			throw new EmailCodeException("이메일 또는 인증번호가 잘못되었습니다.");
		}
		
		authMapper.deleteEmailCode(emailDTO);
		
		return emailDTO;
	}
	
	
	@Override
	public NwUserDetails getUserDetails() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    NwUserDetails user = (NwUserDetails) authentication.getPrincipal();
	    return user;
	}

}
