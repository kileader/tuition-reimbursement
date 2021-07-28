package com.kevin_leader.repositories;

import java.util.List;

public interface GenericRepo<T> {

    public int add(T entity);

    public List<T> getAll();

    public <T> T getById(int id);

    public <T> T update(T entity);

    public <T> T delete(T entity);

}
