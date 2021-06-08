package org.example.app.repository;

import org.apache.log4j.Logger;
import org.example.web.dto.User;
import org.example.web.dto.User.UserRole;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository implements ProjectRepository<User> {

    private final Logger logger = Logger.getLogger(UserRepository.class);
    private final Map<String, User> repo = new HashMap<>();

    {
        repo.put("root", new User("root", "pass", UserRole.ADMIN));
    }

    public User getByName(String username) {
        return repo.get(username);
    }

    @Override
    public boolean store(User user) {
        String username = user.getUsername();
        if (!repo.containsKey(username)) {
            logger.info("Creating new user: " + username);
            repo.put(username, user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> retrieveAll() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public boolean remove(User user) {
        throw new NotImplementedException();
    }
}
