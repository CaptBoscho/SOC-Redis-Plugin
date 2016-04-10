package main.java.daos;

import main.java.dto.CommandDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class CommandDAO implements ICommandDAO{


    @Override
    public void addCommand(CommandDTO dto) throws SQLException {

    }

    @Override
    public List<CommandDTO> getCommands(int gameID) throws SQLException {
        return null;
    }

    @Override
    public List<CommandDTO> getAllCommands() throws SQLException {
        return null;
    }

    @Override
    public void deleteAllCommands() throws SQLException {

    }

    @Override
    public void deleteCommandsFromGame(int gameID) throws SQLException {

    }
}
