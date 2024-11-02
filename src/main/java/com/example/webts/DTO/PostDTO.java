package com.example.webts.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	
	@NotNull(message="제목에 null은 불가능합니다.")
	@NotBlank(message="제목을 넣어주세요.")
	private String title;
	
	@NotNull(message="내용에 null은 불가능합니다.")
	@NotBlank(message="내용을 넣어주세요.")
	private String content;
}
