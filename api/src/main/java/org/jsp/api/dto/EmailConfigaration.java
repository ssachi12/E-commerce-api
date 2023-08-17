package org.jsp.api.dto;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmailConfigaration {
private Map<String, String>user;
private String subject;
private String text;
private String email;
}
