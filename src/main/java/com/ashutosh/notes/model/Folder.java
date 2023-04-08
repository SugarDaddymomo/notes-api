package com.ashutosh.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "folder")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<String> text;

    @Getter
    @Setter
    private boolean isPrivate;

    @Lob
    @Getter
    @Setter
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Folder parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private Set<Folder> children;

    @JsonIgnore
    public Set<Folder> getChildren() {
        return children;
    }
}
