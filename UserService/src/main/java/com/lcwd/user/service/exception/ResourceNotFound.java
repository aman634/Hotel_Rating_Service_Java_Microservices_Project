package com.lcwd.user.service.exception;

public class ResourceNotFound extends Exception{
   public ResourceNotFound(){
        super("Resource not found");
    }

    public ResourceNotFound(String message){
       super(message);
    }
}
