package com.fxf.demo.service.dto;

public interface BaseDTO<T> {
    T toEntity();
    void fromEntity(T entity);
}
