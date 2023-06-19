import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import io.curiositycore.thecuriositycore.TheCuriosityCore;

import io.curiositycore.thecuriositycore.testclasses.testCommand.PrimaryPrimaryCommandAManager;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.PrimaryExecutableA;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

//TODO write the javadocs.
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
        public void primaryCommandCheck() {
            PrimaryExecutableA primaryExecuteableA = new PrimaryExecutableA();
            boolean result = player.performCommand("primary_command_a primary_executable_a");


            assertFalse(result);
            assertTrue(player.nextMessage().contains(primaryExecuteableA.getSyntax()));
        }
        @Test
        public void primaryCommandTabsCheck(){
            PrimaryPrimaryCommandAManager primaryPrimaryCommandAManager = new PrimaryPrimaryCommandAManager();
            List<String> expectedTabStrings = new ArrayList<>();
            expectedTabStrings.add("sub_executable_a");
            expectedTabStrings.add("sub_executable_b");
            List<String> tabs = server.
                                getCommandTabComplete(player,"primary_command_a primary_executable_a ");
            assertFalse(tabs.contains("Player1"));
            assertEquals( tabs, expectedTabStrings);
        }

        @Test
        public void subExecutionACheck() {

            boolean result = player.performCommand("primary_command_a primary_executable_a sub_executable_a");



            assertTrue(result);
            assertTrue(player.nextMessage().contains("Sub-Executable-A executed via command by " + player.getName()));
        }
        @Test
        public void subExecutionBCheck() {

            boolean result = player.performCommand("primary_command_a primary_executable_a sub_executable_b");


            assertTrue(result);
            assertTrue(player.nextMessage().contains("Sub-Executable-B executed via command by " + player.getName()));
        }
        //TODO tests to check args works
        //TODO tests to ensure too many args doesnt default to first applicable args (e.g: if 3 args, it wont default_
        //      _to the second arg if it works.

    }
}
