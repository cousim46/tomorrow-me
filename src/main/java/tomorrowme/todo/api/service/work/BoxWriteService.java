package tomorrowme.todo.api.service.work;

import static tomorrowme.todo.exception.TomorrowErrorCode.NOT_EXIST_INFO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import tomorrowme.todo.api.service.annotation.WriteService;
import tomorrowme.todo.domain.account.Account;
import tomorrowme.todo.domain.account.AccountRepository;
import tomorrowme.todo.domain.work.Box;
import tomorrowme.todo.domain.work.BoxRepository;
import tomorrowme.todo.exception.TomorrowException;

@RequiredArgsConstructor
@WriteService
public class BoxWriteService {

    private final BoxRepository boxRepository;
    private final AccountRepository accountRepository;

    public void create(String phone, String keyword, String title, LocalDateTime registrationDate) {
        Account account = accountRepository.findByPhoneAndKeyword(phone, keyword)
            .orElseThrow(() -> new TomorrowException(NOT_EXIST_INFO));
        try {
            boxRepository.save(Box.create(title, account, registrationDate));
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                String message = e.getMessage().split(":")[0];
                if (e.getMessage().contains("Unique") || e.getMessage().contains("Duplicate")) {
                    message = "이미 등록된 보관함이 존재합니다.";
                }
                throw new DataIntegrityViolationException(message);
            }
        }
    }
}
