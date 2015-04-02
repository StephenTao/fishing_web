package com.augmentum.common.base;

public interface IBaseDao<T, K> {

    public Boolean add(T obj);

    public Boolean delete(K id);

    public Boolean update(T obj);

    public T getById(K id);

}
