package me.emate.mateback.contents.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.tag.entity.Tag;

@Table(name = "con_tag")
@Entity
@Getter
@NoArgsConstructor
public class ContentsTag {
    @Id
    @Column(name = "con_tag_no")
    private Integer conTagNo;

    @ManyToOne
    @JoinColumn(name = "contents_no")
    private Contents contents;

    @ManyToOne
    @JoinColumn(name = "tag_no")
    private Tag tag;
}
