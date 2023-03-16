package osa.com.Spring.project.exception;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String s) {
        super(s);
    }
}

