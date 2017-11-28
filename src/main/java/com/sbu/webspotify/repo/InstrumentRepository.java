package com.sbu.webspotify.repo;

import com.sbu.webspotify.dto.identifier.InstrumentIdentifier;
import com.sbu.webspotify.model.Instrument;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("instrumentRepository")
public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {
	
	@Query("SELECT i.id as id, i.name as name FROM Instrument i WHERE i.name LIKE CONCAT('%', :queryString, '%')")
    Set<InstrumentIdentifier> findByNameContaining(@Param("queryString") String queryString);

	Instrument findById(int id);
}
