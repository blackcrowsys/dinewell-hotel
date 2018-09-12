package com.blackcrowsys.dinewell.hotel.common.service;

import io.vavr.control.Option;

public interface CachingService<S, K, V> {

    void put(K k, V v);

    Option<V> get(K k);

    Option<K> getKey(S s);
}
