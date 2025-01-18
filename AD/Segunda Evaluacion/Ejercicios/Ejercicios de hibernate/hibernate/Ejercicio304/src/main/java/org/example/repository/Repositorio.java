package org.example.repository;

import java.util.List;

public interface Repositorio <T>{
    void insertarUno(T t);
    void borrar(T t);
    List<T> encontrarTodos();
    T encontrarUnoPorString(String nombre);
    void actualizar(T t);

}
