package main.java.daos;

import main.java.dto.GameDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class GameDAO implements IGameDAO {

    @Override
    public void addGameObject(GameDTO dto) throws SQLException {

    }

    @Override
    public GameDTO getGameModel(int gameID) throws SQLException {
        return null;
    }

    @Override
    public List<GameDTO> getAllGames() throws SQLException {
        return null;
    }

    @Override
    public void updateGame(GameDTO dto) throws SQLException {

    }

    @Override
    public void deleteAllGames() throws SQLException {

    }

    @Override
    public void deleteGame(int gameID) throws SQLException {

    }
}
