package com.cache.dao;

import java.util.*;
import java.util.stream.Collectors;

import com.cache.model.Holiday;
/*
 * @author Ricardo Marcondes
 */
public class HolidayDAO implements Dao<Holiday>{

    //Simulates content from DB
    private Map<Integer, Holiday> countries;

    public HolidayDAO() {

        countries = new HashMap<Integer, Holiday>();
        countries.put(0, new Holiday(0, "CX", "Christimas", (short) 0, new Date(), 0, new Date()));
        countries.put(1, new Holiday(1, "NY", "New Year", (short) 0, new Date(), 0, new Date()));
        countries.put(2, new Holiday(2, "EA", "Easter", (short) 0, new Date(), 0, new Date()));
        countries.put(3, new Holiday(3, "ID", "Independence Day", (short) 0, new Date(), 0, new Date()));
        countries.put(4, new Holiday(4, "CD", "Canadian Day", (short) 0, new Date(), 0, new Date()));
    }

    @Override
    public Optional<Holiday> get(int id) {
        return Optional.ofNullable(countries.get(id));
    }

    @Override
    public Collection<Holiday> getAll() {
        return countries.values().stream().collect(Collectors.toList());
    }

    @Override
    public void save(Holiday holiday) {
        countries.put(holiday.getId(), holiday);

    }

    @Override
    public void update(Holiday holiday) {

        if(countries.containsKey(holiday.getId())){
            countries.replace(holiday.getId(), holiday);
        }

    }

    @Override
    public void delete(Holiday holiday) {

    }
}
