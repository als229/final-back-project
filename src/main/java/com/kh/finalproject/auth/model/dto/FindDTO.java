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
public class FindDTO {

	@Size(min = 2, max = 30, message = "이름 길이는 한글 2~5자, 영어 2~30자 사이여야 합니다.")
	@Pattern(regexp = "^([a-zA-Z]{2,30}|[\\uAC00-\\uD7A3]{2,5})$", message = "잘못된 이름 형식입니다.")
	@NotBlank(message = "이름은 비어있거나 공백이 포함될 수 없습니다.")
	private String realName;
	@NotNull(message = "이메일 종류는 비어 있을 수 없습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "잘못된 이메일 형식입니다.")
	private String email;
	
	private String userId;
	
}
