package progmatic.BlogSpringJu.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum RoleType {

    ADMIN(UserAuth.WRITE, UserAuth.READ, UserAuth.DELETE),
    MODERATOR(UserAuth.WRITE, UserAuth.READ, UserAuth.DELETE),
    USER(UserAuth.READ),
    NONE;

    public final UserAuth[] AUTHS;

    RoleType(UserAuth... auths) {
        AUTHS = auths;
    }

    public List<SimpleGrantedAuthority> getAuths() {
        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        for (UserAuth auth : AUTHS) {
            auths.add(new SimpleGrantedAuthority(auth.name()));
        }
        return auths;
    }
}
