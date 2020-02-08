package com.example.demo;

/**
 * Created by canerbakar on 30.04.2018.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceError extends RuntimeException {

    public ResourceError( String resourceName) {

        super(String.format("%s  ", resourceName));

    }


}
