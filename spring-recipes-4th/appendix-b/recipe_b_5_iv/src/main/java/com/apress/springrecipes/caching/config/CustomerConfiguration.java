package com.apress.springrecipes.caching.config;

import com.apress.springrecipes.caching.CustomerRepository;
import com.apress.springrecipes.caching.MapBasedCustomerRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by marten on 15-08-14.
 */
@Configuration
@EnableCaching
public class CustomerConfiguration {

    @Bean
    public CacheManager cacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return cacheManager;
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return factory;
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new MapBasedCustomerRepository();
    }

}
