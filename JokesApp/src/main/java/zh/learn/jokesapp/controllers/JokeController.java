package zh.learn.jokesapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import zh.learn.jokesapp.services.QuoteService;

@Controller
public class JokeController {
    private final QuoteService quoteService;

    public JokeController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @RequestMapping({"/", ""})
    public String showJoke(Model model) {
        model.addAttribute("joke", quoteService.getRandomQuote());
        return "chucknorris";
    }
}
