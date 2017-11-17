package com.sbu.webspotify.repo;

import java.util.Set;

import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.model.Playlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>
{
    Playlist findById(int id);

    @Query("SELECT p.id as id, p.name as name FROM Playlist p WHERE p.name LIKE CONCAT('%', :queryString, '%')")
    Set<PlaylistIdentifier> findByNameContaining(@Param("queryString") String queryString);
}