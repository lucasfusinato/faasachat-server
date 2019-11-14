package br.com.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.model.User;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import br.com.util.DbConnect;

/**
 *
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public class UserRepositoryTest {

    static User user = null;
    
    static UserRepository model = null;

    @BeforeClass
    public static void setUpClass() {
        model = new UserRepository();
        user = new User(99999, "user_test", "test@testmail.com", "SomePass123", 2019);
    }
    
    public static void delete(){
        try {
            PreparedStatement stm = DbConnect.getConnection().prepareStatement("DELETE FROM user WHERE usu_id = ?");
            stm.setLong(1, user.getId());
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        delete();
    }

    /**
     * Test of insert method, of class UserRepository.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        delete();
        assertTrue(model.insert(user));
    }

    /**
     * Test of update method, of class UserRepository.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        user.setEmail("updated@updatedemail.com");
        assertTrue(model.update(user));
    }

    /**
     * Test of findByNickname method, of class UserRepository.
     */
    @Test
    public void testFindByNickname() throws Exception {
        System.out.println("findByNickname");
        model.insert(user);
        User result = model.findByNickname(user.getNickname());
        assertEquals(user.getId(), result.getId());
        result = model.findByNickname("Invalid user");
        assertNull(result);
    }

}
