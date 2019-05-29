package com.cache.dao;

import java.util.*;
import java.util.stream.Collectors;

import com.cache.model.Currency;
/*
 * @author Ricardo Marcondes
 */
public class CurrencyDAO implements Dao<Currency>{

    //Simulates content from DB
    private Map<Integer, Currency> currencies;

    public CurrencyDAO() {

        currencies = new HashMap<Integer, Currency>();
        currencies.put(0, new Currency(0, "BRL", "Real", (short) 0, new Date(), 0, "R$"));
        currencies.put(1, new Currency(1, "CAD", "Canadian Dollar", (short) 0, new Date(), 0, "$"));
        currencies.put(2, new Currency(2, "USD", "United States", (short) 0, new Date(), 0, "$"));
        currencies.put(3, new Currency(3, "EUR", "Euro", (short) 0, new Date(), 0,"€"));
        currencies.put(4, new Currency(4, "JPY", "Yen", (short) 0, new Date(), 0, "¥ "));
    }

    @Override
    public Optional<Currency> get(int id) {
        return Optional.ofNullable(currencies.get(id));
    }

    @Override
    public Collection<Currency> getAll() {
        return currencies.values().stream().collect(Collectors.toList());
    }

    @Override
    public void save(Currency currency) {
        currencies.put(currency.getId(), currency);

    }

    @Override
    public void update(Currency currency) {

        if(currencies.containsKey(currency.getId())){
            currencies.replace(currency.getId(), currency);
        }

    }

    @Override
    public void delete(Currency currency) {
        currencies.remove(currency);
    }
}
