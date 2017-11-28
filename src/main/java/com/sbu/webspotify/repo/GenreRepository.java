package com.sbu.webspotify.repo;

import com.sbu.webspotify.dto.identifier.GenreIdentifier;
import com.sbu.webspotify.model.Genre;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<Genre, Integer>
{
    @Query("SELECT g.id as id, g.name as name FROM Genre g WHERE g.name LIKE CONCAT('%', :queryString, '%')")
    Set<GenreIdentifier> findByNameContaining(@Param("queryString") String queryString);

    Genre findById(int id);
}