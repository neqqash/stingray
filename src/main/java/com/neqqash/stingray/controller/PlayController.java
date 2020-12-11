package com.neqqash.stingray.controller;

import com.neqqash.stingray.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayController implements com.neqqash.stingray.api.PlayApi {

    private final SongService songService;

    @Autowired
    public PlayController(SongService songService) {
        this.songService = songService;
    }

    @Override
    public ResponseEntity<String> playSong(Long songId) {
        return songService.playSong(songId);
    }
}

