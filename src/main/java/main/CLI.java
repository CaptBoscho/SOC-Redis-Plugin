package main;

import daos.IGameDAO;
import factory.DAOFactory;
import server.persistence.Database;
import server.persistence.dto.GameDTO;

/**
 * @author Derek Argueta
 *
 * A class for manually interacting with Redis.
 */
public class CLI {

    public static void main(String[] args) {
        IGameDAO dao = DAOFactory.getInstance().createGameDAO();
        for(GameDTO game : dao.getAllGames()) {
            System.out.println(game.getGameID() + " --- " + game.getTitle());
        }
    }
}
