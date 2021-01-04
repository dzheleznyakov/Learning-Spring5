package zh.learn.spring.springclientexamples.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zh.learn.spring.api.domain.User;

import java.util.List;

public interface ApiService {
    List<User> getUsers(Integer limit);

    Flux<User> getUsers(Mono<Integer> limit);
}
