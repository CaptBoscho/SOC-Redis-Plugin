package daos;

import server.persistence.Database;
import redis.clients.jedis.Jedis;
import server.persistence.dto.UserDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Joel Bradley
 */
public class UserDAO implements IUserDAO {

    @Override
    public void addUser(UserDTO dto) {
        Jedis jedis = Database.getConnection();
        jedis.lpush("user-" + dto.getId(), dto.getPassword());
        jedis.lpush("user-" + dto.getId(), dto.getUserName());
    }

    @Override
    public List<UserDTO> getUsers() {
        Jedis jedis = Database.getConnection();
        ArrayList<UserDTO> users = new ArrayList<>();
        Set<String> keys = jedis.keys("user-*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            key = key.substring(5, key.length());
            List<String> result = jedis.lrange("user-" + key, 0, 1);
            UserDTO dto = new UserDTO();
            dto.setId(Integer.parseInt(key));
            dto.setUserName(result.get(0));
            dto.setPassword(result.get(1));
            users.add(dto);
        }
        return users;
    }

    @Override
    public void deleteUsers() {
        Jedis jedis = Database.getConnection();
        Set<String> keys = jedis.keys("user-*");
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            jedis.del(iter.next());
        }
    }
}
