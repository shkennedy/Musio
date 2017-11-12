package com.sbu.webspotify.repo;

import org.springframework.data.repository.CrudRepository;
import com.sbu.webspotify.model.*;

public interface SongRepository extends CrudRepository<Song, Integer>
{

}