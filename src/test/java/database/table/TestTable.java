package database.table;

import io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes;
import io.curiositycore.thecuriositycore.database.mysql.table.BaseSqlTable;
import io.curiositycore.thecuriositycore.database.mysql.table.SqlColumn;
import io.curiositycore.thecuriositycore.database.mysql.table.SqlRow;

import javax.sql.DataSource;
import java.util.NoSuchElementException;

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
    public String getTestChar(int rowIndex){
        SqlRow rowToGetValueFrom = getRowBaseOnIndex(rowIndex);
        return getCastedValue(String.class,rowToGetValueFrom.getRowData()[0]);
    }

    public int getTestInteger(int rowIndex){
        SqlRow rowToGetValueFrom = getRowBaseOnIndex(rowIndex);
        return getCastedValue(Integer.class,rowToGetValueFrom.getRowData()[1]);
    }

    public boolean getTestBoolean(int rowIndex){
        SqlRow rowToGetValueFrom = getRowBaseOnIndex(rowIndex);
        return getCastedValue(Boolean.class,rowToGetValueFrom.getRowData()[2]);
    }


    @Override
    protected SqlColumn[] initColumns(boolean tableExistsInDatabase) {
        addColumnToTable("TestChar", SqlDataTypes.CHAR,200,tableExistsInDatabase);
        addColumnToTable("TestInteger",SqlDataTypes.INTEGER,100,tableExistsInDatabase);
        addColumnToTable("testBoolean",SqlDataTypes.BOOLEAN,tableExistsInDatabase);
        return this.columnsInTable;
    }
}
