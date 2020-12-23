package fr.stazi.epsi.spring.boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "A resource with the same id already exists.")
public class AlreadyExistsException extends Exception {

	private static final long serialVersionUID = 713184647336095003L;

}
