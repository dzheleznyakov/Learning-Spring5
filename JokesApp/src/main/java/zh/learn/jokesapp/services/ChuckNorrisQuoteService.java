package zh.learn.jokesapp.services;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;

@Service
public class ChuckNorrisQuoteService implements QuoteService {
    private final ChuckNorrisQuotes chuckNorrisQuotes;

    public ChuckNorrisQuoteService() {
        chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    @Override
    public String getRandomQuote() {
        return chuckNorrisQuotes.getRandomQuote();
    }
}
