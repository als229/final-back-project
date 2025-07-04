package com.kh.finalproject.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindResponseDTO {

	@Size(min = 4, max = 20, message = "아이디 길이는 4~20자 사이여야 합니다.")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,19}$", message = "아이디는 영문자로 시작하며, 영문자와 숫자로 이루어진 4~20자여야 합니다.")
	@NotBlank(message = "아이디는 비어있거나 공백이 포함될 수 없습니다.") 
	private String userId;
	@Size(min = 8, max = 30, message = "비밀번호 길이는 8~30자 사이여야 합니다.")
	@Pattern( regexp = "^(?=.*[a-zA-Z])(?=.*\\d)\\S{6,30}$", message = "영문자와 숫자를 포함한 6~30자의 비밀번호여야 하며, 공백은 포함할 수 없습니다.")
	@NotBlank(message = "비밀번호는 비어있거나 공백이 포함될 수 없습니다.")
	private String password;
	private String email;
}
