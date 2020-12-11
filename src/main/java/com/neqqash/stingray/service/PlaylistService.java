package com.neqqash.stingray.service;

import com.neqqash.stingray.dao.PlaylistRepository;
import com.neqqash.stingray.dao.SongRepository;
import com.neqqash.stingray.dao.UserRepository;
import com.neqqash.stingray.entity.Playlist;
import com.neqqash.stingray.entity.Song;
import com.neqqash.stingray.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public ResponseEntity<String> createPlaylist(com.neqqash.stingray.dto.PlaylistDTO playlistDTO){
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Playlist playlist = new Playlist(userRepository.findByUsername(user.getUsername()),playlistDTO.getName());
        Playlist createdPlaylist = playlistRepository.save(playlist);

        if(createdPlaylist.getId() > 0){
            return new ResponseEntity<>("Successful operation", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<com.neqqash.stingray.dto.PlaylistDTO> getPlaylist(Long playlistId) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Playlist> optionalPlaylist = playlistRepository.findByIdAndUserId(playlistId, user.getId());

        if(optionalPlaylist.isPresent()){
            Playlist playlist = optionalPlaylist.get();
            List<com.neqqash.stingray.dto.SongDTO> songDTOS = new LinkedList<>();
            for(Song song: playlist.getSongs()){
                com.neqqash.stingray.dto.SongDTO songDTO = new com.neqqash.stingray.dto.SongDTO().id(song.getId()).name(song.getName());
                songDTOS.add(songDTO);
            }
            com.neqqash.stingray.dto.PlaylistDTO dto = new com.neqqash.stingray.dto.PlaylistDTO().name(playlist.getName()).songs(songDTOS);


            return new ResponseEntity<>(dto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> addSongsToPlaylist(Long playlistId, List<com.neqqash.stingray.dto.SongDTO> songsDTO) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Playlist> optionalPlaylist = playlistRepository.findByIdAndUserId(playlistId, user.getId());

        if(optionalPlaylist.isPresent()){
            Playlist playlist = optionalPlaylist.get();
            Set<Song> songs = playlist.getSongs();

            for(com.neqqash.stingray.dto.SongDTO songDTO :songsDTO){
                songs.add(songRepository.getOne(songDTO.getId()));
            }
            playlistRepository.save(playlist);
            return new ResponseEntity<>("Successful Operation",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> removeSongsToPlaylist(Long playlistId, List<com.neqqash.stingray.dto.SongDTO> songsDTO) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Playlist> optionalPlaylist = playlistRepository.findByIdAndUserId(playlistId, user.getId());

        if(optionalPlaylist.isPresent()){
            Playlist playlist = optionalPlaylist.get();
            Set<Song> songs = playlist.getSongs();

            for(com.neqqash.stingray.dto.SongDTO songDTO :songsDTO){
                songs.remove(songRepository.getOne(songDTO.getId()));
            }
            playlistRepository.save(playlist);
            return new ResponseEntity<>("Successful Operation",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
