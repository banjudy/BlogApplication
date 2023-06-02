package progmatic.BlogSpringJu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import progmatic.BlogSpringJu.models.Blog;
import progmatic.BlogSpringJu.returnModels.ResponseTransfer;
import progmatic.BlogSpringJu.services.BlogService;

import java.util.List;

@RestController
public class BlogController {

    private BlogService service;

    @Autowired
    public BlogController(BlogService service) {
        this.service = service;
    }

    @GetMapping(value = {"/blogs"})
    public List<Blog> loadAllBlog(){
        return service.getAllBlog();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE},
                value = "/blogs")
    public ResponseTransfer registerBlog(
            @RequestBody Blog blog){
        return service.createSpecificBlog(blog);
    }
}
