package io.axasoft.mayacomposite.exception;

public class ServiceException extends RuntimeException {
    private final Object[] args;

    public ServiceException(String messageKey, Object... args) {
        super(messageKey);
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
