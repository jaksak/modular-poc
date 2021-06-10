package a.module1;

import modular.poc.commons.command.*;
import modular.poc.commons.command.handler.CommandHandler;

@LocatedCommand(location = CommandLocation.GROUP_A)
public class A1CommandHandler implements CommandHandler<Integer, A1Command> {

    private int i;

    @Override
    public Integer handle(A1Command command) {
        return i++;
    }
}
