package com.newadmin.demoservice.DesignPattern.singletonPattern.cache;

// 定义缓存系统的接口，提供基本的缓存操作方法
public interface Cache<K, V> {

    // 向缓存中添加数据，并指定数据的生存时间（毫秒）
    void put(K key, V value, long ttl); // ttl: time to live in milliseconds

    // 从缓存中获取数据
    V get(K key);

    // 从缓存中移除数据
    void remove(K key);

    // 清空缓存
    void clear();

    // 获取缓存中的数据条数
    int size();
}
