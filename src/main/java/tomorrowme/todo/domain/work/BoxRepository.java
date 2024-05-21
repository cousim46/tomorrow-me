package tomorrowme.todo.domain.work;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tomorrowme.todo.domain.account.Account;

public interface BoxRepository extends JpaRepository<Box, Long> {

    List<Box> findAllByAccountOrderByRegistrationDateDesc(Account account);

}
