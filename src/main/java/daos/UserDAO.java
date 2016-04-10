package main.java.daos;

import main.java.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class UserDAO implements IUserDAO {

    @Override
    public void addUser(UserDTO dto) throws SQLException {
        
    }

    @Override
    public List<UserDTO> getUsers() throws SQLException {
        return null;
    }

    @Override
    public void deleteUsers() throws SQLException {

    }
}
