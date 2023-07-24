package io.curiositycore.thecuriositycore.database.mysql.queries;

import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Year;

/**
 * Enum that defines the various data types that can utilised within an SQL database. The types can have one, two or no
 * parameters to define the details of their datatype (e.g: The maximum number of characters within a CHAR data type).
 */
public enum SqlDataTypes {
    /**
     * A FIXED length string (can contain letters, numbers, and special characters).
     * The size parameter specifies the column length in characters - can be from 0 to 255. Default is 1.
     */
    CHAR("CHAR(%s)",true,false, String.class),

    /**
     * 	A VARIABLE length string (can contain letters, numbers, and special characters).
     * 	The size parameter specifies the maximum string length in characters - can be from 0 to 65535
     */
    VARCHAR("VARCHAR(%s)",true,false, String.class),

    /**
     * 	Equal to CHAR(), but stores binary byte strings. The size parameter specifies the column length in bytes.
     * 	Default is 1
     */
    BINARY("BINARY(%s)",true,false, String.class),

    /**
     * Equal to VARCHAR(), but stores binary byte strings. The size parameter specifies the maximum column length in
     * bytes.
     */
    VARBINARY("VARBINARY(%s)",true,false, String.class),

    /**
     * For BLOBs (Binary Large Objects). Max length: 255 bytes
     */
    TINYBLOB("TINYBLOB",false,false, String.class),

    /**
     * Holds a string with a maximum length of 255 characters
     */
    TINYTEXT("TINYTEXT",false,false, String.class),

    /**
     * Holds a string with a maximum length of 65,535 bytes
     */
    TEXT("TEXT(%s)",true,false, String.class),

    /**
     * For BLOBs (Binary Large Objects). Holds up to 65,535 bytes of data
     */
    BLOB("BLOB(%s)",true,false, String.class),

    /**
     * Holds a string with a maximum length of 16,777,215 characters
     */
    MEDIUMTEXT("MEDIUMTEXT",false,false, String.class),

    /**
     * For BLOBs (Binary Large Objects). Holds up to 16,777,215 bytes of data
     */
    MEDIUMBLOB("MEDIUMBLOB",false,false, String.class),

    /**
     * Holds a string with a maximum length of 4,294,967,295 characters
     */
    LONGTEXT("LONGTEXT",false,false, String.class),

    /**
     * For BLOBs (Binary Large Objects). Holds up to 4,294,967,295 bytes of data
     */
    LONGBLOB("LONGBLOB",false,false, String.class),

    /**
     * 	A bit-value type. The number of bits per value is specified in size. The size parameter can hold a value
     * 	from 1 to 64. The default value for size is 1.
     */
    BIT("BIT(%s)",true,false, Boolean.class),

    /**
     * 	A very small integer. Signed range is from -128 to 127. Unsigned range is from 0 to 255.
     * 	The size parameter specifies the maximum display width (which is 255).
     */
    TINYINT("TINYINT(%s)",true,false, Integer.class),

    /**
     * Zero is considered as false, nonzero values are considered as true.
     */
    BOOL("BOOL",false,false, Boolean.class),

    /**
     * Equal to BOOL.
     */
    BOOLEAN("BOOLEAN",false,false, Boolean.class),

    /**
     * 	A small integer. Signed range is from -32768 to 32767. Unsigned range is from 0 to 65535. The size parameter
     * 	specifies the maximum display width (which is 255).
     */
    SMALLINT("SMALLINT(%s)",true,false, Integer.class),
    /**
     * 	A medium integer. Signed range is from -8388608 to 8388607. Unsigned range is from 0 to 16777215. The size
     * 	parameter specifies the maximum display width (which is 255).
     */
    MEDIUMINT("MEDIUMINT(%s)",true,false, Integer.class),

    /**
     * 	A medium integer. Signed range is from -2147483648 to 2147483647. Unsigned range is from 0 to 4294967295. The
     * 	size parameter specifies the maximum display width (which is 255)
     */
    INT("INT(%s)",true,false, Integer.class),
    /**
     * Equal to INT(size)
     */
    INTEGER("INTEGER(%s)",true,false, Integer.class),

    /**
     * 	A large integer. Signed range is from -9223372036854775808 to 9223372036854775807. Unsigned range is from 0 to
     * 	18446744073709551615. The size parameter specifies the maximum display width (which is 255)
     */
    BIGINT("BIGINT(%s)",true,false, Integer.class),

    /**
     * 	A floating point number. MySQL uses the p value to determine whether to use FLOAT or DOUBLE for the resulting
     * 	data type. If p is from 0 to 24, the data type becomes FLOAT(). If p is from 25 to 53, the data type becomes DOUBLE()
     */
    FLOAT("FLOAT(%s)",false,true, Float.class),

    /**
     * 	A normal-size floating point number. The total number of digits is specified in size. The number of digits after
     * 	the decimal point is specified in the d parameter
     */
    DOUBLE("DOUBLE(%s)",false, true, Double.class),

    /**
     * 	An exact fixed-point number. The total number of digits is specified in size. The number of digits after the
     * 	decimal point is specified in the d parameter. The maximum number for size is 65. The maximum number for d is
     * 	30. The default value for size is 10. The default value for d is 0.
     */
    DECIMAL("DECIMAL(%s)",false,true, BigDecimal.class),

    /**
     * 	A date and time combination. Format: YYYY-MM-DD hh:mm:ss. The supported range is from '1000-01-01 00:00:00' to
     * 	'9999-12-31 23:59:59'. Adding DEFAULT and ON UPDATE in the column definition to get automatic initialization and
     * 	updating to the current date and time.
     */
    DATETIME("DATETIME(%s)",true,false, LocalDateTime.class),

    /**
     * 	A timestamp. TIMESTAMP values are stored as the number of seconds since the Unix epoch ('1970-01-01 00:00:00'
     * 	UTC). Format: YYYY-MM-DD hh:mm:ss. The supported range is from '1970-01-01 00:00:01' UTC to '2038-01-09 03:14:07'
     * 	UTC.
     */
    TIMESTAMP("TIMESTAMP(%s)",true,false, Timestamp.class),

    /**
     * A time. Format: hh:mm:ss. The supported range is from '-838:59:59' to '838:59:59'
     */
    TIME("TIME(%s)",true,false, Time.class),

    /**
     * A year in four-digit format. Values allowed in four-digit format: 1901 to 2155, and 0000.
     * MySQL 8.0 does not support year in two-digit format.
     */
    YEAR("YEAR",false,false, Year.class);

    /**
     * The string of the datatype, lacking its parameters.
     */
    private final String dataTypeString;

    /**
     * The class that the data type represents in the java framework.
     */
    @Getter
    private final Class<?> typeClass;

    /**
     * Boolean that determines if it has a single parameter.
     */
    final boolean hasParameter;

    /**
     * Boolean that determines if the data type has 2 parameters.
     */
    final boolean hasTwoParameters;

    SqlDataTypes(String dataTypeString, boolean hasParameter, boolean hasTwoParameters,Class<?> typeClass){
        this.dataTypeString = dataTypeString;
        this.typeClass = typeClass;
        this.hasParameter = hasParameter;
        this.hasTwoParameters = hasTwoParameters;
    }

    /**
     * Method for retrieving the string of the data type, for the purposes of adding to a query. This method is only
     * for data types with no parameters.
     * @return The string of a data type to be utilised in a database query.
     */
    public String getDataType(){
        if(hasParameter || hasTwoParameters){
            throw new IllegalArgumentException("The "+ this.dataTypeString +" data type has parameter requirements");
        }
        return this.dataTypeString;
    }

    /**
     * Method for retrieving the string of the data type, for the purposes of adding to a query. This method is only
     * for data types with a singular parameter.
     * @param sizeOrFractionalSecondPrecision The parameter of the data type.
     * @return The string of a data type to be utilised in a database query.
     */
    public String getDataType(int sizeOrFractionalSecondPrecision){
        if(!hasParameter){
            throw new IllegalArgumentException("The "+ this.dataTypeString +" does not require only size/fsp parameter.");
        }
        return String.format(this.dataTypeString,sizeOrFractionalSecondPrecision);
    }

    /**
     * Method for retrieving the string of the data type, for the purposes of adding to a query. This method is only
     * for data types with 2 parameters.
     * @param size The parameter that denotes the maximum size of the data type that can be inputted into the table.
     * @param decimals The amount of decimal places to utilise for the data type that can be inputted into the table.
     * @return The string of a data type to be utilised in a database query.
     */
    public String getDataType(int size, int decimals){
        if(!hasTwoParameters){
            throw new IllegalArgumentException("The "+ this.dataTypeString +" does not require multiple parameters.");
        }
        return String.format(this.dataTypeString,(size+", "+decimals));
    }

}
