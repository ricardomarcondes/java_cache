package com.cache.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.cache.model.Cacheable;
/*
*
*  Manages the cache
*  Implements the singleton design pattern in order to have a global and unique instance
*  @author Ricardo Marcondes
* */
public class CacheManager {

    private static CacheManager instance;
    private static Map<String, Map<Integer, Cacheable>> cache;

    private CacheManager(){
        cache = new ConcurrentHashMap<String, Map<Integer, Cacheable>>();
    }

    public static CacheManager getInstance(){
        if(null == instance){
            instance = new CacheManager();
        }
        return instance;
    }

    public Cacheable getObject(String tableName, Integer primaryKeyValue){
        Objects.requireNonNull(tableName, "tableName cannot be null");
        Objects.requireNonNull(primaryKeyValue, "primaryKeyValue cannot be null");

        Map<Integer, Cacheable> map = cache.get(tableName);
        return (null != map)? map.get(primaryKeyValue): null;
    }

    public void putObject(String tableName, Cacheable object){
        Objects.requireNonNull(tableName, "tableName cannot be null");
        Objects.requireNonNull(object, "object cannot be null");

        //Search for existing table cache
        Map<Integer, Cacheable> map = cache.get(tableName);

        //If exists just update the respective cache
        if(null != map){

            map.put(object.getId(), object);

        }else{//Creates a new table cache
            Map newMap = new ConcurrentHashMap<Integer, Cacheable>();
            newMap.put(object.getId(), object);
            cache.put(tableName, newMap);
        }
    }

    public void removeObject(String tableName, Cacheable object){

        Objects.requireNonNull(tableName, "tableName cannot be null");
        Objects.requireNonNull(object, "object cannot be null");


        Map<Integer, Cacheable> map = cache.get(tableName);
        if(null != map){
            map.remove(object.getId());
        }
    }

    public void removeTableCache(String tableName){

        Objects.requireNonNull(tableName, "tableName cannot be null");

        Map<Integer, Cacheable> map = cache.remove(tableName);

    }

    public void reset(){
        cache.clear();
    }

    @Override
    public String toString() {
        return cache.toString();
    }
}
