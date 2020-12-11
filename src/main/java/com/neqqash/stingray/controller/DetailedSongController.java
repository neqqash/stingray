package com.neqqash.stingray.controller;

import com.neqqash.stingray.dao.SongRepository;
import com.neqqash.stingray.dto.DetailedSongDTO;
import com.neqqash.stingray.dto.SongDTO;
import com.neqqash.stingray.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DetailedSongController implements com.neqqash.stingray.api.SongApi{

    private final SongService songService;

    @Autowired
    public DetailedSongController(SongService songService) {
        this.songService = songService;
    }

    @Override
    public ResponseEntity<DetailedSongDTO> getDetailedSong(Long songId) {
        return songService.getDetailedSong(songId);
    }


}
