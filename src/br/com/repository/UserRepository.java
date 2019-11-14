package br.com.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class UserRepository implements UserRepositoryInterface {

    @Override
    public boolean insert(User user) throws Exception {
        int updatedRows = 0;
        try{
            String sql = "INSERT INTO user(usu_id, usu_nickname, usu_email, usu_password, usu_year_of_birth) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stm = DbConnect.getConnection().prepareStatement(sql);
            stm.setLong(1, user.getId());
            stm.setString(2, user.getNickname());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getPassword());
            stm.setInt(5, user.getYearOfBirth());
            updatedRows = stm.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updatedRows > 0;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE user" +
                    "   SET usu_email=?, usu_nickname=?, usu_password=?, usu_year_of_birth=? " +
                    " WHERE usu_id=?;";
        int updatedRows = 0;
        try {
            PreparedStatement stm = DbConnect.getConnection().prepareStatement(sql);
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getNickname());
            stm.setString(3, user.getPassword());
            stm.setInt(4, user.getYearOfBirth());
            stm.setLong(5, user.getId());
            updatedRows = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updatedRows > 0;
    }

    @Override
    public User findByNickname(String nickname) {
        String sql = "SELECT usu_id, usu_email, usu_password, usu_year_of_birth "
                    +   "FROM user"
                    + " WHERE usu_nickname = ?";
        User user = null;
        try {
            PreparedStatement stm = DbConnect.getConnection().prepareStatement(sql);
            stm.setString(1, nickname);
            ResultSet rs = stm.executeQuery();
            if(!rs.isAfterLast()){
                rs.next();
                long id = rs.getLong("usu_id");
                String email = rs.getString("usu_email");
                String password = rs.getString("usu_password");
                int yearOfBirth = rs.getInt("usu_year_of_birth");
                user = new User(id, nickname, email, password, yearOfBirth);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

}
