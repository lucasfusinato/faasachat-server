package br.com.repository;

import br.com.repository.SessionRepository;
import br.com.repository.UserRepository;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.model.Session;
import br.com.model.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import br.com.util.DbConnect;

/**
 *
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public class SessionRepositoryTest {

    static Session session = null;
    static User user = null;

    static SessionRepository model = null;

    @BeforeClass
    public static void setUpClass() {
        model = new SessionRepository();
        insertUser();
    }

    @AfterClass
    public static void tearDownClass() {
        deletar();
    }

    public static void insert() {
        insertUser();
        insertSession();
    }

    public static void insertUser() {
        user = new User(5555, "user_test", "555@testmail.com", "SomePass123", 2019);
        try {
            UserRepository userRep = new UserRepository();
            userRep.insert(user);
        } catch (Exception ex) {
            Logger.getLogger(SessionRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        session = new Session(88888, user, LocalDateTime.now(), true, "6SD5G4D65S4E4F65SD6FG54WEF65");
    }

    public static void insertSession() {
        try {
            model.insert(session);
        } catch (Exception ex) {
            Logger.getLogger(SessionRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deletar(){
        deletarSessao();
        deletarUsuario();
    }

    public static void deletarSessao() {
        try {
            PreparedStatement stm = DbConnect.getConnection().prepareStatement("DELETE FROM session WHERE ses_id = ?");
            stm.setLong(1, session.getId());
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deletarUsuario() {
        try {
            PreparedStatement stm = DbConnect.getConnection().prepareStatement("DELETE FROM user WHERE usu_id = ?");
            stm.setLong(1, user.getId());
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of insert method, of class SessionRepository.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        deletarSessao();
        assertTrue(model.insert(session));
    }

    /**
     * Test of update method, of class SessionRepository.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        session.setLastUpdate(LocalDateTime.now());
        assertTrue(model.update(session));
    }

}
