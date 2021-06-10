package modular.poc.commons.command;

public interface CommandExecutor {
    <RESULT, CMD extends Command<RESULT>> void asyncExecute(CMD command);

    <RESULT, CMD extends Command<RESULT>> RESULT synchronousExecute(CMD command);
}
