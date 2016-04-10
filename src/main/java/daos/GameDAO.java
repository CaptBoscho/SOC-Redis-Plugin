package daos;

import database.Database;
import redis.clients.jedis.Jedis;
import server.persistence.dto.GameDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Joel Bradley
 */
public class GameDAO implements IGameDAO {

    @Override
    public void addGameObject(GameDTO dto) {
        Jedis jedis = Database.getConnection();
        jedis.lpush(dto.getGameID() + "", dto.getState());
        jedis.lpush(dto.getGameID() + "", dto.getTitle());
    }

    @Override
    public GameDTO getGameModel(int gameID) {
        Jedis jedis = Database.getConnection();
        List<String> result = jedis.lrange(gameID + "", 0, 1);
        GameDTO dto = new GameDTO();
        dto.setGameID(gameID);
        dto.setTitle(result.get(0));
        dto.setState(result.get(1));
        return dto;
    }

    @Override
    public List<GameDTO> getAllGames() {
        Jedis jedis = Database.getConnection();
        ArrayList<GameDTO> games = new ArrayList<>();
        Set<String> keys = jedis.keys("*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            games.add(getGameModel(Integer.parseInt(iter.next())));
        }
        return games;
    }

    @Override
    public void updateGame(GameDTO dto) {
        Jedis jedis = Database.getConnection();
        jedis.rpop(dto.getGameID() + "");
        jedis.rpush(dto.getState());
    }

    @Override
    public void deleteAllGames() {
        Jedis jedis = Database.getConnection();
        Set<String> keys = jedis.keys("*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            jedis.del(iter.next());
        }
    }

    @Override
    public void deleteGame(int gameID) {
        Jedis jedis = Database.getConnection();
        jedis.del(gameID + "");
    }

}
