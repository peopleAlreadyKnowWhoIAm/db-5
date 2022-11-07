package com.iot.project.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "authors")
    List<Song> songs;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    List<Album> albums;

    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + "]";
    }

}
