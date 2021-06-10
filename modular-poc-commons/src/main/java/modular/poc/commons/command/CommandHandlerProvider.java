package modular.poc.commons.command;

import lombok.RequiredArgsConstructor;
import modular.poc.commons.command.exception.*;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.*;

import java.lang.reflect.ParameterizedType;
import java.util.*;

@RequiredArgsConstructor
public class CommandHandlerProvider {

    private final CommandLocation commandLocation;
    private final Map<String, CommandHandler<?, ?>> handlerMap = new HashMap<>();

    @SuppressWarnings("unchecked")
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
        event.getApplicationContext()
                .getBeansOfType(CommandHandler.class)
                .forEach((key, value) -> addToHandlerMap(value));
    }

    private void addToHandlerMap(Object potentiallyHandler) {
        var currentClass = potentiallyHandler.getClass();
        if (potentiallyHandler instanceof CommandHandler<?, ?> handler) {
            var location = handler.getCommandLocation();
            if (commandLocation.equals(location)) {
                var commandIdentifier = ((ParameterizedType) currentClass.getGenericInterfaces()[0]).getActualTypeArguments()[1].getTypeName();
                handlerMap.put(commandIdentifier, handler);
            }
        }
    }
}
