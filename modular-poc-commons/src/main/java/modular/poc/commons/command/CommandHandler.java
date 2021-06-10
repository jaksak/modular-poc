package modular.poc.commons.command.handler;

import modular.poc.commons.command.Command;

public interface CommandHandler<RESULT, CMD extends Command<RESULT>> {
    RESULT handle(CMD command);
}
