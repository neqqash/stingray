package com.neqqash.stingray.controller;

import com.neqqash.stingray.dto.SongDTO;
import com.neqqash.stingray.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongsController implements com.neqqash.stingray.api.SongsApi {

    private final SongService songService;

    @Autowired
    public SongsController(SongService songService) {
        this.songService = songService;
    }

    @Override
    public ResponseEntity<List<SongDTO>> getSongs() {
        return songService.getSongs();
    }
}
