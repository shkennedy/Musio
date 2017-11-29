package com.sbu.webspotify.repo;

import java.util.Set;

import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.model.Song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SongRepository extends JpaRepository<Song, Integer>
{
    Song findById(int id);

    @Query("SELECT s.id as id, s.title as title FROM Song s WHERE s.title LIKE CONCAT('%', :queryString, '%')")
    Set<SongIdentifier> findByNameContaining(@Param("queryString") String queryString);

    @Query(value = "SELECT s.id as id, s.title as title FROM user_favorite_song ufs, song s WHERE ufs.song_id = s.id and ufs.user_id = :userId ORDER BY timestamp DESC LIMIT :numElements", nativeQuery = true)    
	Set<SongIdentifier> findRecentlyFavoritedSongsByUser(@Param("userId") int userId, @Param("numElements") int numElements);
}