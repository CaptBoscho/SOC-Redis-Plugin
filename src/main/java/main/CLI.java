package main;

import server.persistence.Database;

/**
 * @author Derek Argueta
 *
 * A class for manually interacting with Redis.
 */
public class CLI {

    public static void main(String[] args) {
        System.out.println(Database.getConnection().keys("*"));
    }
}
