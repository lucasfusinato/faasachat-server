package br.com.faasachat.api.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.faasachat.domain.model.User;

/**
 * Repository to access users in database.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class UserMemoryRepository {
    
    /**
     * List of all users.
     * TODO remove it and implement all operations in database
     */
    private static List<User> users = new ArrayList<>();
    
    /**
     * Instantiates user repository with default rows.
     */
    public UserMemoryRepository() {
        users.add(new User(1L, "admin",    "admin@email.com",    "admin",    1989));
        users.add(new User(2L, "lucas",    "lucas@email.com",    "lucas",    2002));
        users.add(new User(3L, "joao",     "joao@email.com",     "joao",     1994));
        users.add(new User(4L, "fernando", "fernando@email.com", "fernando", 1981));
    }

    /**
     * Inserts user data.
     * @param user
     * @return
     */
    public boolean insert(User user) {
        users.add(user);
        return true;
    }
    
    /**
     * Updates user data.
     * @param user
     * @return
     */
    public boolean update(User user) {
        User currentUser = null;
        boolean updated = false;
        int index = users.size() - 1;
        while(index-- >= 0) {
            currentUser = users.get(index);
            if(user.equals(currentUser)) {
                users.set(index, user);
                updated = true;
                break;
            }
        }
        return updated;
    }

    /**
     * Finds a user by your nickname.
     * @param nickname
     * @return
     */
    public User findByNickname(String nickname) {
        for(User user : users) {
            if(user.getNickname().equals(nickname)) {
                return user;
            }
        }
        return null;
    }

}
