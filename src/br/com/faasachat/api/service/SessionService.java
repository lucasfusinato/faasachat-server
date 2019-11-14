package br.com.faasachat.api.service;

import java.time.LocalDateTime;

import br.com.faasachat.api.model.Token;
import br.com.faasachat.api.repository.SessionMemoryRepository;
import br.com.faasachat.domain.model.Session;
import br.com.faasachat.domain.model.User;

/**
 * Service to execute sessions business rules.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class SessionService {

    /**
     * Repository to manipulate sessions in database.
     */
    private SessionMemoryRepository sessionRepository = new SessionMemoryRepository();
    
    /**
     * Creates a user session.
     * @param nickname
     * @param password
     * @return
     */
    public Session createSession(String nickname, String password) throws Exception {
        Session session = new Session();
        session.setUser(new UserService().authenticate(nickname, password));
        session.setActive(true);
        session.setToken(Token.getInstance().generate());
        session.setLastUpdate(LocalDateTime.now());
        sessionRepository.insert(session);
        return session;
    }

    /**
     * Updates a session.
     * @param session
     * @return
     */
    public boolean updateSession(Session session) throws Exception {
        session.setLastUpdate(LocalDateTime.now());
        return sessionRepository.update(session);
    }

    /**
     * Destroys a session.
     * @param session
     * @return
     */
    public boolean destroySession(Session session) throws Exception {
        session.setActive(false);
        return sessionRepository.update(session);
    }

    /**
     * Finds a session by token value.
     * @param token
     * @return
     */
    public Session getSessionByToken(String token) throws Exception {
        return sessionRepository.findByToken(token);
    }

    public Session getActiveSessionByUser(User user) {
        return sessionRepository.findActiveByUser(user);
    }

}
