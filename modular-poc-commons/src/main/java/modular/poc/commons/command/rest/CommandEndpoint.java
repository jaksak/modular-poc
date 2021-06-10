package modular.poc.commons.command.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import modular.poc.commons.command.*;
import modular.poc.commons.command.exception.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommandEndpoint {

    private final CommandHandlerProvider commandHandlerProvider;
    private final ObjectMapper objectMapper;

    @PostMapping("command")
    public <T> CommandResult<?> call(@RequestBody CommandRequest request) throws ClassNotFoundException, JsonProcessingException {
        CommandResult.CommandResultBuilder<T> result = CommandResult.builder();
        Class<? extends Command<T>> commandClass = (Class<? extends Command<T>>) Class.forName(request.getCommandClass());
        Command<T> command = objectMapper.treeToValue(request.getCommand(), commandClass);
        try {
            var resultBody = commandHandlerProvider.provide(command).handle(command);
            result.result(resultBody);
        } catch (ModuleException e) {
            result.errorCode(e.getErrorCode())
                    .message(e.getMessage());
        } catch (Exception e) {
            result.errorCode(ExceptionCodes.UNEXPECTED_EXCEPTION)
                    .message(e.getMessage());
        }
        return result.build();
    }
}
