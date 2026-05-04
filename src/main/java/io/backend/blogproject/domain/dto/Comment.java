package io.backend.blogproject.domain.dto;

import io.backend.blogproject.constant.CommentError;
import io.backend.blogproject.constant.CommentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;


@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;

    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "parrent_id")
    private Comment child_id;

    @OneToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Comment parent_id;

    private Comment(
            String content
    ){
        this.content = content;
        this.commentStatus = CommentStatus.ACTIVATED;
        createdAt = LocalDateTime.now();
    }

    private void setComment(Comment parentComment){
        this.parent_id = parentComment;
    }

    public static Comment createComment(
        String content,
        Comment parentComment
    ){
        if (Strings.isEmpty(content)) throw new RuntimeException(CommentError.NO_COMMENT.message);

        Comment comment = new Comment(content);

        if(parentComment != null) comment.setComment(parentComment);

        return comment;
    }
}
