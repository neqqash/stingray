package com.neqqash.stingray.controller;

import com.neqqash.stingray.dto.PlaylistDTO;
import com.neqqash.stingray.dto.SongDTO;
import com.neqqash.stingray.entity.Playlist;
import com.neqqash.stingray.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaylistController implements com.neqqash.stingray.api.PlaylistApi {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public ResponseEntity<String> createPlaylist(PlaylistDTO playlist) {
        return playlistService.createPlaylist(playlist);
    }

    @Override
    public ResponseEntity<String> addSongsToPlaylist(Long playlistId, List<SongDTO> songs) {
        return playlistService.addSongsToPlaylist(playlistId, songs);
    }

    @Override
    public ResponseEntity<PlaylistDTO> getPlaylist(Long playlistId) {
        return playlistService.getPlaylist(playlistId);
    }

    @Override
    public ResponseEntity<String> removeSongsToPlaylist(Long playlistId, List<SongDTO> songs) {
        return playlistService.removeSongsToPlaylist(playlistId, songs);
    }
}
