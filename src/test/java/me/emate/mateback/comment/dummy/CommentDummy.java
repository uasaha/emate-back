package me.emate.mateback.comment.dummy;

import me.emate.mateback.comment.entity.Comment;
import me.emate.mateback.contents.dummy.ContentsDummy;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.member.entity.Member;

public class CommentDummy {
  public static Comment dummy() {
    Contents contents = ContentsDummy.dummy();
    Member member = contents.getMember();
    Comment comment = Comment.memberBuilder()
        .contents(contents)
        .content("dummy_comment_mom")
        .member(member)
        .secret(false)
        .build();

    return Comment.memberBuilder()
        .contents(contents)
        .content("dummy_comment_child")
        .mom(comment)
        .member(member)
        .secret(false)
        .build();
  }
}
