package br.com.faasachat.api.service;

import br.com.faasachat.api.repository.UserMemoryRepository;
import br.com.faasachat.domain.model.User;

/**
 * Service to execute users business rules.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class UserService {

    /**
     * Repository to manipulate users in database.
     */
    private UserMemoryRepository userRepository = new UserMemoryRepository();

    /**
     * Creates a user.
     * @param user
     * @return
     */
    public boolean createUser(User user) throws Exception {
        return userRepository.insert(user);
    }

    /**
     * Updates a user.
     * @param user
     * @return
     */
    public boolean updateUser(User user) throws Exception {
        return userRepository.update(user);
    }
    /**
     * Returns user data by nickname and password.
     * @param nickname
     * @param password
     * @return
     */
    
    public User authenticate(String nickname, String password) throws Exception {
        User user = userRepository.findByNickname(nickname);
        if(user == null) {
            throw new Exception("User not found.");
        }
        if(!user.getPassword().equals(password)) {
            throw new Exception("Password doesn't matches.");
        }
        return user;
    }

}
