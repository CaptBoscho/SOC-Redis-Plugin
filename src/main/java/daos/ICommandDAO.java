package daos;

import server.persistence.dto.CommandDTO;

import java.util.List;

/**
 * @author Joel Bradley
 */
public interface ICommandDAO {
    /**
     * Handles adding a user,
     * adding a command
     * adding a game
     * @param dto
     */
    void addCommand(CommandDTO dto);

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     * @return
     */
    List<CommandDTO> getCommands(int gameID);

    List<CommandDTO> getAllCommands();

    /**
     * mostly be used for updating the game blob state
     */
    void deleteAllCommands();

    /**
     * Mostly be used for deleting commands every n
     * moves.
     */
    void deleteCommandsFromGame(int gameID);
}
