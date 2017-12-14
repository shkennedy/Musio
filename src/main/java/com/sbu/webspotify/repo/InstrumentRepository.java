package com.sbu.webspotify.repo;

import java.util.Set;

import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.InstrumentIdentifier;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.model.Instrument;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("instrumentRepository")
public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {
	
	@Query("SELECT i.id as id, i.name as name FROM Instrument i WHERE i.name LIKE CONCAT('%', :queryString, '%')")
    Set<InstrumentIdentifier> findByNameContaining(@Param("queryString") String queryString);

	Instrument findById(int id);

	@Query(value = "SELECT img.full_file_id as fullFileId, a.id as id, a.name as name "
					+"FROM artist_instrument_mapping aim, artist a, Image img, Instrument ins "
					+"WHERE aim.artist_id = a.id AND aim.instrument_id = ins.id AND ins.id = :instrumentId "
					+"AND a.artist_art_id = img.id ", 
						nativeQuery = true)      
	Set<ArtistIdentifier> findArtistsByInstrument(@Param("instrumentId") int instrumentId);

	@Query(value = "SELECT art.id as artistId, art.name as artistName, i.full_file_id as fullFileId, alb.id as id, alb.title as title "
					+ "FROM Album alb, Artist art, album_artist_mapping aam, Image i, album_instrument_mapping aim, Instrument ins "
					+ "WHERE aim.album_id = alb.id AND aim.instrument_id = ins.id AND ins.id = :instrumentId "
					+ "AND aam.artist_id = art.id AND aam.album_id = alb.id AND alb.album_art_id = i.id ",
					nativeQuery = true)
	Set<AlbumIdentifier> findAlbumsByInstrument(@Param("instrumentId") int instrumentId);

	@Query(value = "SELECT alb.id as id, alb.title as albumTitle, art.id as artistId, art.name as artistName, s.id as albumId, s.title as title "
					+ "FROM Song s, Album alb, Artist art, song_album_mapping sam, album_artist_mapping aam, song_instrument_mapping sim, Instrument ins "
					+ "WHERE s.id = sam.song_id AND sam.album_id = alb.id AND aam.artist_id = art.id AND aam.album_id = alb.id "
					+ "AND sim.song_id = s.id AND sim.instrument_id = ins.id AND ins.id = :instrumentId ",
					nativeQuery = true)
	Set<SongIdentifier> findSongsByInstrument(@Param("instrumentId") int instrumentId);
}
