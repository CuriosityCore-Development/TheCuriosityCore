import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import io.curiositycore.thecuriositycore.TheCuriosityCore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.Assert.assertTrue;

public class CommandTests {
    public static class CommandTest {

        private ServerMock server;
        private PlayerMock player;
        private TheCuriosityCore curiosityCore;

        @BeforeEach
        public void setUp() {
            server = MockBukkit.mock();
            curiosityCore = MockBukkit.load(TheCuriosityCore.class);
            player = server.addPlayer("Player1");
        }

        @AfterEach
        public void tearDown() {
            MockBukkit.unmock();
        }

        @Test
        public void testGreetCommand() {


            boolean result = player.performCommand("messages greet ");


            assertTrue(result);
            assertTrue(player.nextMessage().contains("Hello, " + player.getName()));
        }

        @Test
        public void testSayGoodbyeCommand() {

            boolean result = player.performCommand("messages greet goodbye");


            assertTrue(result);
            assertTrue(player.nextMessage().contains("Goodbye, " + player.getName()));
        }
        @Test
        public void testSalutations() {

            boolean result = player.performCommand("messages greet salutations");


            assertTrue(result);
            assertTrue(player.nextMessage().contains("Salutations, " + player.getName()));
        }
        //TODO tests to check args works
        //TODO tests to ensure too many args doesnt default to first applicable args (e.g: if 3 args, it wont default_
        //      _to the second arg if it works.
        //TODO tests to properly test if commands and sub sub commands work (these examples are not clear as to which_
        //      _is which.
    }
}
