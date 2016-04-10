package server.persistence;

import daos.ICommandDAO;
import daos.IGameDAO;
import daos.IUserDAO;
import factory.DAOFactory;
import redis.clients.jedis.Jedis;
import server.persistence.dto.CommandDTO;
import server.persistence.dto.GameDTO;
import server.persistence.dto.UserDTO;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Joel Bradley
 */
public class Database implements IDatabase {

    private static Database instance;
    private static Jedis connection;

    public static Database getInstance() {
        if(instance == null) {

            instance = new Database();
        }
        return instance;
    }

    public static Jedis getConnection() {
        return getInstance().connection;
    }

    public Database() {
        connection = new Jedis("45.55.178.18", 6379);
    }

    @Override
    public void clear() {
        Set<String> keys = connection.keys("*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            connection.del(iter.next());
        }
    }

    @Override
    public void shutdown() {
        connection.close();
    }

    @Override
    public void addUser(UserDTO dto) {
        IUserDAO dao = DAOFactory.getInstance().createUserDAO();
        dao.addUser(dto);
    }

    @Override
    public List<UserDTO> getUsers() {
        IUserDAO dao = DAOFactory.getInstance().createUserDAO();
        return dao.getUsers();
    }

    @Override
    public void addGame(GameDTO dto) {
        IGameDAO dao = DAOFactory.getInstance().createGameDAO();
        dao.addGameObject(dto);
    }

    @Override
    public List<GameDTO> getAllGames() {
        IGameDAO dao = DAOFactory.getInstance().createGameDAO();
        return dao.getAllGames();
    }

    @Override
    public List<CommandDTO> getCommands(int gameId) {
        ICommandDAO dao = DAOFactory.getInstance().createCommandDAO();
        return dao.getCommands(gameId);
    }

    @Override
    public void addCommand(CommandDTO dto) {
        ICommandDAO dao = DAOFactory.getInstance().createCommandDAO();
        dao.addCommand(dto);
    }

    @Override
    public void updateGame(GameDTO dto) {
        IGameDAO dao = DAOFactory.getInstance().createGameDAO();
        dao.updateGame(dto);
    }

    @Override
    public void deleteCommandsFromGame(int gameID) {
        ICommandDAO dao = DAOFactory.getInstance().createCommandDAO();
        dao.deleteCommandsFromGame(gameID);
    }
}
