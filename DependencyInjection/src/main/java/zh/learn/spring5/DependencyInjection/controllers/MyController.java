package zh.learn.spring5.DependencyInjection.controllers;

import org.springframework.stereotype.Controller;
import zh.learn.spring5.DependencyInjection.services.GreetingService;

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
