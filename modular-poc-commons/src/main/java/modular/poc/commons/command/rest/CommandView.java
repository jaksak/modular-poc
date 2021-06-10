package modular.poc.commons.command.rest;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import modular.poc.commons.command.Command;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CommandView implements Serializable {
    private Command<?> command;
    private String commandClass;
}
