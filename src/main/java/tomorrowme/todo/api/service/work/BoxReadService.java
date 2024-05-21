package tomorrowme.todo.api.service.work;

import static tomorrowme.todo.exception.TomorrowErrorCode.NOT_EXIST_INFO;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import tomorrowme.todo.api.service.annotation.ReadService;
import tomorrowme.todo.api.service.work.dto.response.BoxInfo;
import tomorrowme.todo.domain.account.Account;
import tomorrowme.todo.domain.account.AccountRepository;
import tomorrowme.todo.domain.work.BoxRepository;
import tomorrowme.todo.exception.TomorrowException;

@RequiredArgsConstructor
@ReadService
public class BoxReadService {
    private final BoxRepository boxRepository;
    private final AccountRepository accountRepository;

    public List<BoxInfo> findAll(String phone, String keyword) {
        Account account = accountRepository.findByPhoneAndKeyword(phone, keyword)
            .orElseThrow(() -> new TomorrowException(NOT_EXIST_INFO));
        return boxRepository.findAllByAccountOrderByRegistrationDateDesc(account)
            .stream().map(it -> new BoxInfo(it.getRegistrationDate(),it.getTitle(),it.getUpdatedAt()))
            .collect(Collectors.toList());
    }
}
