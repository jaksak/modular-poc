package a.module1;

import modular.poc.commons.command.*;

public class A1CommandHandler implements CommandHandler<Integer, A1Command> {

    private int i;

    @Override
    public Integer handle(A1Command command) {
        return i++;
    }

    @Override
    public CommandLocation getCommandLocation() {
        return CommandLocation.GROUP_A;
    }
}
