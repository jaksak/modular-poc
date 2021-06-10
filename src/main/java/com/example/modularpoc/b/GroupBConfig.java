package com.example.modularpoc.b;

import b.module1.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import modular.poc.commons.command.*;
import org.springframework.context.annotation.*;

@Profile("B")
@Configuration
public class GroupBConfig {

    @Bean
    public B1CommandHandler b1CommandHandler(
            CommandExecutor aCommandExecutor
    ) {
        return new B1CommandHandler(aCommandExecutor);
    }

    @Bean
    public HttpEndpoint httpEndpoint(
            CommandExecutor bCommandExecutor
    ) {
        return new HttpEndpoint(bCommandExecutor);
    }

    @Bean
    public CommandExecutor bCommandExecutor(
            CommandHandlerProvider bCommandHandlerProvider
    ) {
        return new LocalCommandExecutor(bCommandHandlerProvider);
    }

    @Bean
    public CommandHandlerProvider bCommandHandlerProvider() {
        return new CommandHandlerProvider(CommandLocation.GROUP_B);
    }

    @Bean
    @Profile("!A")
    public CommandExecutor aCommandExecutor(
    ) {
        return new RemoteCommandExecutor("http://localhost:8041", new ObjectMapper());
    }
}
