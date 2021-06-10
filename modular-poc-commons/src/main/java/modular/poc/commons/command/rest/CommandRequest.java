package modular.poc.commons.command.rest;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.io.Serializable;

@Data
public class CommandRequest implements Serializable {
    private JsonNode  command;
    private String commandClass;
}
