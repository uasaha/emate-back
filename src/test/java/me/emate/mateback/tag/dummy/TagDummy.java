package me.emate.mateback.tag.dummy;

import me.emate.mateback.tag.entity.Tag;

public class TagDummy {
    public static Tag dummy() {
        return new Tag(1, "dummy", false, "#252525");
    }
}
