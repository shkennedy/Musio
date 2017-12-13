package com.sbu.webspotify.repo;

import com.sbu.webspotify.model.Advertisement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Create, Read, Update, Delete -- CRUD
public interface AdRepository extends JpaRepository<Advertisement, Integer>
{
    @Query(value = "SELECT * FROM advertisement ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Advertisement findRandomAd();
    
}