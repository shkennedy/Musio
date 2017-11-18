package com.sbu.webspotify.repo;

import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.model.Album;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// Create, Read, Update, Delete -- CRUD
public interface AlbumRepository extends JpaRepository<Album, Integer>
{
    Album findByTitle(String title);
    Album findById(int id);

    @Query("SELECT a.id as id, a.title as title FROM Album a WHERE a.title LIKE CONCAT('%', :queryString, '%')")
    Set<AlbumIdentifier> findByNameContaining(@Param("queryString") String queryString);
}