package com.neqqash.stingray.dao;

import com.neqqash.stingray.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {
}
