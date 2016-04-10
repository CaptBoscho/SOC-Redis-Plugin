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
    public int addUser(UserDTO dto) {
        Jedis jedis = Database.getConnection();
        Set<String> keys = jedis.keys("user-*");
        Iterator<String> iter = keys.iterator();
        boolean userExists = false;
        ArrayList<String> ids = new ArrayList<>();
        while(iter.hasNext()) {
            String key = iter.next();
            key = key.substring(5, key.length());
            ids.add(key);
            if(Integer.parseInt(key) == dto.getId()) {
                userExists = true;
            }
        }
        if(!userExists) {
            jedis.lpush("user-" + dto.getId(), dto.getPassword());
            jedis.lpush("user-" + dto.getId(), dto.getUserName());
            return dto.getId();
        } else {
            int id = dto.getId();
            while(ids.contains(id + "")) {
                id++;
            }
            jedis.lpush("user-" + id, dto.getPassword());
            jedis.lpush("user-" + id, dto.getUserName());
            return id;
        }
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
            UserDTO dto = new UserDTO(Integer.parseInt(key), result.get(0), result.get(1));
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
