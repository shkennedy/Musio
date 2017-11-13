package com.sbu.webspotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sbu.webspotify.model.*;

public interface ArtistRepository extends JpaRepository<Artist, Integer>
{
    Artist findByName(String name);
    Artist findById(int id);
}