package server.persistence;

import server.persistence.dto.GameDTO;
import server.persistence.dto.UserDTO;

/**
 * Created by boscho on 4/4/16.
 */
public interface IDatabase {

    void clear();

    void shutdown();

    void addUser(UserDTO dto);

    void addGame(GameDTO dto);
}