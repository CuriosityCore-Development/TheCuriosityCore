package io.curiositycore.thecuriositycore.database.util.exceptions;

public abstract class IncorrectSqlDataParamsException extends RuntimeException{
    public IncorrectSqlDataParamsException(String errorMessage){
        super(errorMessage);
    }
}
