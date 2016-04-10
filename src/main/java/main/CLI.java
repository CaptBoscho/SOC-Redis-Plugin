package main;

import daos.IGameDAO;
import factory.DAOFactory;
import server.persistence.Database;

/**
 * @author Derek Argueta
 *
 * A class for manually interacting with Redis.
 */
public class CLI {

    public static void main(String[] args) {
        IGameDAO dao = DAOFactory.getInstance().createGameDAO();
        dao.deleteGame(2);
        System.out.println(Database.getConnection().keys("*"));
    }
}
