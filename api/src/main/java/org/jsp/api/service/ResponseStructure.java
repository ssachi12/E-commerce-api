package org.jsp.api.service;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String message;
	private int code;
	private T body;

}
