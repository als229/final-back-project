package com.kh.finalproject.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
public class UserResponseDTO {

	
	@Size(min = 4, max = 20, message = "아이디 길이는 4~20자 사이여야 합니다.")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,19}$", message = "아이디는 영문자로 시작하며, 영문자와 숫자로 이루어진 4~20자여야 합니다.")
	@NotBlank(message = "아이디는 비어있거나 공백이 포함될 수 없습니다.") 
	private String userId;
	
	@NotNull(message = "이메일 종류는 비어 있을 수 없습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "잘못된 이메일 형식입니다.")
	private String email;
	@Size(min = 2, max = 30, message = "닉네임 길이는 한글 2~5자, 영어/숫자 2~30자 사이여야 합니다.")
	@Pattern(regexp = "^([a-zA-Z0-9]{2,30}|[\\uAC00-\\uD7A3]{2,5})$", message = "닉네임은 영문/숫자 2~30자 또는 한글 2~5자여야 합니다.")
	@NotBlank(message = "닉네임은 비어있거나 공백이 포함될 수 없습니다.")
	private String nickName;
	@Size(min = 2, max = 30, message = "이름 길이는 한글 2~5자, 영어 2~30자 사이여야 합니다.")
	@Pattern(regexp = "^([a-zA-Z]{2,30}|[\\uAC00-\\uD7A3]{2,5})$", message = "잘못된 이름 형식입니다.")
	@NotBlank(message = "이름은 비어있거나 공백이 포함될 수 없습니다.")
	private String realName;
	
	private String fileUrl;
}
