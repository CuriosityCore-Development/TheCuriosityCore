package io.curiositycore.thecuriositycore.database.mysql.queries;

import com.google.errorprone.annotations.Var;

public enum SqlDataTypes implements SqlDataType{
    CHAR("CHAR(%s)",true,false),
    VARCHAR("VARCHAR(%s)",true,false),
    BINARY("BINARY(%s)",true,false),
    VARBINARY("VARBINARY(%s)",true,false),
    TINYBLOB("TINYBLOB",false,false),
    TINYTEXT("TINYTEXT",false,false),
    TEXT("TEXT(%s)",true,false),
    BLOB("BLOB(%s)",true,false),
    MEDIUMTEXT("MEDIUMTEXT",false,false),
    MEDIUMBLOB("MEDIUMBLOB",false,false),
    LONGTEXT("LONGTEXT",false,false),
    LONGBLOB("LONGBLOB",false,false),
    BIT("BIT(%s)",true,false),
    TINYINT("TINYINT(%s)",true,false),
    BOOL("BOOL",false,false),
    BOOLEAN("BOOLEAN",false,false),
    SMALLINT("SMALLINT(%s)",true,false),
    MEDIUMINT("MEDIUMINT(%s)",true,false),
    INT("INT(%s)",true,false),
    INTEGER("INTEGER(%s)",true,false),
    BIGINT("BIGINT(%s)",true,false),
    FLOAT("FLOAT(%s)",false,true),
    DOUBLE("DOUBLE(%s)",false, true),
    DECIMAL("DECIMAL(%s)",false,true),
    DATETIME("DATETIME(%s)",true,false),
    TIMESTAMP("TIMESTAMP(%s)",true,false),
    TIME("TIME(%s)",true,false),
    YEAR("YEAR(%s)",false,false);

    private String dataTypeString;
    boolean hasParameters;
    boolean hasMultiParameters;

    SqlDataTypes(String dataTypeString, boolean hasParameters, boolean hasMultiParameters){
        this.dataTypeString = dataTypeString;
        this.hasParameters = hasParameters;
        this.hasMultiParameters = hasMultiParameters;
    }
    public String getDataType(){
        if(hasParameters || hasMultiParameters){
            throw new IllegalArgumentException("The "+ this.dataTypeString +" data type has parameter requirements");
        }
        return this.dataTypeString;
    }
    public String getDataType(int sizeOrFractionalSecondPrecision){
        if(!hasParameters){
            throw new IllegalArgumentException("The "+ this.dataTypeString +" does not require only size/fsp parameter.");
        }
        return String.format(this.dataTypeString,sizeOrFractionalSecondPrecision);
    }
    public String getDataType(int size, int decimals){
        if(!hasMultiParameters){
            throw new IllegalArgumentException("The "+ this.dataTypeString +" does not require multiple parameters.");
        }
        return String.format(this.dataTypeString,(size+", "+decimals));
    }

}
