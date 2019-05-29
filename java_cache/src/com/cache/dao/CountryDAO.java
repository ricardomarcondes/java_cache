package com.cache.dao;

import java.util.*;
import java.util.stream.Collectors;

import com.cache.model.Country;
/*
 * @author Ricardo Marcondes
 */
public class CountryDAO implements Dao<Country>{

    //Simulates content from DB
    private Map<Integer, Country> countries;

    public CountryDAO() {

        countries = new HashMap<Integer, Country>();
        countries.put(0, new Country(0, "BR", "Brazil", (short) 0, new Date(), 0));
        countries.put(1, new Country(1, "CA", "Canada", (short) 0, new Date(), 0));
        countries.put(2, new Country(2, "US", "United States", (short) 0, new Date(), 0));
        countries.put(3, new Country(3, "DE", "Germany", (short) 0, new Date(), 0));
        countries.put(4, new Country(4, "JP", "Japan", (short) 0, new Date(), 0));
    }

    @Override
    public Optional<Country> get(int id) {
        return Optional.ofNullable(countries.get(id));
    }

    @Override
    public Collection<Country> getAll() {
        return countries.values().stream().collect(Collectors.toList());
    }

    @Override
    public void save(Country country) {
        countries.put(country.getId(), country);

    }

    @Override
    public void update(Country country) {

        if(countries.containsKey(country.getId())){
            countries.replace(country.getId(), country);
        }

    }

    @Override
    public void delete(Country country) {
        countries.remove(country);
    }
}
