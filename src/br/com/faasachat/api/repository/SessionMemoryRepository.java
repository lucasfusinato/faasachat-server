package br.com.faasachat.api.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.faasachat.domain.model.Session;
import br.com.faasachat.domain.model.User;

/**
 * Repository to access sessions in database.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class SessionMemoryRepository {
    
    /**
     * List of all sessions.
     * TODO remove it and implement all operations in database
     */
    private static List<Session> sessions = new ArrayList<>();

    /**
     * Inserts a session in database.
     * @param session
     * @return
     */
    public boolean insert(Session session) {
        sessions.add(session);
        return true;
    }

    /**
     * Updates a session in database.
     * @param session
     * @return
     */
    public boolean update(Session session) {
        Session currentSession = null;
        boolean updated = false;
        int index = sessions.size() - 1;
        while(index-- >= 0) {
            currentSession = sessions.get(index);
            if(session.equals(currentSession)) {
                sessions.set(index, session);
                updated = true;
                break;
            }
        }
        return updated;
    }

    /**
     * Returns a session instance by token value.
     * @param token
     * @return
     */
    public Session findByToken(String token) {
        for(Session session : sessions) {
            if(session.getToken().equals(token)) {
                return session;
            }
        }
        return null;
    }

    public Session findActiveByUser(User user) {
        for(Session session : sessions) {
            if(session.isActive() && user.equals(session.getUser())) {
                return session;
            }
        }
        return null;
    }

}
