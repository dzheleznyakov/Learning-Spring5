package zh.learn.spring.springclientexamples.services;

import zh.learn.spring.api.domain.User;

import java.util.List;

public interface ApiService {
    List<User> getUsers(Integer limit);
}
