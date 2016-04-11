
import daos.GameDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.persistence.Database;
import server.persistence.dto.GameDTO;


import static org.junit.Assert.assertEquals;

/**
 * @author Derek Argueta
 */
public class GameTests {

    @Before
    public void setup() {
        // make sure we're using the test environment
        Database.getInstance().setTestDb();
        Database.getInstance().clear();
    }

    @After
    public void tearDown () {
        Database.getInstance().clear();
    }

    @Test
    public void testAddingGame() {
        // validate environment is empty
        assertEquals(0, new GameDAO().getAllGames().size());

        // insert a new game
        GameDTO dto = new GameDTO(1, "title", "state");
        new GameDAO().addGameObject(dto);

        // validate game got added
        assertEquals(1, new GameDAO().getAllGames().size());

        // insert a new game
        dto = new GameDTO(2, "another title", "state");
        new GameDAO().addGameObject(dto);

        // validate game got added
        assertEquals(2, new GameDAO().getAllGames().size());

        // insert a new game
        dto = new GameDTO(45, "hey another title", "state");
        new GameDAO().addGameObject(dto);

        // validate game got added
        assertEquals(3, new GameDAO().getAllGames().size());

        // ----- validate the data

        GameDTO result = new GameDAO().getGameModel(1);
        assertEquals(1, result.getGameID());
        assertEquals("title", result.getTitle());
        assertEquals("state", result.getState());
        result = new GameDAO().getGameModel(2);
        assertEquals(2, result.getGameID());
        assertEquals("another title", result.getTitle());
        assertEquals("state", result.getState());
        result = new GameDAO().getGameModel(45);
        assertEquals(45, result.getGameID());
        assertEquals("hey another title", result.getTitle());
        assertEquals("state", result.getState());
    }

    @Test
    public void testUpdatingGame() {
        // validate environment is empty
        assertEquals(0, new GameDAO().getAllGames().size());

        // insert a new game
        GameDTO dto = new GameDTO(1, "title", "state");
        new GameDAO().addGameObject(dto);

        // validate game got added
        assertEquals(1, new GameDAO().getAllGames().size());

        // try updating state
        dto = new GameDTO(1, "title", "ayyeee new state");
        new GameDAO().updateGame(dto);

        // validate game count is the same
        assertEquals(1, new GameDAO().getAllGames().size());

        // validate changes worked
        GameDTO result = new GameDAO().getGameModel(1);
        assertEquals(1, result.getGameID());
        assertEquals("title", result.getTitle());
        assertEquals("ayyeee new state", result.getState());

        // change the game state again
        dto = new GameDTO(1, "title", "a third state!!");
        new GameDAO().updateGame(dto);
        assertEquals(1, new GameDAO().getAllGames().size());
        result = new GameDAO().getGameModel(1);
        assertEquals(1, result.getGameID());
        assertEquals("title", result.getTitle());
        assertEquals("a third state!!", result.getState());
    }
}
