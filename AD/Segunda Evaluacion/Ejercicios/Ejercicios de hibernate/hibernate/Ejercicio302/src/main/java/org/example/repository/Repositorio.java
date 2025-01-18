package org.example.repository;

import org.example.entity.Emp;

public interface Repositorio<T> {
    void guardar(T t);
}