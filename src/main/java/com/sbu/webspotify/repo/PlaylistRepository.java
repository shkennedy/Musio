package com.sbu.webspotify.repo;

import org.springframework.data.repository.CrudRepository;
import com.sbu.webspotify.model.*;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer>
{
    Playlist findById(int id);
}