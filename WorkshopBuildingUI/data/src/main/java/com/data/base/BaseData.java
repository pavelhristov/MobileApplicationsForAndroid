package com.data.base;

import io.reactivex.Observable;

public interface BaseData<T> {

    Observable<T[]> getAll();
    Observable<T[]> search(String pattern);
}
