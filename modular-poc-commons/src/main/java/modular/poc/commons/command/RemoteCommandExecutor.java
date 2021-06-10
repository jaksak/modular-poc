package modular.poc.commons.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import modular.poc.commons.command.exception.*;
import modular.poc.commons.command.rest.*;

public class RemoteCommandExecutor implements CommandExecutor {
    private final String commandUrl;
    private final ObjectMapper objectMapper;

    @Override
    public <RESULT, CMD extends Command<RESULT>> void asyncExecute(CMD command) {
        Unirest.post(commandUrl)
                .header("Content-Type", "application/json")
                .body(command)
                .asEmptyAsync();
    }

    @Override
    public <RESULT, CMD extends Command<RESULT>> RESULT synchronousExecute(CMD command) {
        var commandView = new CommandView(command, command.getClass().getName());
        var httpResponse = Unirest.post(commandUrl)
                .header("Content-Type", "application/json")
                .body(commandView)
                .asJson();
        if (httpResponse.isSuccess()) {
            var jsonResult = httpResponse.getBody().getObject();
            var errorCode = jsonResult.get("errorCode");
            var resultAsString = jsonResult.getString("result");
            if (errorCode == null) {
                return prepareSuccessResponse(resultAsString);
            } else {
                throw new ModuleException(jsonResult.getInt("errorCode"), "Remote exception" + jsonResult.getString("message"));
            }
        } else {
            throw new ModuleException(ExceptionCodes.INVALID_RESPONSE, "invalid response");
        }
    }

    private <RESULT> RESULT prepareSuccessResponse(String resultAsString) {
        try {
            return resultAsString == null || resultAsString.isBlank() ? null : objectMapper.readValue(resultAsString, new TypeReference<RESULT>() {
            });
        } catch (JsonProcessingException e) {
            throw new ModuleException(ExceptionCodes.PARSING_ERROR, e.getMessage());
        }
    }

    public RemoteCommandExecutor(String baseUrl, ObjectMapper objectMapper) {
        this.commandUrl = baseUrl + "/command";
        this.objectMapper = objectMapper;
    }
}
