package net.tychecash.explorer.common.model.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class ErrorResponse {

	@JsonProperty("error_cd")
	private Integer errorCode;

	@JsonProperty("message")
	private String message;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(Integer errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}