package com.neqqash.stingray.dao;

import com.neqqash.stingray.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
  Optional<Playlist> findByIdAndUserId(Long id, Long userId);
}
