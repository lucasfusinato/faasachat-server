package br.com.repository;

import br.com.model.Session;

/**
 * Repository interface to access sessions in database.
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public interface SessionRepositoryInterface {

    /**
     * Inserts a session in database.
     * @param session
     * @return
     */
    boolean insert(Session session) throws Exception;

    /**
     * Updates a session in database.
     * @param session
     * @return
     */
    boolean update(Session session) throws Exception;
    
}
