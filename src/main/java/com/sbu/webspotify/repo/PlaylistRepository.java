package com.sbu.webspotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sbu.webspotify.model.*;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>
{
    Playlist findById(int id);
}