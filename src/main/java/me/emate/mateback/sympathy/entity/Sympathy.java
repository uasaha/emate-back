package me.emate.mateback.sympathy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.member.entity.Member;

@Table(name = "sympathy")
@Entity
@NoArgsConstructor
@Getter
public class Sympathy {
    @Id
    @Column
    private Long sympathyNo;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "contents_no")
    private Contents contents;
}
