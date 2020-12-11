package com.neqqash.stingray.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user; //maps playlist (many) to one UserId (one)

    @Column(name = "name")
    private String name;

    @ManyToMany
    private Set<Song> songs;

    public Playlist(){

    }

    public Playlist(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
