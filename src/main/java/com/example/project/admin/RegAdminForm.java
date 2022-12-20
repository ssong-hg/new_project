package com.example.project.admin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegAdminForm {
	@Size(min=3)
	@NotEmpty(message = "아이디는 필수항목입니다.")
	private String id;
	
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String pw;
	
	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String pw2;
	
	@NotEmpty(message = "이름은 필수항목입니다.")
	private String name;
	
	@NotEmpty(message = "전화번호는 필수항목입니다.")
	private String tell;
	
}
