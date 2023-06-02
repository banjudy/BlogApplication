package progmatic.BlogSpringJu.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private long commentID;

    @ManyToOne
    private BlogUser author;

    @ManyToOne
    private Post postTo;

    @ManyToOne
    private Comment commentTo;

    private String text;

    @CreationTimestamp
    private Timestamp dateSent;

    public Comment() {
    }

    public Comment(long commentID, BlogUser author, Post postTo, Comment commentTo, String text, Timestamp dateSent) {
        this.commentID = commentID;
        this.author = author;
        this.postTo = postTo;
        this.commentTo = commentTo;
        this.text = text;
        this.dateSent = dateSent;
    }

    public long getCommentID() {
        return commentID;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public BlogUser getAuthor() {
        return author;
    }

    public void setAuthor(BlogUser author) {
        this.author = author;
    }

    public Post getPostTo() {
        return postTo;
    }

    public void setPostTo(Post postTo) {
        this.postTo = postTo;
    }

    public Comment getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(Comment commentTo) {
        this.commentTo = commentTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDateSent() {
        return dateSent;
    }

    public void setDateSent(Timestamp dateSent) {
        this.dateSent = dateSent;
    }
}