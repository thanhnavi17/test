package com.ib.service;

import java.util.List;

public interface ActionItf<T, idT> {
    List<T> getAll();

    T getInfo(idT ma);

    boolean add(T obj);

    boolean update(T obj);

    boolean delete(idT ma);
}
