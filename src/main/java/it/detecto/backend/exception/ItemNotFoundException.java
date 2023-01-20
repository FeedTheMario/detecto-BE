package it.detecto.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NO_CONTENT, reason="No such item")  // 404 to do 2xx no content
public class ItemNotFoundException extends RuntimeException {}