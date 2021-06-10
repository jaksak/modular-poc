package modular.poc.commons.command.exception;

import lombok.Getter;

@Getter
public class ModuleException extends RuntimeException {

    private final int errorCode;
    private Object errorModel;

    public ModuleException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
