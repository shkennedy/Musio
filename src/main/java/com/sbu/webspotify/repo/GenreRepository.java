package com.sbu.webspotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sbu.webspotify.model.*;

public interface GenreRepository extends JpaRepository<Genre, Integer>
{
    Genre findById(int id);
}