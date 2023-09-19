package io.curiositycore.thecuriositycore.database.util.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutOfBoundsSqlDataParamsException extends IncorrectSqlDataParamsException{
    private String parameterBoundsComparison;
    public OutOfBoundsSqlDataParamsException(String errorMessage, String dataTypeName, int... intParams) {
        super(errorMessage);
        parameterBoundsComparison = "Data Type Bounds for " + dataTypeName
                + "are incorrect, inputted bounds parameter: ";
    }
    private String formattedParams(int... intParams){
        List<String> listParams = new ArrayList<>();
        Arrays.stream(intParams).forEach(param-> listParams.add(String.valueOf(param)));
        return String.join(", ",listParams);
    }
}
