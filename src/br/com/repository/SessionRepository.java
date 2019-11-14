package br.com.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.model.Session;
import br.com.util.DbConnect;

/**
 *
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public class SessionRepository implements SessionRepositoryInterface{

    @Override
    public boolean insert(Session session) throws Exception {
        int updatedRows = 0;
        try{
            String sql = "INSERT INTO session(usu_id, last_update, token, ses_id) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = DbConnect.getConnection().prepareStatement(sql);
            stm.setLong(1, session.getUser().getId());
            stm.setString(2, session.getLastUpdate().toString());
            stm.setString(3, session.getToken());
            stm.setLong(4, session.getId());
            updatedRows = stm.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(SessionRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updatedRows > 0;
    }

    @Override
    public boolean update(Session session) throws Exception {
        String sql = "UPDATE session" +
                    "   SET usu_id=?, last_update=?, token=? " +
                    " WHERE ses_id=?;";
        int updatedRows = 0;
        try {
            PreparedStatement stm = DbConnect.getConnection().prepareStatement(sql);
            stm.setLong(1, session.getUser().getId());
            stm.setString(2, session.getLastUpdate().toString());
            stm.setString(3, session.getToken());
            stm.setLong(4, session.getId());
            updatedRows = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SessionRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updatedRows > 0;
    }
    
}
