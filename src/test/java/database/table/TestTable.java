package database.table;

import io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes;
import io.curiositycore.thecuriositycore.database.mysql.table.BaseSqlTable;
import io.curiositycore.thecuriositycore.database.mysql.table.SqlColumn;

import javax.sql.DataSource;

public class TestTable extends BaseSqlTable {
    /**
     * The constructor that initialises the datasource for the table.
     *
     * @param dataSourceForTable The datasource the table is to reside in.
     * @param tableName
     */
    public TestTable(DataSource dataSourceForTable, String tableName) {
        super(dataSourceForTable, tableName);
    }

    @Override
    protected SqlColumn[] initColumns(boolean tableExistsInDatabase) {
        addColumnToTable("TestChar", SqlDataTypes.CHAR,200,tableExistsInDatabase);
        addColumnToTable("TestInteger",SqlDataTypes.INTEGER,100,tableExistsInDatabase);
        addColumnToTable("testBoolean",SqlDataTypes.BOOLEAN,tableExistsInDatabase);
        return this.columnsInTable;
    }
}
