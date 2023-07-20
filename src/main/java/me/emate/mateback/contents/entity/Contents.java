package me.emate.mateback.contents.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.member.entity.Member;

@Table(name = "contents")
@Entity
@Getter
@NoArgsConstructor
public class Contents {
    @Id
    @Column(name = "contents_no")
    private Integer contentsNo;

    @ManyToOne
    @JoinColumn(name = "category_no")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @Column(name = "subject")
    private String subject;

    @Column(name = "detail")
    private String detail;

    @Column(name = "created_at")
    private boolean createdAt;

    @Column(name = "contents_hidden")
    private boolean isHidden;

    @Column(name = "contents_deleted")
    private boolean isDeleted;
}