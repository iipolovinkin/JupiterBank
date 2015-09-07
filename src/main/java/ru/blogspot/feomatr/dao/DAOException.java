package ru.blogspot.feomatr.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO layer exception.
 *
 * @author iipolovinkin
 * @since 07.09.2015
 */
public class DAOException extends Exception {
    private static final Logger log = LoggerFactory.getLogger(DAOException.class);
    private static final long serialVersionUID = -2714132903218340178L;

    public DAOException() {
        super();
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }


}
