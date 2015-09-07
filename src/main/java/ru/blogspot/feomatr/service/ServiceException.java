package ru.blogspot.feomatr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service layer exception.
 *
 * @author iipolovinkin
 * @since 07.09.2015
 */
public class ServiceException extends Exception {
    private static final Logger log = LoggerFactory.getLogger(ServiceException.class);
    private static final long serialVersionUID = -149048498117792908L;

    public ServiceException() {
        super();
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
