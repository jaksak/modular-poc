package b.module1;

import lombok.RequiredArgsConstructor;
import modular.poc.commons.command.CommandExecutor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HttpEndpoint {

    private final CommandExecutor bCommandExecutor;

    @GetMapping
    public String getSth() {
        return bCommandExecutor.synchronousExecute(new B1Command());
    }
}
