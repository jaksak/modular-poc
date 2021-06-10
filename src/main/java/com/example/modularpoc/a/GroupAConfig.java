package com.example.modularpoc.a;

import a.module1.A1CommandHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import modular.poc.commons.command.*;
import modular.poc.commons.command.rest.CommandEndpoint;
import org.springframework.context.annotation.*;

@Profile("A")
@Configuration
public class GroupAConfig {

    @Bean
    public A1CommandHandler a1CommandHandler() {
        return new A1CommandHandler();
    }

    @Bean
    public CommandExecutor aCommandExecutor(
            CommandHandlerProvider aCommandHandlerProvider
    ) {
        return new LocalCommandExecutor(aCommandHandlerProvider);
    }

    @Bean
    public CommandHandlerProvider aCommandHandlerProvider() {
        return new CommandHandlerProvider(CommandLocation.GROUP_A);
    }

    @Bean
    @Profile("!B")
    public CommandExecutor bCommandExecutor(
            ObjectMapper objectMapper
    ) {
        return new RemoteCommandExecutor("localhost:8040", objectMapper);
    }

    @Bean
    public CommandEndpoint commandEndpoint(
            CommandHandlerProvider aCommandHandlerProvider,
            ObjectMapper objectMapper
    ) {
        return new CommandEndpoint(aCommandHandlerProvider, objectMapper);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
