package com.akerke.ecommerceapi.common.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, Object id) {
        super("Entity %s with id %s not found".formatted( entityClass.getSimpleName(), id));
    }

    public EntityNotFoundException(Class<?> entityClass, String fieldName, Object value) {
        super("Entity %s with %s %s not found".formatted(entityClass.getSimpleName(), fieldName, value));
    }
}