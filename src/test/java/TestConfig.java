import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import io.curiositycore.thecuriositycore.TheCuriosityCore;
import io.curiositycore.thecuriositycore.configurations.CachedConfigManager;
import io.curiositycore.thecuriositycore.configurations.test.configs.caches.ActivityScanSettingsCacheTest;
import io.curiositycore.thecuriositycore.configurations.test.configs.caches.CustomWarCacheTest;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.ActivityScanSettings;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.CustomWarValue;
import org.bukkit.configuration.file.YamlConfiguration;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class TestConfig {
    public static class Test{
        private ServerMock server;
        /**
         * A mock instance of a <code>Player</code> instance.
         */
        private PlayerMock player;
        /**
         * A mock instance of the CuriosityCore library.
         */
        private TheCuriosityCore curiosityCore;
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

        @org.junit.jupiter.api.Test
        void BooleanTest(){
            CachedConfigManager configManager = this.curiosityCore.getConfigManager();
            Boolean testbool = configManager.getObject(ActivityScanSettingsCacheTest.class,Boolean.class, ActivityScanSettings.ENABLED);
            assertTrue(testbool);
        }
        @org.junit.jupiter.api.Test
        void IntegerTest() {
            long testTime;
            long testTime2;
            CachedConfigManager configManager = this.curiosityCore.getConfigManager();

            YamlConfiguration config = (YamlConfiguration) this.curiosityCore.getConfig();

            // Measure retrieval time for config.getInt()
            long initialTime2 = System.nanoTime();
            int configValue = config.getInt("custom_war.area_capture_radius");
            testTime2 = System.nanoTime() - initialTime2;

            // Measure retrieval time for configManager.getObject()
            long initialTime = System.nanoTime();
            Integer cachedValue = configManager.getObject(CustomWarCacheTest.class, Integer.class, CustomWarValue.AREA_CAPTURE_RADIUS);
            testTime = System.nanoTime() - initialTime;



            assertNotNull(cachedValue);

            System.out.println("Normal Spigot retrieval time in nanoSeconds:" + testTime2+", New Cache retrieval time in nanoSeconds: "+ testTime);
            assertEquals(10, (int) cachedValue);
        }
        @org.junit.jupiter.api.Test
        void IntegerTest2() {
            long testTime2;
            long testTime1;
            CachedConfigManager configManager = this.curiosityCore.getConfigManager();




            long initialTime1 = System.nanoTime();
            int influenceRequired = configManager.getObject(CustomWarCacheTest.class,
                                                                        Integer.class,
                                                                        CustomWarValue.AREA_INFLUENCE_REQUIREMENT);
            testTime1 = System.nanoTime() - initialTime1;


            long initialTime2 = System.nanoTime();
            Integer testInt = configManager.getObject(CustomWarCacheTest.class, Integer.class, CustomWarValue.AREA_INFLUENCE_REQUIREMENT);
            testTime2 = System.nanoTime() - initialTime2;
            assertNotNull(testInt);
            System.out.println("Normal Spigot retrieval time in nanoSeconds:" + testTime1+", New Cache retrieval time in nanoSeconds: "+ testTime2);

            assertEquals(30, (int) testInt);
        }

    }

}
