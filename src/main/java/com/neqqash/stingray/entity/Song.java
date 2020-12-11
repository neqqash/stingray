package com.neqqash.stingray.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "artist")
    private String artist;

    @Column(name = "album")
    private String album;

    @Column(name = "album_cover")
    private String albumCover;

    @Column(name = "label")
    private String label;

    @Column(name = "times_played")
    private long timesPlayed;

    @ManyToMany
    private Set<Playlist> playlists;

    public Song(){

    }

    public Song(String name, String artist, String album, String albumCover, String label, long timesPlayed) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.albumCover = albumCover;
        this.label = label;
        this.timesPlayed = timesPlayed;
    }

    public Set<Playlist> getPlaylist(){
        return playlists;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public String getLabel() {
        return label;
    }

    public long getTimesPlayed() {
        return timesPlayed;
    }

    public void setPlaylists(Set<Playlist> playlists){
        this.playlists = playlists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setTimesPlayed(long timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Song){
            return id == (((Song) obj).getId());
        }
        return false;
    }
}
