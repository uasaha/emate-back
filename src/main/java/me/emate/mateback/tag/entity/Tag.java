package me.emate.mateback.tag.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tag")
@Entity
@Getter
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer tagNo;

    @Column
    private String tagName;

    @Column(name = "tag_deleted")
    private boolean isDeleted;
}
