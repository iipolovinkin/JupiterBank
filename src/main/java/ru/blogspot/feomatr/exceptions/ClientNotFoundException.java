package ru.blogspot.feomatr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author iipolovinkin
 * @since 29.01.15.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Client")
public class ClientNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 9063553783333691967L;

    public ClientNotFoundException(Long id) {
    }
}
