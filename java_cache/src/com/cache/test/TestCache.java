package com.cache.test;

import com.cache.dao.CountryDAO;
import com.cache.dao.CurrencyDAO;
import com.cache.dao.Dao;
import com.cache.dao.HolidayDAO;
import com.cache.manager.CacheManager;
import com.cache.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

/*
 *
 * Contains test cases for cache manager
 *  @author Ricardo Marcondes
 * */
public class TestCache {

    private final static CacheManager cacheManager = CacheManager.getInstance();


    public static void search(String tableName, Dao dao){


        Set<Cacheable> searchResult = new HashSet<Cacheable>();

        IntStream.range(0,5).forEach(i ->{

            Cacheable cacheable = (Cacheable)cacheManager.getObject(tableName, i);

            //If not found search using DAO
            if(null == cacheable){
                System.out.println("Object not found in com.techmahindra.cache: table=" + tableName + "|key=" + i);
                System.out.println("Search using DAO");
                Optional<Cacheable> optional = dao.get(i);
                optional.ifPresent(cacheable1 -> {
                    //Updates cache
                    System.out.println("Updating cache");
                    cacheManager.putObject(tableName, cacheable1);
                    //Updates result
                    searchResult.add(cacheable1);
                });
            }else{
                System.out.println("Object found in cache: " + cacheable);
            }
        }
        );

    }

    public static void loadCountries(){

        System.out.println("loadCountries");
        cacheManager.putObject("REF_COUNTRY", new Country(0, "BR", "Brazil", (short) 0, new Date(), 0));
        cacheManager.putObject("REF_COUNTRY", new Country(1, "CA", "Canada", (short) 0, new Date(), 0));
        cacheManager.putObject("REF_COUNTRY",  new Country(2, "US", "United States", (short) 0, new Date(), 0));
        cacheManager.putObject("REF_COUNTRY",  new Country(3, "DE", "Germany", (short) 0, new Date(), 0));
        cacheManager.putObject("REF_COUNTRY",  new Country(4, "JP", "Japan", (short) 0, new Date(), 0));

    }

    public static void loadCurrencies(){

        System.out.println("loadCurrencies");
        cacheManager.putObject("REF_CURRENCY", new Currency(0, "BRL", "Real", (short) 0, new Date(), 0, "R$"));
        cacheManager.putObject("REF_CURRENCY", new Currency(1, "CAD", "Canadian Dollar", (short) 0, new Date(), 0, "$"));
        cacheManager.putObject("REF_CURRENCY", new Currency(2, "USD", "United States", (short) 0, new Date(), 0, "$"));
        cacheManager.putObject("REF_CURRENCY", new Currency(3, "EUR", "Euro", (short) 0, new Date(), 0,"€"));
        cacheManager.putObject("REF_CURRENCY", new Currency(4, "JPY", "Yen", (short) 0, new Date(), 0, "¥ "));

    }

    public static void loadHolidays(){

        System.out.println("loadHolidays");
        cacheManager.putObject("REF_HOLIDAY", new Holiday(0, "XS", "Christimas", (short) 0, new Date(), 0, new Date()));
        cacheManager.putObject("REF_HOLIDAY", new Holiday(1, "NYD", "New Years Day", (short) 0, new Date(), 0, new Date()));
        cacheManager.putObject("REF_HOLIDAY", new Holiday(2, "EA", "Easter", (short) 0, new Date(), 0, new Date()));
        cacheManager.putObject("REF_HOLIDAY", new Holiday(3, "ID", "Independence Day", (short) 0, new Date(), 0, new Date()));
        cacheManager.putObject("REF_HOLIDAY", new Holiday(4, "CD", "Canadian Day", (short) 0, new Date(), 0, new Date()));

    }


    /*
     *
     * Test case where the cache is empty initially and after some searches by the DAOs, the cache in updated.
     * A new search is started and this time the object are retrieved by the cache instead of DAOs
     *
     */
    public static void testCacheInitiallyEmpty(){

        System.out.println("\n\nReset current cache\n\n");
        TestCache.cacheManager.reset();

        System.out.println("\n\nCache current content: \n\n" + cacheManager.toString() + "\n\n");

        System.out.println("Initial search with cache empty\n\n");

        TestCache.search("REF_COUNTRY", new CountryDAO());
        TestCache.search("REF_CURRENCY", new CurrencyDAO());
        TestCache.search("REF_HOLIDAY", new HolidayDAO());

        System.out.println("\n\nCache current content: \n\n" + cacheManager.toString() + "\n\n");

        System.out.println("New search with cache loaded from previous results\n\n");

        TestCache.search("REF_COUNTRY", new CountryDAO());
        TestCache.search("REF_CURRENCY", new CurrencyDAO());
        TestCache.search("REF_HOLIDAY", new HolidayDAO());
    }

    /*
     *
     * Test case where the cache is empty initially and after some searches by the DAOs, the cache in updated.
     * A new search is started and this time the object are retrieved by the cache instead of DAOs
     *
     */
    public static void testCacheInitiallyLoaded(){

        TestCache.cacheManager.reset();

        TestCache.loadCountries();
        TestCache.loadCurrencies();
        TestCache.loadHolidays();

        System.out.println("\n\nCache current content: \n\n" + cacheManager.toString() + "\n\n");
        System.out.println("Starting search\n\n");

        TestCache.search("REF_COUNTRY", new CountryDAO());
        TestCache.search("REF_CURRENCY", new CurrencyDAO());
        TestCache.search("REF_HOLIDAY", new HolidayDAO());

    }

    /*
     *
     * Test case where the cache is empty initially and after some searches by the DAOs, the cache in updated.
     * A new search is started and this time the object are retrieved by the cache instead of DAOs
     *
     */
    public static void testInvalidTableNameInCache(){

        TestCache.cacheManager.reset();

        TestCache.cacheManager.putObject(null, new Country());

    }

    /*
     *
     * Test case where the cache is empty initially and after some searches by the DAOs, the cache in updated.
     * A new search is started and this time the object are retrieved by the cache instead of DAOs
     *
     */
    public static void testInvalidObjectInCache(){

        TestCache.cacheManager.reset();

        TestCache.cacheManager.putObject("tableName", null);
    }

    public static void main(String[] args) {


        //TestCache search with cache empty
        System.out.println("Initial search with cache empty");

        TestCache.testCacheInitiallyEmpty();

        System.out.println("Cache current content:" + cacheManager.toString() + "\n\n");

        System.out.println("New test with cache loaded");
        TestCache.testCacheInitiallyLoaded();

        //System.out.println("testInvalidTableNameInCache");
        //TestCache.testInvalidTableNameInCache();

        //System.out.println("testInvalidObjectInCache");
        //TestCache.testInvalidObjectInCache();

        System.out.println("\n\nFinish Test");
    }
}
