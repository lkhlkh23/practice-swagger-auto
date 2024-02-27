package com.practice.swagger.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
	_100(404, -100, "Data Not Found"),
	_101(400, -101, "Bad Request"),
	_102(500, -102, "Internal Server Error");

	private int statusCode;
	private int errorCode;
	private String message;
}
