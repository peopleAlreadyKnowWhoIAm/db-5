package com.iot.project.exceptions;

public class ResourceNotFoundException extends Error {

    public ResourceNotFoundException(String table, Integer id) {
        super(
                String.format("The resource with id=%d not found in table: %s", id, table)

        );
    }

}
