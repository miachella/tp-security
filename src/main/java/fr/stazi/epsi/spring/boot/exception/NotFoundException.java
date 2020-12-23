package fr.stazi.epsi.spring.boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "You're trying to access an unexisting resource.")
public class NotFoundException extends Exception {

	private static final long serialVersionUID = -7206328033039781971L;

}
