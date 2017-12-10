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

    @Query(value = "SELECT art.id as artistId, art.name as artistName, alb.id as id, f.id as thumbnailFileId, alb.title as title "
                    + "FROM Album alb, Artist art, album_artist_mapping aam, Image i, File f "
                    + "WHERE aam.artist_id = art.id AND aam.album_id = alb.id AND alb.album_art_id = i.id AND i.thumb_file_id = f.id "
                    + "AND alb.title LIKE CONCAT('%', :queryString, '%')",
                    nativeQuery = true)
    Set<AlbumIdentifier> findByNameContaining(@Param("queryString") String queryString);

    @Query(value = "SELECT art.id as artistId, art.name as artistName, alb.id as id, f.id as thumbnailFileId, alb.title as title "
                    + "FROM user_favorite_album ufa, album alb, artist art, album_artist_mapping aam, Image i, File f "
                    + "WHERE ufa.album_id = alb.id AND ufa.user_id = :userId AND aam.artist_id = art.id AND aam.album_id = alb.id AND alb.album_art_id = i.id AND i.thumb_file_id = f.id "
                    + "ORDER BY timestamp DESC LIMIT :numElements",
                    nativeQuery = true)    
    Set<AlbumIdentifier> findRecentlyFavoritedAlbumsByUser(@Param("userId") int userId, @Param("numElements") int numElements);
    
    @Query(value = "SELECT art.id as artistId, art.name as artistName, alb.id as id, alb.title as title "
                    + "FROM Album alb, Artist art, album_artist_mapping aam "
                    + "WHERE aam.artist_id = :artistId AND aam.artist_id = art.id AND aam.album_id = alb.id",
                    nativeQuery = true)
    Set<AlbumIdentifier> findAllByArtistId(@Param("artistId") int artistId);
}