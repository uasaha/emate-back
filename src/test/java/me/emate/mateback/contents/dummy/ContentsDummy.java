package me.emate.mateback.contents.dummy;


import me.emate.mateback.category.dummy.CategoryDummy;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.member.dummy.MemberDummy;

import java.time.LocalDateTime;

public class ContentsDummy {
    public static Contents dummy() {
        return new Contents(
                101,
                CategoryDummy.dummy(),
                MemberDummy.dummy(),
                "dummy_subject",
                "dummy_detail",
                LocalDateTime.of(2023, 11, 1, 10, 30, 0),
                false,
                false,
                0,
                0,
                "dummy_thumbnail"
        );
    }
}
