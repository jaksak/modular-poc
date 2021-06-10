package modular.poc.commons.command;

import lombok.RequiredArgsConstructor;
import modular.poc.commons.command.exception.*;
import modular.poc.commons.command.handler.CommandHandler;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.*;

import java.lang.reflect.ParameterizedType;
import java.util.*;

@RequiredArgsConstructor
public class CommandHandlerProvider {

    private final CommandLocation commandLocation;
    private final Map<String, modular.poc.commons.command.handler.CommandHandler<?, ?>> handlerMap = new HashMap<>();

    public <RESULT, CMD extends Command<RESULT>> CommandHandler<RESULT, CMD> provide(CMD command) {
        var commandIdentifier = command.getClass().getName();
        CommandHandler<RESULT, CMD> commandHandler = (CommandHandler<RESULT, CMD>) handlerMap.get(commandIdentifier);
        if (commandHandler == null) {
            throw new ModuleException(ExceptionCodes.UNKNOWN_COMMAND, "unknow command " + commandIdentifier);
        } else {
            return commandHandler;
        }
    }

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBeansWithAnnotation(LocatedCommand.class)
                .forEach((key, value) -> addToHandlerMap(value));
    }

    private void addToHandlerMap(Object potentiallyHandler) {
        var currentClass = potentiallyHandler.getClass();
        var location = currentClass.getAnnotation(LocatedCommand.class).location();
        if (commandLocation.equals(location) && potentiallyHandler instanceof modular.poc.commons.command.handler.CommandHandler<?, ?> handler) {
            // a.module1.A1Command
            var commandIdentifier = ((ParameterizedType) currentClass.getGenericInterfaces()[0]).getActualTypeArguments()[1].getTypeName();
            handlerMap.put(commandIdentifier, handler);
        }
    }
}
