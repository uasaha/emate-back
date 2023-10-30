package me.emate.mateback.file.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.member.entity.Member;

import java.time.LocalDateTime;

/**
 * File entity 입니다.
 *
 * @author 여운석
 */
@Table(name = "file")
@Entity
@Getter
@NoArgsConstructor
public class File {
    @Id
    @Column(name = "file_no")
    private Integer fileNo;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "contents_no")
    private Contents contents;

    @Column
    private String fileCategory;

    @Column
    private String filePath;

    @Column
    private String extension;

    @Column
    private String originName;

    @Column
    private String savedName;

    @Column
    private LocalDateTime createdAt;
}
