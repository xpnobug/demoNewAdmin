package com.newadmin.demoservice.DesignPattern.singletonPattern.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 使用单例模式实现缓存系统

/**
 * 缓存系统涉及到多个方面，包括缓存数据的存储、数据的过期策略、并发控制等
 *
 * @param <K>
 * @param <V>
 */
public class SimpleCache<K, V> implements Cache<K, V> {

    // 单例实例
    private static SimpleCache instance;
    // 存储缓存数据的Map
    private final Map<K, CacheEntry<V>> cache;

    // 私有构造函数，防止外部实例化
    private SimpleCache() {
        cache = new ConcurrentHashMap<>();
        startCleanerThread(); // 启动清理线程
    }

    // 获取单例实例的方法
    public static synchronized SimpleCache getInstance() {
        if (instance == null) {
            instance = new SimpleCache<>();
        }
        return instance;
    }

    // 向缓存中添加数据，并指定数据的生存时间（毫秒）
    @Override
    public void put(K key, V value, long ttl) {
        long expiryTime = System.currentTimeMillis() + ttl; // 计算数据的过期时间
        cache.put(key, new CacheEntry<>(value, expiryTime));
    }

    // 从缓存中获取数据
    @Override
    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry != null && !entry.isExpired()) { // 检查数据是否过期
            return entry.getValue();
        }
        cache.remove(key); // 如果数据过期，则从缓存中移除
        return null;
    }

    // 从缓存中移除数据
    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    // 清空缓存
    @Override
    public void clear() {
        cache.clear();
    }

    // 获取缓存中的数据条数
    @Override
    public int size() {
        return cache.size();
    }

    // 启动清理线程，定期清理过期数据
    private void startCleanerThread() {
        Thread cleanerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000); // 清理间隔，60秒
                    cleanUp(); // 清理过期数据
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleanerThread.setDaemon(true); // 设置为守护线程
        cleanerThread.start();
    }

    // 清理过期数据的方法
    private void cleanUp() {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<K, CacheEntry<V>>> iterator = cache.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<K, CacheEntry<V>> entry = iterator.next();
            if (entry.getValue().isExpired(currentTime)) { // 检查数据是否过期
                iterator.remove();
            }
        }
    }

    // 内部类，用于存储缓存数据和过期时间
    private static class CacheEntry<V> {

        private final V value;
        private final long expiryTime;

        CacheEntry(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }

        V getValue() {
            return value;
        }

        boolean isExpired(long currentTime) {
            return currentTime > expiryTime;
        }

        boolean isExpired() {
            return isExpired(System.currentTimeMillis());
        }
    }
}
