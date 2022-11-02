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
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    Integer yearOfPublishing;

    @ManyToOne
    @JoinColumn(name = "label_id", nullable = false, unique = false)
    Label label;

    @ManyToMany
    @JoinTable(
        name = "album_has_author",
        joinColumns = @JoinColumn(name="album_id", referencedColumnName = "id", nullable = false),
        inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName = "id", nullable = false)
    )
    List<Author> authors;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="album")
    List<Song> songs;


    @Override
    public String toString() {
        return "Album [id=" + id + ", name=" + name + ", yearOfPublishing=" + yearOfPublishing + ", label=" + label
                + ", authors=" + authors + "]";
    }
}
