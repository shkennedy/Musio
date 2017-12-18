package com.sbu.webspotify.repo;

import java.util.Set;
import javax.transaction.Transactional;
import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.model.Playlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>
{
    Playlist findById(int id);

    @Query(value = "SELECT p.image_file_id as artworkFileId, p.id as id, p.name as name FROM Playlist p WHERE p.name LIKE CONCAT('%', :queryString, '%')", nativeQuery=true)
    Set<PlaylistIdentifier> findByNameContaining(@Param("queryString") String queryString);

    @Query(value = "SELECT p.image_file_id as artworkFileId, p.id as id, p.name as name FROM user_favorite_playlist ufp, playlist p WHERE ufp.playlist_id = p.id and ufp.user_id = :userId ORDER BY timestamp DESC LIMIT :numElements", nativeQuery = true)    
	Set<PlaylistIdentifier> findRecentlyFavoritedPlaylistsByUser(@Param("userId") int userId, @Param("numElements") int numElements);
    
    @Modifying
	@Query(value = "DELETE from playlist where id = :playlistId", nativeQuery = true)
	@Transactional
	void deleteByPlaylistId(@Param("playlistId") int playlistId);

    @Query(value = "SELECT p.image_file_id as artworkFileId, p.id as id, p.name as name FROM playlist p where p.is_private = 0 AND p.owner_id = :userId", nativeQuery = true)        
	Set<PlaylistIdentifier> getPublicPlaylistsForUser(@Param("userId") int userId);
}