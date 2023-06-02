package progmatic.BlogSpringJu.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue
    private long blogID;

    @Column(name = "blog_name")
    private String blogName;

    @ManyToOne
    private BlogUser createdBy;
    private String templateName;
    private Timestamp createdOn;

    @OneToMany(mappedBy = "releasedBlog")
    public List<Post> posts;

    public Blog() {
        posts = new ArrayList<>();
    }

    public Blog(String blogName, String templateName) {
        this.blogName = blogName;
        this.templateName = templateName;
    }

    public Blog(String blogName, BlogUser createdBy, String templateName) {
        this.blogName = blogName;
        this.createdBy = createdBy;
        this.templateName = templateName;
    }

    public Blog(long blogID, String blogName, BlogUser createdBy, String templateName, Timestamp createdOn) {
        this(blogName, createdBy, templateName);
        this.blogID = blogID;
        this.createdOn = createdOn;
    }

    public long getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public BlogUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BlogUser createdBy) {
        this.createdBy = createdBy;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
