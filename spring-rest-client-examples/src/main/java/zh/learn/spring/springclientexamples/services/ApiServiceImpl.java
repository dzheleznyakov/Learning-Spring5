package zh.learn.spring.springclientexamples.services;

import org.springframework.web.client.RestTemplate;
import zh.learn.spring.api.domain.User;
import zh.learn.spring.api.domain.UserData;

import java.util.List;

public class ApiServiceImpl implements ApiService {
    private final RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/users?_limit=" + limit, UserData.class);
    }
}
