package modular.poc.commons.command;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface LocatedCommand {
    CommandLocation location();
}
