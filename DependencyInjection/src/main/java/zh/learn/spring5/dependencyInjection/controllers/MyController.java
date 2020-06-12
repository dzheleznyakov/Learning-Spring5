package zh.learn.spring5.dependencyInjection.controllers;

import org.springframework.stereotype.Controller;
import zh.learn.spring5.dependencyInjection.services.GreetingService;

@Controller
public class MyController {
    private final GreetingService greetingService;

    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return greetingService.sayGreeting();
    }
}
