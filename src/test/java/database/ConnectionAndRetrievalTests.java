package database;
import database.table.DatasourceTest;
import database.table.TestTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionAndRetrievalTests {
    DataSource dataSource;
    @Before
    public void conditionInit() throws SQLException {
        DatasourceTest datasourceTest = new DatasourceTest();
        this.dataSource = datasourceTest.getNewDataSource(null);
    }
    @Test
    public void connectionTest() throws SQLException {
        try(Connection connection = this.dataSource.getConnection()){
            Assert.assertTrue(connection.isValid(5));
        }
    }

    @Test
    public void createTable() throws SQLException {
        TestTable testTable = new TestTable(this.dataSource,"CreationTestTable");
        Assert.assertEquals(3,testTable.getColumnsInTable().length);
        Assert.assertEquals("TestChar",testTable.getColumnsInTable()[0].getColumnName());

    }

}
