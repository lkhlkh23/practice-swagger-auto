package com.practice.swagger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.swagger.config.SwaggerHelper;
import com.practice.swagger.controller.dto.Person;
import com.practice.swagger.type.ErrorCode;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v1")
public class PersonController {

	@SwaggerHelper(targets = {ErrorCode._100, ErrorCode._101})
	@Operation(summary = "회원 목록 요청", description = "회원 목록 응답", tags = { "Person Controller" })
	@GetMapping("/people")
	public List<Person> getPeople() {
		return List.of(new Person("dob", 35), new Person("lee", 35));
	}

	@Operation(summary = "회원 단일 요청", description = "회원 단일 응답", tags = { "Person Controller" })
	@GetMapping("/person")
	public Person getPerson() {
		return new Person("dob", 35);
	}

	@SwaggerHelper(targets = ErrorCode._101)
	@Operation(summary = "회원 단일 요청 (이름)", description = "회원 단일 응답 (이름)", tags = { "Person Controller" })
	@GetMapping("/person/{name}")
	public Person getPersonByName(@PathVariable final String name) {
		return new Person("dob", 35);
	}

}
