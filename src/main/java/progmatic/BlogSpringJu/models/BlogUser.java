package progmatic.BlogSpringJu.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class BlogUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long userID;
    private String userName;

    @Enumerated(EnumType.STRING)
    private RoleType authority;

    private String password;
    private String emailAddress;

    @CreationTimestamp
    private Timestamp registrationDate;

    private boolean isLocked;

    @OneToMany(mappedBy = "createdBy")
    private List<Blog> blogs;

    public BlogUser() {
        blogs = new ArrayList<>();
    }

    public BlogUser(
            String userName,
            RoleType authority,
            String password,
            String emailAddress) {
        this();
        this.userName = userName;
        this.authority = authority;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public BlogUser(Long userID,
                    String userName,
                    RoleType authority,
                    String password,
                    String emailAddress,
                    Timestamp registrationDate) {
        this(userName, authority, password, emailAddress);
        this.userID = userID;
        this.registrationDate = registrationDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority.getAuths();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isLocked;
    }

    @Override
    public boolean isEnabled() {
        return !isLocked;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RoleType getRoleType() {
        return authority;
    }

    public void setRoleType(RoleType roleType) {
        this.authority = roleType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    /*public List<Blog> getBlogs() {
        return blogs;
    }*/

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
