package com.jaime.platform.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Code not found")
public class CodeNotFoundException extends RuntimeException{
}
