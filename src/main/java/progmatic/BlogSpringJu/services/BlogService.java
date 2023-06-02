package progmatic.BlogSpringJu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import progmatic.BlogSpringJu.JPArepos.BlogRepo;
import progmatic.BlogSpringJu.models.Blog;
import progmatic.BlogSpringJu.returnModels.ResponseTransfer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogService {

    @PersistenceContext
    private EntityManager em;

    private UserService userService;

    private BlogRepo blogRepo;

    @Autowired
    public BlogService(EntityManager em, BlogRepo blogRepo, UserService userService) {
        this.em = em;
        this.blogRepo = blogRepo;
        this.userService = userService;
    }

    @Transactional
    public List<Blog> getAllBlog(){
        return em.createQuery("SELECT blog FROM Blog blog", Blog.class)
                .getResultList();
    }
    @Transactional
    public ResponseTransfer createSpecificBlog(Blog blog) {
        Blog newBlog = createBlog(blog);
        saveBlog(newBlog);
        try {
            return new ResponseTransfer(
                    true,
                    "Blog has been created by user ",
                    HttpStatus.OK,
                    newBlog);
        } catch (Exception e) {
            return new ResponseTransfer(
                    false,
                    "Blog cannot be created.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public Blog createBlog(Blog blog) {
        Blog returnBlog = new Blog();
            returnBlog.setBlogName(blog.getBlogName());
            returnBlog.setCreatedBy(userService.getLoggedInUser());
            returnBlog.setTemplateName(blog.getTemplateName());
        em.persist(returnBlog);
        return returnBlog;
    }

    @Transactional
    public void saveBlog(Blog blog) {
        blogRepo.save(blog);
    }

}
