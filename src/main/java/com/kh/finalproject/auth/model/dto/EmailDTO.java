package com.kh.finalproject.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

	@NotNull(message = "이메일 종류는 비어 있을 수 없습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "잘못된 이메일 형식입니다.")
	private String email;
	@Size(min = 6, max = 6, message = "인증코드는 6자리여야 합니다.")
	@Pattern(regexp = "\\d{6}", message = "인증코드는 숫자 6자리여야 합니다.")
	@NotBlank(message = "인증코드는 비어있을 수 없습니다.")
	private String code;
	
	
}
