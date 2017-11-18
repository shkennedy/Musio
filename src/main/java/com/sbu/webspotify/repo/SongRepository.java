package com.sbu.webspotify.repo;

import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.model.Song;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SongRepository extends JpaRepository<Song, Integer>
{
    Song findById(int id);

    @Query("SELECT s.id as id, s.title as title FROM Song s WHERE s.title LIKE CONCAT('%', :queryString, '%')")
    Set<SongIdentifier> findByNameContaining(@Param("queryString") String queryString);
}