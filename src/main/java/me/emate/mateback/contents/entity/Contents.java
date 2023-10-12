package me.emate.mateback.contents.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.member.entity.Member;

import java.time.LocalDateTime;

@Table(name = "contents")
@Entity
@Getter
@NoArgsConstructor
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDateTime createdAt;

    @Column(name = "contents_hidden")
    private boolean isHidden;

    @Column(name = "contents_deleted")
    private boolean isDeleted;

    @Column(name = "views")
    private Integer views;

    @Column(name = "loving")
    private Integer loving;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Builder
    public Contents(Category category, Member member, String subject, String detail, String thumbnail) {
        this.category = category;
        this.member = member;
        this.subject = subject;
        this.detail = detail;
        this.views = 0;
        this.loving = 0;
        this.createdAt = LocalDateTime.now();
        this.thumbnail = thumbnail;
    }
}
