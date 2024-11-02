package com.example.webts.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@NotNull(message ="username은 null이면 안됩니다")
	@NotBlank(message = "username을 입력해주세요")
	@Size(min=3, max=15, message="username의 길이는 3~15자까지 가능합니다")
	private String username;
	
	@NotNull(message = "password는 null이면 안됩니다")
	@NotBlank(message = "password를 입력해주세요")
	@Size(min=4, max=50, message="password는 4~50자 입력해주세요")
	private String password;
	
	@NotNull(message = "eamile은 null이면 안됩니다")
	@NotBlank(message = "eamile은 입력해주세요")
	@Email(message="형식에 맞게 입력해주세요")
	private String email;
	
	@NotNull(message = "성별을 눌러주세요")
	private String gender;
}
