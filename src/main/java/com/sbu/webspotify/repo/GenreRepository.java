package com.sbu.webspotify.repo;

import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.GenreIdentifier;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
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

	@Query(value = "SELECT img.full_file_id as fullFileId, a.id as id, a.name as name "
    +"FROM artist_genre_mapping agm, artist a, Image img, Genre gnre "
    +"WHERE agm.artist_id = a.id AND agm.genre_id = gnre.id AND gnre.id = :genreId "
    +"AND a.artist_art_id = img.id ", 
        nativeQuery = true)      
    Set<ArtistIdentifier> findArtistsByGenre(@Param("genreId") int genreId);

    @Query(value = "SELECT art.id as artistId, art.name as artistName, i.full_file_id as fullFileId, alb.id as id, alb.title as title "
        + "FROM Album alb, Artist art, album_artist_mapping aam, Image i, album_genre_mapping agm, Genre gnre "
        + "WHERE agm.album_id = alb.id AND agm.genre_id = gnre.id AND gnre.id = :genreId "
        + "AND aam.artist_id = art.id AND aam.album_id = alb.id AND alb.album_art_id = i.id ",
        nativeQuery = true)
    Set<AlbumIdentifier> findAlbumsByGenre(@Param("genreId") int genreId);

    @Query(value = "SELECT alb.id as id, alb.title as albumTitle, art.id as artistId, art.name as artistName, s.id as albumId, s.title as title "
        + "FROM Song s, Album alb, Artist art, song_album_mapping sam, album_artist_mapping aam, song_genre_mapping sgm, Genre gnre "
        + "WHERE s.id = sam.song_id AND sam.album_id = alb.id AND aam.artist_id = art.id AND aam.album_id = alb.id "
        + "AND sgm.song_id = s.id AND sgm.genre_id = gnre.id AND gnre.id = :genreId ",
        nativeQuery = true)
    Set<SongIdentifier> findSongsByGenre(@Param("genreId") int genreId);
    
}