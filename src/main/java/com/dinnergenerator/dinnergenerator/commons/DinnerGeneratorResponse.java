package com.dinnergenerator.dinnergenerator.commons;

public class DinnerGeneratorResponse<T>{

    private T data;
    private String error;


    public DinnerGeneratorResponse(T data){
        this.data = data;

    }

    public DinnerGeneratorResponse(){

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
