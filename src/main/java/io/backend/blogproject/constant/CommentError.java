package io.backend.blogproject.constant;

public enum CommentError {
    NO_COMMENT("댓글 내용이 없습니다.");
    public String message;
    CommentError(String message){
        this.message = message;
    }
}
