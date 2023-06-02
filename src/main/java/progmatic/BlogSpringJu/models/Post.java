package progmatic.BlogSpringJu.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    private Long postID;

    @ManyToOne
    private Blog releasedBlog;
    private String title;
    private String status;
    private String text;

    @CreationTimestamp
    private Timestamp createdOn;

    @OneToMany(mappedBy = "postTo")
    private List<Comment> comments;

    public Post() {
        comments = new ArrayList<>();
    }

    public Post(Long postID, Blog releasedBlog, String title, String status, String text, Timestamp createdOn) {
        this.releasedBlog = releasedBlog;
        this.postID = postID;
        this.title = title;
        this.status = status;
        this.text = text;
        this.createdOn = createdOn;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public Blog getReleasedBlog() {
        return releasedBlog;
    }

    public void setReleasedBlog(Blog releasedBlog) {
        this.releasedBlog = releasedBlog;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

