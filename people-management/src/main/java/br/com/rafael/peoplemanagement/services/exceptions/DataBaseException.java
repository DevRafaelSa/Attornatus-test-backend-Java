package br.com.rafael.peoplemanagement.services.exceptions;

public class DataBaseException extends RuntimeException{

    public DataBaseException(String msg) {
        super(msg);
    }
}
