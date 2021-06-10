package b.module1;

import a.module1.A1Command;
import lombok.RequiredArgsConstructor;
import modular.poc.commons.command.*;

@RequiredArgsConstructor
public class B1CommandHandler implements CommandHandler<String, B1Command> {

    private final CommandExecutor aCommandExecutor;

    @Override
    public String handle(B1Command command) {
        return "Hello " + aCommandExecutor.synchronousExecute(new A1Command());
    }

    @Override
    public CommandLocation getCommandLocation() {
        return CommandLocation.GROUP_B;
    }
}
