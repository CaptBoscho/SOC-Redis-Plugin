package daos;

import server.persistence.dto.GameDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Joel Bradley
 */
public interface IGameDAO {
    /**
     * Handles adding a user,
     * adding a command
     * adding a game
     * @param dto
     */
    void addGameObject(GameDTO dto);

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     * @return
     */
    GameDTO getGameModel(int gameID);

    List<GameDTO> getAllGames() throws SQLException;

    /**
     * mostly be used for updating the game blob state
     * @param dto
     */
    void updateGame(GameDTO dto);

    /**
     * Deletes all games
     */
    void deleteAllGames();

    /**
     * Deletes a game
     */
    void deleteGame(int gameID);
}
