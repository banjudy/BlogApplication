package progmatic.BlogSpringJu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import progmatic.BlogSpringJu.JPArepos.UserRepo;
import progmatic.BlogSpringJu.models.BlogUser;
import progmatic.BlogSpringJu.returnModels.ResponseTransfer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return em.createQuery("SELECT u FROM BlogUser u WHERE u.userName = :userName", BlogUser.class)
                .setParameter("userName", userName)
                .getSingleResult();
    }

    @Transactional
    public List<BlogUser> loadAllUser() {
        return em.createQuery("SELECT u FROM BlogUser u", BlogUser.class)
                .getResultList();
    }

    @Transactional
    public BlogUser loadUserByUserID(long userID) throws UsernameNotFoundException {
        return em.createQuery("SELECT u FROM BlogUser u WHERE u.userID = :userID", BlogUser.class)
                .setParameter("userID", userID)
                .getSingleResult();
    }

    public BlogUser getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof BlogUser) {
                return (BlogUser) principal;
            }
        }
        return null;
    }

    @Transactional
    public ResponseTransfer registerUser(BlogUser blogUser) {
        if (!isUserNameTaken(blogUser.getUserName())) {
            BlogUser returnUser = createUser(blogUser);
            saveUser(returnUser);
            try {
                return new ResponseTransfer(
                        true,
                        "User is registered.",
                        HttpStatus.OK,
                        returnUser);
            } catch (Exception e) {
                return new ResponseTransfer(
                        false,
                        "User has not been registered.",
                        HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseTransfer(
                false,
                "Username is already enabled.",
                HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public void saveUser(BlogUser user) {
        userRepo.save(user);
    }

    @Transactional
    public BlogUser createUser(BlogUser user) {
        BlogUser returnUser = new BlogUser();
            returnUser.setUserName(user.getUsername());
            returnUser.setPassword(passwordEncoder.encode(user.getPassword()));
            returnUser.setRoleType(user.getRoleType());
            returnUser.setEmailAddress(user.getEmailAddress());
        em.persist(returnUser);
        return returnUser;
    }

    @Transactional
    public boolean isUserNameTaken(String username){
        try {
            loadUserByUsername(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
