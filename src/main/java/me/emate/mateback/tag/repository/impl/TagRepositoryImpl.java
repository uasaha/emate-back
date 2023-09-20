package me.emate.mateback.tag.repository.impl;

import com.querydsl.core.types.Projections;
import me.emate.mateback.tag.dto.TagListResponseDto;
import me.emate.mateback.tag.entity.QTag;
import me.emate.mateback.tag.entity.Tag;
import me.emate.mateback.tag.repository.TagRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TagRepositoryImpl extends QuerydslRepositorySupport implements TagRepositoryCustom {
    public TagRepositoryImpl() {
        super(Tag.class);
    }

    QTag tag = QTag.tag;


    @Override
    public List<TagListResponseDto> findAllTags() {
        return from(tag)
                .select(Projections.constructor(
                        TagListResponseDto.class,
                        tag.tagNo,
                        tag.tagName
                ))
                .where(tag.isDeleted.eq(false))
                .fetch();
    }
}
