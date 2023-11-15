package me.emate.mateback.contentstag.dummy;

import me.emate.mateback.contents.dummy.ContentsDummy;
import me.emate.mateback.contentstag.entity.ContentsTag;
import me.emate.mateback.tag.dummy.TagDummy;

public class ContentsTagDummy {
    public static ContentsTag dummy() {
        return new ContentsTag(1L, ContentsDummy.dummy(), TagDummy.dummy());
    }
}
