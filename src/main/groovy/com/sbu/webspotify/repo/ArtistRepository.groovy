package com.sbu.webspotify.repo

import org.springframework.data.repository.CrudRepository
import com.sbu.webspotify.domain.*

public interface ArtistRepository extends CrudRepository<Artist, Integer>
{
    Artist findByName(String name)
}