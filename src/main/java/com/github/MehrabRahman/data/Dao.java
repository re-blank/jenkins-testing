package com.github.MehrabRahman.data;

public interface Dao<T> {
    T get(int id);
    void save(T t);
}