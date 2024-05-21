package tomorrowme.todo.api.controller.work;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomorrowme.todo.api.controller.work.dto.request.BoxCreate;
import tomorrowme.todo.api.controller.work.dto.request.BoxFind;
import tomorrowme.todo.api.service.work.BoxReadService;
import tomorrowme.todo.api.service.work.BoxWriteService;
import tomorrowme.todo.api.service.work.dto.response.BoxInfo;


@RequiredArgsConstructor
@RequestMapping("/api/box")
@RestController
public class BoxController {
    private final BoxWriteService boxWriteService;
    private final BoxReadService  boxReadService;

    @PostMapping
    public void create(@Valid @RequestBody BoxCreate boxCreate) {
        boxWriteService.create(boxCreate.phone(), boxCreate.keyword(), boxCreate.title(), LocalDateTime.now());
    }

    @GetMapping
    public List<BoxInfo> findAll(@Valid BoxFind boxFind) {
        return boxReadService.findAll(boxFind.phone(), boxFind.keyword());
    }
}
