package br.com.repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ContactRepositoryTest {
    
    private static ContactRepository model;
    private static UserRepository userRepository;

    @BeforeClass
    public static void setUpClass() {
        deleteContacts();
        insertContacts();
        model = new ContactRepository();
        userRepository = new UserRepository();
    }

    @AfterClass
    public static void tearDownClass() {
        deleteContacts();
    }

    public static void insertContacts() {
        try {
            Statement statement = DbConnect.getConnection().createStatement();
            statement.execute("INSERT INTO user(usu_id, usu_nickname, usu_email, usu_password, usu_year_of_birth) VALUES (65000, 'user1', 'user1@unit.com.br', 'passuser1', 2000);");
            statement.execute("INSERT INTO user(usu_id, usu_nickname, usu_email, usu_password, usu_year_of_birth) VALUES (65001, 'user2', 'user2@unit.com.br', 'passuser2', 1999);");
            statement.execute("INSERT INTO user(usu_id, usu_nickname, usu_email, usu_password, usu_year_of_birth) VALUES (65002, 'user3', 'user3@unit.com.br', 'passuser3', 2001);");
            statement.execute("INSERT INTO user(usu_id, usu_nickname, usu_email, usu_password, usu_year_of_birth) VALUES (65003, 'user4', 'user4@unit.com.br', 'passuser4', 2002);");
            statement.execute("INSERT INTO user(usu_id, usu_nickname, usu_email, usu_password, usu_year_of_birth) VALUES (65004, 'user5', 'user5@unit.com.br', 'passuser5', 1998);");
            
            statement.execute("INSERT INTO contact(usu_id, usu_id_contact) VALUES (65000, 65001);");
            statement.execute("INSERT INTO contact(usu_id, usu_id_contact) VALUES (65000, 65002);");
            statement.execute("INSERT INTO contact(usu_id, usu_id_contact) VALUES (65001, 65000);");
            statement.execute("INSERT INTO contact(usu_id, usu_id_contact) VALUES (65002, 65001);");
            statement.execute("INSERT INTO contact(usu_id, usu_id_contact) VALUES (65002, 65000);");
        } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteContacts() {
        try {
            Statement statement = DbConnect.getConnection().createStatement();
            statement.execute("DELETE FROM contact WHERE usu_id IN (65000, 65001, 65002, 65003, 65004)");
            statement.execute("DELETE FROM user WHERE usu_id IN (65000, 65001, 65002, 65003, 65004)");
        } catch (SQLException ex) {
            Logger.getLogger(ContactRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContacts method, of class ContactRepository.
     */
    @Test
    public void testGetContacts() {
        System.out.println("getContacts");
        User user = userRepository.findByNickname("user1");
        assertEquals(2, model.getContacts(user).size());
    }

    /**
     * Test of insert method, of class ContactRepository.
     */
    @Test
    public void testInsert() {
        assertTrue(model.insert(userRepository.findByNickname("user2"), userRepository.findByNickname("user3")));
    }

    /**
     * Test of remove method, of class ContactRepository.
     */
    @Test
    public void testRemove() {
        model.insert(userRepository.findByNickname("user4"), userRepository.findByNickname("user5"));
        assertTrue(model.remove(userRepository.findByNickname("user4"), userRepository.findByNickname("user5")));
    }

}
