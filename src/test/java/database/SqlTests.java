package database;
import database.table.DatasourceTest;
import database.table.TestTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class SqlTests {
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

        //----------------------------------------------
        Assert.assertEquals(3,testTable.getColumnsInTable().length);
        Assert.assertEquals("TestChar",testTable.getColumnsInTable()[0].getColumnName());
        Assert.assertEquals(0,testTable.getCurrentRows());
    }

    @Test
    public void createAndDeleteRow(){
        TestTable testTable = new TestTable(this.dataSource,"CreationTestTable");
        Object[] newRowObject = new Object[]{"TestCharVal",10,false};

        testTable.insertRow(newRowObject);
        testTable.updateTableInDataBase();

        int createTest = testTable.getCurrentRows();
        testTable.deleteRow(testTable.getRowList().get(0).getRowIndex());
        int deleteTest = testTable.getCurrentRows();

        //----------------------------------------------
        Assert.assertEquals(1,createTest);
        Assert.assertEquals(0,deleteTest);

    }

    @Test
    public void createMultipleRowsAndUpdateTable(){
        TestTable testTable = new TestTable(this.dataSource,"CreationTestTable");
        Object[] newRowObject = new Object[]{"TestCharVal",10,false};
        Object[] newRowObject2 = new Object[]{"TestCharVal2",20,false};
        Object[] newRowObject3 = new Object[]{"TestCharVal3",30,false};
        Object[] newRowObject4 = new Object[]{"TestCharVal4",40,false};
        testTable.insertRow(newRowObject);
        testTable.insertRow(newRowObject2);
        testTable.insertRow(newRowObject3);
        testTable.insertRow(newRowObject4);
        testTable.updateTableInDataBase();

        int createTest = testTable.getCurrentRows();
        testTable.deleteRow(testTable.getRowList().get(0).getRowIndex());
        testTable.deleteRow(testTable.getRowList().get(0).getRowIndex());
        testTable.deleteRow(testTable.getRowList().get(0).getRowIndex());
        testTable.deleteRow(testTable.getRowList().get(0).getRowIndex());
        int deleteTest = testTable.getCurrentRows();

        //----------------------------------------------
        Assert.assertEquals(4,createTest);
        Assert.assertEquals(0,deleteTest);
    }
    @Test
    public void createRowFailure(){
        TestTable testTable = new TestTable(this.dataSource,"CreationTestTable");
        Object[] newRowObject = new Object[]{10,10,false};


        //----------------------------------------------
        Assert.assertThrows(NoSuchElementException.class,() -> {testTable.insertRow(newRowObject);});
    }

    @Test
    public void deleteRowFailure(){
        TestTable testTable = new TestTable(this.dataSource,"CreationTestTable");

        //----------------------------------------------
        Assert.assertThrows(RuntimeException.class,() -> {testTable.deleteRow(10);});
    }

    @Test
    public void getDataTest(){
        TestTable testTable = new TestTable(this.dataSource,"CreationTestTable");
        Object[] newRowObject = new Object[]{"TestCharVal",10,false};
        testTable.insertRow(newRowObject);

        //----------------------------------------------
        Assert.assertEquals(newRowObject[0],testTable.getTestChar(1));
        Assert.assertEquals(newRowObject[1],testTable.getTestInteger(1));
        Assert.assertEquals(newRowObject[2],testTable.getTestBoolean(1));
    }

    @Test
    public void updateRowData(){
        TestTable testTable = new TestTable(this.dataSource,"CreationTestTable");
        Object[] newRowObject = new Object[]{"TestCharVal",10,false};
        testTable.insertRow(newRowObject);
        testTable.updateTableInDataBase();
        Object[] updatedRowObject = new Object[]{"TestCharVal",30,false};
        testTable.updateRow(1,updatedRowObject);
        int testInt = testTable.getTestInteger(1);
        testTable.deleteRow(1);


        Assert.assertEquals(updatedRowObject[1],testInt);
    }

        }
