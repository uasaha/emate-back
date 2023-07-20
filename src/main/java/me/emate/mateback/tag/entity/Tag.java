package me.emate.mateback.tag.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tag")
@Entity
@Getter
@NoArgsConstructor
public class Tag {
    @Id
    @Column
    private Integer tagNo;

    @Column
    private String tagName;

    @Column(name = "tag_deleted")
    private boolean isDeleted;
}
