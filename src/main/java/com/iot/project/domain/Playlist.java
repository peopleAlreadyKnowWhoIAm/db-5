package com.iot.project.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user_playlist_info")
@EqualsAndHashCode
@Getter
@Setter
public class Playlist {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    User user;

    @ManyToMany
    @JoinTable(
        name = "playlist_has_song",
        joinColumns = @JoinColumn(name="user_playlist_info_id", referencedColumnName = "id", nullable = false),
        inverseJoinColumns = @JoinColumn(name="song_id", referencedColumnName = "id", nullable = false)
    )
    List<Song> songs;
    
}
