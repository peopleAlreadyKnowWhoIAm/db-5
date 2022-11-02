package com.iot.project.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String email;

    @ManyToMany
    @JoinTable(
        name = "songs_saved_by_user", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = false), 
        inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id", nullable = false, unique = false)
    )
    List<Song> savedSongs;

    @ManyToMany
    @JoinTable(
        name = "albums_saved_by_user", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = false), 
        inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false, unique = false)
    )
    List<Album> savedAlbums;


    @ManyToMany
    @JoinTable(
        name = "user_prefer_genre", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = false), 
        inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false, unique = false)
    )
    List<Genre> preferedGenres;

    @OneToMany(mappedBy = "user")
    List<Playlist> playlists;
}
