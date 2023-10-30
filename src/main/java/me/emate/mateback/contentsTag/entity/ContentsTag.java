package me.emate.mateback.contentsTag.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.tag.entity.Tag;

/**
 * Contents tag entity 입니다.
 *
 * @author 여운석
 */
@Table(name = "con_tag")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_tag_no")
    private Long conTagNo;

    @ManyToOne
    @JoinColumn(name = "contents_no")
    private Contents contents;

    @ManyToOne
    @JoinColumn(name = "tag_no")
    private Tag tag;
}
