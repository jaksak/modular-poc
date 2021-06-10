package modular.poc.commons.command;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.*;

@RequiredArgsConstructor
public class LocalCommandExecutor implements CommandExecutor {

    private final CommandHandlerProvider commandHandlerProvider;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


    @Override
    public <RESULT, CMD extends Command<RESULT>> void asyncExecute(CMD command) {
        CommandHandler<RESULT, CMD> handler = commandHandlerProvider.provide(command);
        executorService.execute(() -> handler.handle(command));
    }

    @Override
    public <RESULT, CMD extends Command<RESULT>> RESULT synchronousExecute(CMD command) {
        CommandHandler<RESULT, CMD> handler = commandHandlerProvider.provide(command);
        return handler.handle(command);
    }
}
