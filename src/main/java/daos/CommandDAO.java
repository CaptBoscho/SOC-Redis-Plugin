package daos;

import server.persistence.Database;
import redis.clients.jedis.Jedis;
import server.persistence.dto.CommandDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Joel Bradley
 */
public class CommandDAO implements ICommandDAO {

    @Override
    public void addCommand(CommandDTO dto) {
        Jedis jedis = Database.getConnection();
        jedis.rpush("comm-" + dto.getGameID(), dto.getCommand());
    }

    @Override
    public List<CommandDTO> getCommands(int gameID) {
        Jedis jedis = Database.getConnection();
        ArrayList<CommandDTO> commands = new ArrayList<>();
        List<String> result = jedis.lrange("comm-" + gameID, 0, -1);
        for(String command : result) {
            CommandDTO dto = new CommandDTO();
            dto.setGameID(gameID);
            dto.setCommand(command);
            commands.add(dto);
        }
        return commands;
    }

    @Override
    public List<CommandDTO> getAllCommands() {
        Jedis jedis = Database.getConnection();
        ArrayList<CommandDTO> commands = new ArrayList<>();
        Set<String> keys = jedis.keys("comm-*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            key = key.substring(5, key.length());
            for(CommandDTO command : getCommands(Integer.parseInt(key))) {
                commands.add(command);
            }
        }
        return commands;
    }

    @Override
    public void deleteAllCommands() {
        Jedis jedis = Database.getConnection();
        Set<String> keys = jedis.keys("comm-*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            jedis.del(iter.next());
        }
    }

    @Override
    public void deleteCommandsFromGame(int gameID) {
        Jedis jedis = Database.getConnection();
        jedis.del("comm-" + gameID);
    }
}
