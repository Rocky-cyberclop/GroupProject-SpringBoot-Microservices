package com.twoteethreeeight.userservice.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileDto {

	private String email;
	private String FullName;
	private String Phone;
}
