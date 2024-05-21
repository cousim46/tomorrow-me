package tomorrowme.todo.api.controller.work;

import jakarta.validation.Valid;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomorrowme.todo.api.controller.work.dto.request.BoxCreate;
import tomorrowme.todo.api.service.work.BoxWriteService;

@RequiredArgsConstructor
@RequestMapping("/api/box")
@RestController
public class BoxController {
    private final BoxWriteService boxWriteService;

    @PostMapping
    public void create(@Valid @RequestBody BoxCreate boxCreate) {
        boxWriteService.create(boxCreate.phone(), boxCreate.keyword(), boxCreate.title(), LocalDate.now());
    }
}
