package tomorrowme.todo.api.controller.account;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomorrowme.todo.api.controller.account.dto.request.SignUp;
import tomorrowme.todo.api.service.AccountWriteService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountWriteService accountWriteService;

    @PostMapping
    public void signUp(@Valid @RequestBody SignUp signUp) {
        accountWriteService.singUp(signUp.phone(), signUp.keyword(), signUp.wakeUpTime(),
            signUp.sleepTime());
    }
}