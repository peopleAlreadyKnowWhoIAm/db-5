package com.iot.project.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    Integer length;

    @ManyToMany
    @JoinTable(
        name = "song_has_author",
        joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    )
    List<Author> authors;

    @OneToOne()
    @JoinColumn(name="genre_id", referencedColumnName = "id", nullable = true, unique = false)
    Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="album_id", referencedColumnName = "id", nullable = true, unique = false)
    Album album;

}
