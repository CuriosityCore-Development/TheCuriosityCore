package database.table;
import io.curiositycore.thecuriositycore.database.*;
import io.curiositycore.thecuriositycore.database.credentials.SqlSourceInit;
import org.bukkit.configuration.file.FileConfiguration;

public class DatasourceTest extends SqlSourceInit {
    @Override
    protected String setHost(FileConfiguration configurationFile) {
        return "50.20.250.59";
    }

    @Override
    protected int setPort(FileConfiguration configurationFile) {
        return 3306;
    }

    @Override
    protected String setDatabase(FileConfiguration configurationFile) {
        return "mc196742";
    }

    @Override
    protected String setUsername(FileConfiguration configurationFile) {
        return "mc196742";
    }

    @Override
    protected String setPassword(FileConfiguration configurationFile) {
        return "e64f612736";
    }
}
