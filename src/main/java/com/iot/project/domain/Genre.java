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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "related_genres", 
        joinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "related_genre_id", referencedColumnName = "id", nullable = false)
    )
    List<Genre> related;

    @Override
    public String toString() {
        return "Genre [id=" + id + ", name=" + name + "]";
    }
}
