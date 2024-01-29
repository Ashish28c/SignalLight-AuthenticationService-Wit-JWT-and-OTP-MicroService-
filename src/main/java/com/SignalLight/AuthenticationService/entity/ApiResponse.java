package com.SignalLight.AuthenticationService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
	private int status;
	private String msg;
	private Object data;
}
