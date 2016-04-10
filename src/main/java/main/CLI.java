package main;

import redis.clients.jedis.Jedis;

/**
 * @author Derek Argueta
 *
 * A class for manually interacting with Redis.
 */
public class CLI {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("45.55.178.18", 6379);
        jedis.set("hi", "hello");
    }
}
