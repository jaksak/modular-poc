package modular.poc.commons.command.rest;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
public class CommandResult<RESULT> implements Serializable {
    private RESULT result;
    private Integer errorCode;
    private String message;
}
