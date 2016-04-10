package daos;

import server.persistence.dto.UserDTO;

import java.util.List;

/**
 * @author Joel Bradley
 */
public interface IUserDAO {
    /**
     * Handles adding a user
     * @param dto
     */
    void addUser(UserDTO dto);

    /**
     * Handles verifying user which returns userID
     *
     * @return
     */
    List<UserDTO> getUsers();


    /**
     * delete a user
     */
    void deleteUsers() ;
}
