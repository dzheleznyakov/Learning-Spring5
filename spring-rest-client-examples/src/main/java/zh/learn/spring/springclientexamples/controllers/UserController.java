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
//        MultiValueMap<String, String> map = serverWebExchange.getFormData().block();
//
//        Integer limit = new Integer(map.get("limit").get(0));
//
//        log.debug("Received Limit value: " + limit);
//        //default if null or zero
//        if(limit == null || limit == 0){
//            log.debug("Setting limit to default of 10");
//            limit = 10;
//        }
//
//        model.addAttribute("users", apiService.getUsers(limit));
//
//        return "userlist";
        return serverWebExchange.getFormData()
                .map(map -> {
                    Integer limit = Integer.valueOf(map.get("limit").get(0));

                    log.debug("Received Limit value: " + limit);
                    if (limit == 0) {
                        log.debug("Setting limit to default of 10");
                        limit = 10;
                    }

                    model.addAttribute("users", apiService.getUsers(limit));

                    return "userlist";
                });
    }
}
