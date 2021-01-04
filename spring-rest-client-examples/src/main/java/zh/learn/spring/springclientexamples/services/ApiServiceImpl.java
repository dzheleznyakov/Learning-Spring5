package zh.learn.spring.springclientexamples.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zh.learn.spring.api.domain.User;
import zh.learn.spring.api.domain.UserData;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private static final String LIMIT_QUERY_PARAM = "_limit";

    private final RestTemplate restTemplate;
    private final String api_url;

    public ApiServiceImpl(
            RestTemplate restTemplate,
            @Value("${api.url}") String api_url
    ) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url)
                .queryParam(LIMIT_QUERY_PARAM, limit);
        return restTemplate.getForObject(uriBuilder.toUriString(), UserData.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Flux<User> getUsers(Mono<Integer> limitMono) {
        return limitMono
                .flatMap(this::getUsersFromRemote)
                .flatMap(resp -> resp.bodyToMono(UserData.class))
                .flatMapIterable(Iterable.class::cast);
    }

    private Mono<ClientResponse> getUsersFromRemote(Integer l) {
        return WebClient
                .create(api_url)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam(LIMIT_QUERY_PARAM, l).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }
}
