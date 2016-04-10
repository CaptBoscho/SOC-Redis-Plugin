package database;

import redis.clients.jedis.Jedis;

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

    private Database() {
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
}
