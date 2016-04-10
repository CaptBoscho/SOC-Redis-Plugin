package daos;

import server.persistence.Database;
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
    public int addGameObject(GameDTO dto) {
        Jedis jedis = Database.getConnection();
        Set<String> keys = jedis.keys("game-*");
        Iterator<String> iter = keys.iterator();
        boolean gameExists = false;
        ArrayList<String> ids = new ArrayList<>();
        while(iter.hasNext()) {
            String key = iter.next();
            key = key.substring(5, key.length());
            ids.add(key);
            if(Integer.parseInt(key) == dto.getGameID()) {
                gameExists = true;
            }
        }
        if(!gameExists) {
            jedis.lpush("game-" + dto.getGameID(), dto.getState());
            jedis.lpush("game-" + dto.getGameID(), dto.getTitle());
            return dto.getGameID();
        } else {
            int id = dto.getGameID();
            while(ids.contains(id + "")) {
                id++;
            }
            jedis.lpush("game-" + dto.getGameID(), dto.getState());
            jedis.lpush("game-" + dto.getGameID(), dto.getTitle());
            return id;
        }
    }

    @Override
    public GameDTO getGameModel(int gameID) {
        Jedis jedis = Database.getConnection();
        List<String> result = jedis.lrange("game-" + gameID, 0, 1);
        GameDTO dto = new GameDTO(gameID, result.get(0), result.get(1));
        return dto;
    }

    @Override
    public List<GameDTO> getAllGames() {
        Jedis jedis = Database.getConnection();
        ArrayList<GameDTO> games = new ArrayList<>();
        Set<String> keys = jedis.keys("game-*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            key = key.substring(5, key.length());
            games.add(getGameModel(Integer.parseInt(key)));
        }
        return games;
    }

    @Override
    public void updateGame(GameDTO dto) {
        Jedis jedis = Database.getConnection();
        jedis.rpop("game-" + dto.getGameID());
        jedis.rpush("game-" + dto.getGameID(), dto.getState());
    }

    @Override
    public void deleteAllGames() {
        Jedis jedis = Database.getConnection();
        Set<String> keys = jedis.keys("game-*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            jedis.del(iter.next());
        }
    }

    @Override
    public void deleteGame(int gameID) {
        Jedis jedis = Database.getConnection();
        jedis.del("game-" + gameID);
    }

}
