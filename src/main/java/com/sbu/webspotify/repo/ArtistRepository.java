package com.sbu.webspotify.repo;

import java.util.Set;

import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.ConcertIdentifier;
import com.sbu.webspotify.model.Artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends JpaRepository<Artist, Integer>
{
    Artist findByName(String name);
    Artist findById(int id);
    
    @Query(value = "SELECT i.full_file_id as fullFileId, a.id as id, a.name as name "
                    + "FROM image i "
                    + "RIGHT JOIN artist a ON a.artist_art_id = i.id WHERE a.name LIKE CONCAT('%', :queryString, '%')",
                    nativeQuery = true)
    Set<ArtistIdentifier> findByNameContaining(@Param("queryString") String queryString);
    
    @Query(value = "SELECT COUNT(ufs.artist_id) FROM user_favorite_artist ufs WHERE ufs.artist_id = :queryId", nativeQuery = true)    
    int findFavoriteCountForArtist(@Param("queryId") int queryId);
    
    @Query(value = "SELECT i.full_file_id as fullFileId, a.id as id, a.name as name "
                    + "FROM Image i "
                    + "RIGHT JOIN Artist a ON a.artist_art_id = i.id "
                    + "RIGHT JOIN user_favorite_artist ufa ON ufa.artist_id = a.id AND ufa.user_id = :userId "
                    + "ORDER BY timestamp DESC LIMIT :numElements", 
                    nativeQuery = true)    
    Set<ArtistIdentifier> findRecentlyFavoritedArtistsByUser(@Param("userId") int userId, @Param("numElements") int numElements);
    
    @Query(value = "SELECT i.full_file_id as fullFileId, a2.id as id, a2.name as name "
                    +"FROM artist_artist_relation aar, artist a1, artist a2, Image i "
                    +"WHERE a1.id = :artistId AND aar.artist1_id = a1.id AND "
                    +"aar.artist2_id = a2.id AND aar.artist1_id <> aar.artist2_id AND a2.artist_art_id = i.id "
                    +"ORDER BY score DESC LIMIT :numElements", 
                        nativeQuery = true)    
    Set<ArtistIdentifier> findMostRelatedArtists(@Param("artistId") int artistId, @Param("numElements") int numElements);
    
    @Query(value = "SELECT c.date as date, c.id as id, c.name as name, c.venue_id as venueId "
                    + "FROM Concert c, artist_concert_mapping acm "
                    + "WHERE acm.artist_id = :artistId AND c.id = acm.concert_id", nativeQuery=true)    
    Set<ConcertIdentifier> findConcertsForArtist(@Param("artistId") int artistId);
}
