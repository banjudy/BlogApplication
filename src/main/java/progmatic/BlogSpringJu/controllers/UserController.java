package progmatic.BlogSpringJu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import progmatic.BlogSpringJu.models.BlogUser;
import progmatic.BlogSpringJu.models.RoleType;
import progmatic.BlogSpringJu.returnModels.ResponseTransfer;
import progmatic.BlogSpringJu.services.UserService;

import java.util.List;

@RestController
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = {"/","/home"})
    public String loadHomePage() {
        return "success";
    }

    @GetMapping("/user")
    public BlogUser getLoggedInUser() {
        return service.getLoggedInUser();
    }

    @GetMapping(value = {"/users/{id}"})
    public ResponseTransfer getUserPerID(
            @PathVariable("id") long id) {
        if (!((id == getLoggedInUser().getUserID()) || (getLoggedInUser().getRoleType().equals(RoleType.ADMIN)))) {
            return new ResponseTransfer(
                    false,
                    "You are not allowed to see this user information.",
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseTransfer(
                    true,
                    "You are able to see the user information.",
                    HttpStatus.OK,
                    service.loadUserByUserID(id));
        }
    }

    @GetMapping(value = {"/users"})
    public List<BlogUser> getAllUser() {
        return service.loadAllUser();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE},
                    value = "/register")
    public ResponseTransfer registerUser(
            @RequestBody BlogUser blogUser){
        return service.registerUser(blogUser);
    }

}
