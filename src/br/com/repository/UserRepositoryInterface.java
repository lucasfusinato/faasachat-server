package br.com.repository;

import br.com.model.User;

/**
 * Repository interface to access users in database.
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public interface UserRepositoryInterface {

    /**
     * Inserts user data.
     * @param user
     * @return
     */
    boolean insert(User user) throws Exception;

    /**
     * Updates user data.
     * @param user
     * @return
     */
    boolean update(User user) throws Exception;

    /**
     * Finds a user by your nickname.
     * @param nickname
     * @param password
     * @return
     */
    User findByNickname(String nickname) throws Exception;

}
