package zh.learn.spring.springclientexamples.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zh.learn.spring.api.domain.User;
import zh.learn.spring.api.domain.UserData;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
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
                .queryParam("_limit", limit);
        return restTemplate.getForObject(uriBuilder.toUriString(), UserData.class);
    }
}
