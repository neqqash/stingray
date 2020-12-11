package com.neqqash.stingray.service;

import com.neqqash.stingray.dao.SongRepository;
import com.neqqash.stingray.dto.DetailedSongDTO;
import com.neqqash.stingray.dto.SongDTO;
import com.neqqash.stingray.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.NewThreadAction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public ResponseEntity<com.neqqash.stingray.dto.DetailedSongDTO> getDetailedSong(Long songID){
        Optional<Song> optionalSong = songRepository.findById(songID);

        if(optionalSong.isPresent()){
            Song song = optionalSong.get();
            com.neqqash.stingray.dto.DetailedSongDTO dto = new DetailedSongDTO().id(song.getId()).album(song.getAlbum())
                                                                                        .albumCover(song.getAlbumCover())
                                                                                        .artist(song.getArtist())
                                                                                        .label(song.getLabel())
                                                                                        .name(song.getName());

            return new ResponseEntity<>(dto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<com.neqqash.stingray.dto.SongDTO>> getSongs(){
        List<Song> songs = songRepository.findAll();

        List<com.neqqash.stingray.dto.SongDTO> results = new LinkedList<>();
        for(Song song: songs){
            com.neqqash.stingray.dto.SongDTO songDTO = new SongDTO().id(song.getId()).name(song.getName());

            results.add(songDTO);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    public ResponseEntity<String> playSong(Long songId) {
        Optional<Song> optionalSong = songRepository.findById(songId);

        if(optionalSong.isPresent()){
            Song song = optionalSong.get();
            song.setTimesPlayed(song.getTimesPlayed() + 1);
            songRepository.save(song);
            return new ResponseEntity<>("Successful Operation",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
