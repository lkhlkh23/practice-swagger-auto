package com.practice.swagger.config;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import com.practice.swagger.type.ErrorCode;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@OpenAPIDefinition(
	info = @Info(title = "채팅서비스 API 명세서",
		description = "헥사고날 아키텍처 기반 채팅 서비스 API 명세서",
		version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi openApi() {
		final String[] paths = {"/v1/**"};

		return GroupedOpenApi.builder()
							 .group("Practice API v1")
							 .pathsToMatch(paths)
							 .addOperationCustomizer(customize())
							 .build();

	}

	@Bean
	public OperationCustomizer customize() {
		return new CustomOperationCustomizer();
	}
	private static class CustomOperationCustomizer implements OperationCustomizer {

		@Override
		public Operation customize(Operation operation, HandlerMethod handlerMethod) {
			final SwaggerHelper swaggerHelper = handlerMethod.getMethodAnnotation(SwaggerHelper.class);
			if(swaggerHelper != null) {
				final ApiResponses apiResponses = operation.getResponses();
				final ErrorCode[] errorCodes = swaggerHelper.targets();
				for (final ErrorCode errorCode : errorCodes) {
					final ApiResponse item = new ApiResponse();
					item.setDescription(errorCode.getMessage());
					apiResponses.addApiResponse(String.valueOf(errorCode.getStatusCode()), item);
				}

			}
			return operation;
		}
	}


}