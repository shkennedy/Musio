package com.sbu.webspotify.repo;

import java.util.Set;

import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.model.Artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends JpaRepository<Artist, Integer>
{
    Artist findByName(String name);
    Artist findById(int id);

    
    @Query("SELECT a.id as id, a.name as name FROM Artist a WHERE a.name LIKE CONCAT('%', :queryString, '%')")
    Set<ArtistIdentifier> findByNameContaining(@Param("queryString") String queryString);
}