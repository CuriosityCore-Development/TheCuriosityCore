import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import io.curiositycore.thecuriositycore.TheCuriosityCore;


import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.primarya.PrimaryExecutableA;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the various parameters of the command package of this library. Includes tests for both command execution and
 * the tab-completer.
 */
public class CommandTests {

    public static class CommandTest {

        /**
         * A mock instance of a minecraft server.
         */
        private ServerMock server;
        /**
         * A mock instance of a <code>Player</code> instance.
         */
        private PlayerMock player;
        /**
         * A mock instance of the CuriosityCore library.
         */
        private TheCuriosityCore curiosityCore;

        /**
         * The setup before each test is conducted. Creates a server, loads the CurioistyCore library, and then
         * adds a mock player for testing purposes.
         */
        @BeforeEach
        public void setUp() {
            this.server = MockBukkit.mock();
            this.curiosityCore = MockBukkit.load(TheCuriosityCore.class);
            this.player = server.addPlayer("Player1");

        }

        /**
         * Tear down the mock after every test to ensure no follow through errors.
         */
        @AfterEach
        public void tearDown() {
            MockBukkit.unmock();
        }

        /**
         * Tests if the primary command A call will perform the correct response (Incorrect so sends player the syntax)
         */
        @Test
        void primaryCommandACheck() {
            PrimaryExecutableA primaryExecuteableA = new PrimaryExecutableA();
            boolean result = player.performCommand("primary_command_a primary_executable_a");


            assertFalse(result);
            assertTrue(player.nextMessage().contains(primaryExecuteableA.getSyntax()));
        }

        /**
         * Tests if the primary command B call will perform the correct response (Incorrect so sends player the syntax)
         */
        @Test
        void primaryCommandBCheck() {
            PrimaryExecutableA primaryExecutableB = new PrimaryExecutableA();
            boolean result = player.performCommand("primary_command_a primary_executable_b");


            assertFalse(result);
            assertTrue(player.nextMessage().contains(primaryExecutableB.getSyntax()));
        }

        /**
         * Tests if the primary command A calls a list of its sub-executables when only typing in the primary argument.
         */
        @Test
        void primaryCommandTabsCheck(){
            List<String> expectedTabStrings = new ArrayList<>();

            expectedTabStrings.add("primary_executable_a");
            expectedTabStrings.add("primary_executable_b");
            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a ");

            assertFalse(tabs.contains("Player1"));
            assertEquals( tabs, expectedTabStrings);
        }
        /**
         * Tests if the primary executable A will call up a list of sub_executables when the primary command / executable
         * are the only args.
         */
        @Test
        void primaryExecutableATabsCheck(){
            List<String> expectedTabStrings = new ArrayList<>();

            expectedTabStrings.add("sub_executable_a");
            expectedTabStrings.add("sub_executable_b");

            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a primary_executable_a ");

            assertFalse(tabs.contains("Player1"));
            assertEquals( expectedTabStrings, tabs);
        }
        /**
         * Tests if the primary executable B will call up a list of sub_executables when the primary command / executable
         * are the only args.
         */
        @Test
        void primaryExecutableBTabsCheck(){
            List<String> expectedTabStrings = new ArrayList<>();

            expectedTabStrings.add("sub_executable_c");
            expectedTabStrings.add("sub_executable_d");

            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a primary_executable_b ");

            assertFalse(tabs.contains("Player1"));
            assertEquals( expectedTabStrings, tabs);
        }

        /**
         * Tests to see if incomplete tabbing of a sub-executable still leads to the correct result of sub-executable
         * suggesitons for the tab-completer.
         */
        @Test
        void primaryExecuteableIncompleteTabsCheck(){
            List<String> expectedTabStrings = new ArrayList<>();

            expectedTabStrings.add("sub_executable_a");
            expectedTabStrings.add("sub_executable_b");

            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a primary_executable_a sub_ex");

            assertFalse(tabs.contains("Player1"));
            assertEquals( expectedTabStrings, tabs);
        }

        /**
         * Tests if subExecutableA actually performs the correct perform method.
         */
        @Test
        void subExecutionACheck() {
            boolean result = player.performCommand("primary_command_a primary_executable_a sub_executable_a");

            assertTrue(result);
            assertTrue(player.nextMessage().contains("Sub-Executable-A executed via command by " + player.getName()));
        }

        /**
         * Tests if the sub executable A will call up the correct tab completer (no arguments left , so 0) It is
         * important to note that this test specifies not getting a "Player1"  return value, as the idea is that you
         * get nothing if there are no more args to choose from.
         */
        @Test
        void subExecutableATabsCheck(){


            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a primary_executable_a sub_executable_a");

            assertFalse(tabs.contains("Player1"));
            assertEquals(Collections.emptyList(), tabs);
        }
        /**
         * Same as the initial subExecuteableATabsCheck, but with the addition of a space (so an extra arg)
         */
        @Test
        void subExecutableAExtraArgTabsCheck(){


            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a primary_executable_a sub_executable_a ");

            assertFalse(tabs.contains("Player1"));
            assertEquals(Collections.emptyList(), tabs);
        }
        /**
         * Tests if subExecutableB actually performs the correct perform method.
         */
        @Test
        void subExecutionBCheck() {

            boolean result = player.performCommand("primary_command_a primary_executable_a sub_executable_b");


            assertTrue(result);
            assertTrue(player.nextMessage().contains("Sub-Executable-B executed via command by " + player.getName()));
        }
        /**
         * Tests if the sub executable B will call up the correct tab completer (no arguments left , so 0) It is
         * important to note that this test specifies not getting a "Player1"  return value, as the idea is that you
         * get nothing if there are no more args to choose from.
         */
        @Test
        void subExecutableBTabsCheck(){


            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a primary_executable_a sub_executable_b");

            assertFalse(tabs.contains("Player1"));
            assertEquals(Collections.emptyList(), tabs);
        }

        /**
         * Same as the initial subExecuteableBTabsCheck, but with the addition of a space (so an extra arg)
         */
        @Test
        void subExecutableBExtraArgTabsCheck(){


            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a primary_executable_a sub_executable_b ");

            assertFalse(tabs.contains("Player1"));
            assertEquals(Collections.emptyList(), tabs);
        }

        /**
         * Test to see if completely incorrect value still result in an empty list. (as opposed to some sort
         * of exception)
         */
        @Test
        void subExecutableUtterlyIncorrect(){


            List<String> tabs = server.
                    getCommandTabComplete(player,"primary_command_a primary_executable_a potato ");

            assertFalse(tabs.contains("Player1"));
            assertEquals(Collections.emptyList(), tabs);
        }

    }
}
