package server.persistence;

import daos.IUserDAO;
import factory.DAOFactory;
import redis.clients.jedis.Jedis;
import server.persistence.dto.GameDTO;
import server.persistence.dto.UserDTO;

import java.util.Iterator;
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
    public void addGame(GameDTO dto) {

    }
}
