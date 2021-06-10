package b.module1;

import a.module1.A1Command;
import lombok.RequiredArgsConstructor;
import modular.poc.commons.command.*;
import modular.poc.commons.command.handler.CommandHandler;

@LocatedCommand(location = CommandLocation.GROUP_B)
@RequiredArgsConstructor
public class B1CommandHandler implements CommandHandler<String, B1Command> {

    private final CommandExecutor aCommandExecutor;

    @Override
    public String handle(B1Command command) {
        return "Hello " + aCommandExecutor.synchronousExecute(new A1Command());
    }
}
