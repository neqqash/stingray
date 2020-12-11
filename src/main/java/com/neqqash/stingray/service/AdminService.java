package com.neqqash.stingray.service;

import com.neqqash.stingray.dao.SongRepository;
import com.neqqash.stingray.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final SongRepository songRepository;

    @Autowired
    public AdminService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public ResponseEntity<String> addSong(com.neqqash.stingray.dto.DetailedSongDTO detailedSong) {
        Song song = new Song();
        song.setName(detailedSong.getName());
        song.setArtist(detailedSong.getArtist());
        song.setAlbum(detailedSong.getAlbum());
        song.setAlbumCover(detailedSong.getAlbumCover());
        song.setLabel(detailedSong.getLabel());

        songRepository.save(song);

        return new ResponseEntity<>("Successful Operation", HttpStatus.OK);
    }

    public ResponseEntity<List<com.neqqash.stingray.dto.AdminSongDTO>> getSongs() {
        List<Song> songs = songRepository.findAll();

        List<com.neqqash.stingray.dto.AdminSongDTO> results = new LinkedList<>();
        for (Song song : songs) {
            com.neqqash.stingray.dto.AdminSongDTO adminSongDTO = new com.neqqash.stingray.dto.AdminSongDTO().timesPlayed(song.getTimesPlayed());
            adminSongDTO.id(song.getId()).album(song.getAlbum())
                    .albumCover(song.getAlbumCover())
                    .artist(song.getArtist())
                    .label(song.getLabel())
                    .name(song.getName());

            results.add(adminSongDTO);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    public ResponseEntity<String> modifySong(com.neqqash.stingray.dto.DetailedSongDTO detailedSong) {
        Optional<Song> optionalSong = songRepository.findById(detailedSong.getId());

        if(optionalSong.isPresent()){
            Song song = optionalSong.get();

            if(detailedSong.getName() != null && !detailedSong.getName().isEmpty()){
                song.setName(detailedSong.getName());
            }
            if(detailedSong.getAlbum() != null && !detailedSong.getAlbum().isEmpty()){
                song.setAlbum(detailedSong.getAlbum());
            }
            if(detailedSong.getAlbumCover() != null && !detailedSong.getAlbumCover().isEmpty()){
                song.setAlbumCover(detailedSong.getAlbumCover());
            }
            if(detailedSong.getArtist() != null && !detailedSong.getArtist().isEmpty()){
                song.setArtist(detailedSong.getArtist());
            }
            if(detailedSong.getLabel() != null && !detailedSong.getLabel().isEmpty()){
                song.setLabel(detailedSong.getLabel());
            }

            songRepository.save(song);
            return new ResponseEntity<>("Successful Operation",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> removeSong(com.neqqash.stingray.dto.DetailedSongDTO detailedSong) {
        Optional<Song> optionalSong = songRepository.findById(detailedSong.getId());

        if(optionalSong.isPresent()){
            Song song = optionalSong.get();

            songRepository.delete(song);
            return new ResponseEntity<>("Successful Operation",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
