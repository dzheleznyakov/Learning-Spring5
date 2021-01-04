package zh.learn.spring.springclientexamples.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import zh.learn.spring.springclientexamples.services.ApiService;

@Slf4j
@Controller
public class UserController {
    private final ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public Mono<String> index() {
        return Mono.just("index");
    }

    @PostMapping({"/users", "/users/"})
    public Mono<String> formPost(Model model, ServerWebExchange serverWebExchange) {
        Mono<Integer> limitMono = serverWebExchange
                .getFormData()
                .map(data -> data.getFirst("limit"))
                .map(Integer::valueOf);
        model.addAttribute("users", apiService.getUsers(limitMono));
        return Mono.just("userlist");
    }
}
