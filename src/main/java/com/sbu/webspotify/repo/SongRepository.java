package com.sbu.webspotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sbu.webspotify.model.*;

public interface SongRepository extends JpaRepository<Song, Integer>
{
    Song findById(int id);
}