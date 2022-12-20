package com.example.project.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegMemberForm {
	
	@Size(min=3)
	@NotEmpty(message = "아이디는 필수항목입니다.")
	private String id;
	
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String pw;
	
	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String pw2;
	
	@NotEmpty(message = "이름은 필수항목입니다.")
	private String name;
	
	@NotEmpty(message = "생년월일은 필수항목입니다.")
	private String birth;
	
	@NotEmpty(message = "전화번호는 필수항목입니다.")
	private String tell;
	
	@NotEmpty(message = "이메일은 필수항목입니다.")
	@Email
	private String email;
}
