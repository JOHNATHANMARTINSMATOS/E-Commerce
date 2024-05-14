package com.example.ECommerce.interfaces;

import java.util.List;

/**
 * CRUD-http
 * create -post
 * read-get -> lista todos
 * read - get(id) -> lista um registro
 * update- put(id,objeto),patch(id,objeto)
 * delete-delete(id)
 * https://docs.oracle.com/javase/tutorial/java/generics/types.html
 */
public interface GenericOperations<T,N> {
    T creat(T entity);
    List<T> read();
    T read(N id);
    T update(N id, T entity);
    void delete(N id);
}
