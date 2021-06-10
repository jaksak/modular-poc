package modular.poc.commons.command;

public interface CommandHandler<RESULT, CMD extends Command<RESULT>> {
    RESULT handle(CMD command);

    CommandLocation getCommandLocation();
}
