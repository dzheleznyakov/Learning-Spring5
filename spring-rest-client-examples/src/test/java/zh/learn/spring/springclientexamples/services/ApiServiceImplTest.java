package zh.learn.spring.springclientexamples.services;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import zh.learn.spring.api.domain.User;
import zh.learn.spring.springclientexamples.config.RestTemplateConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ApiServiceImplTest {
    ApiService apiService;

    @Before
    public void setUp() {
        RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
        apiService = new ApiServiceImpl(restTemplate, "api_url");
    }

    @Test
    public void testGetUsers() {
        List<User> users = apiService.getUsers(3);

        assertEquals(3, users.size());
    }
}