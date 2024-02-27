package com.practice.swagger.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.practice.swagger.type.ErrorCode;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerHelper {

	ErrorCode[] targets();


}
