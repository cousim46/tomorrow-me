package tomorrowme.todo.api.service;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import tomorrowme.todo.api.service.annotation.WriteService;
import tomorrowme.todo.domain.account.Account;
import tomorrowme.todo.domain.account.AccountRepository;

@WriteService
@RequiredArgsConstructor
public class AccountWriteService {

    private final AccountRepository accountRepository;

    public void singUp(String phone, String keyword) {
        try {
            accountRepository.save(Account.singUp(phone, keyword));
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                throw  new DataIntegrityViolationException("이미 존재하는 정보입니다.");
            }
            throw e;
        }
    }
}
