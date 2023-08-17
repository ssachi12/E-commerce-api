package org.jsp.api.exception;

public class CrNotFoundException extends RuntimeException {

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return "Invalid credentials";
}
}
