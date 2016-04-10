package database;

import redis.clients.jedis.Jedis;

/**
 * @author Joel Bradley
 */
public class Database implements IDatabase {

    private static Jedis connection = new Jedis("45.55.178.18", 6379);

    public static Jedis getConnection() {
        return connection;
    }

    @Override
    public void init() {

    }

    @Override
    public void clear() {

    }
}
