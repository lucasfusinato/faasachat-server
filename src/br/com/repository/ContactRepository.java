package br.com.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.model.User;
import br.com.util.DbConnect;

/**
 *
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public class ContactRepository implements ContactRepositoryInterface {

    @Override
    public List<User> getContacts(User user) {
        String sql =  "SELECT usu_id, usu_nickname, usu_email, usu_password, usu_year_of_birth "
                    +   "FROM user"
                    + " WHERE usu_id IN (SELECT usu_id_contact "
                                       + "FROM contact "
                                      + "WHERE usu_id = ?)";
        PreparedStatement stm;
        ArrayList<User> contacts = new ArrayList<>();
        try {
            stm = DbConnect.getConnection().prepareStatement(sql);
            stm.setLong(1, user.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("usu_id");
                String nickname = rs.getString("usu_nickname");
                String email = rs.getString("usu_email");
                String password = rs.getString("usu_password");
                int yearOfBirth = rs.getInt("usu_year_of_birth");
                contacts.add(new User(id, nickname, email, password, yearOfBirth));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contacts;
    }

    @Override
    public boolean insert(User user, User userContact) {
        int updatedRows = 0;
        try{
            String sql = "INSERT INTO contact(usu_id, usu_id_contact) VALUES (?, ?);";
            PreparedStatement stm = DbConnect.getConnection().prepareStatement(sql);
            stm.setLong(1, user.getId());
            stm.setLong(2, userContact.getId());
            updatedRows = stm.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(ContactRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updatedRows > 0;
    }

    @Override
    public boolean remove(User user, User userContact) {
        try{
            String sql = "DELETE FROM contact WHERE usu_id = ? AND usu_id_contact = ?;";
            PreparedStatement stm = DbConnect.getConnection().prepareStatement(sql);
            stm.setLong(1, user.getId());
            stm.setLong(2, userContact.getId());
            stm.execute();
        } catch(SQLException ex) {
            Logger.getLogger(ContactRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
