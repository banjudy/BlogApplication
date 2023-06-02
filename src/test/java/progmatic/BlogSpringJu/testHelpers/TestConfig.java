package progmatic.BlogSpringJu.testHelpers;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import progmatic.BlogSpringJu.models.BlogUser;
import progmatic.BlogSpringJu.models.RoleType;

import java.util.Arrays;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService(){
        BlogUser user = createUser( "usertest", RoleType.USER, "user", "user@hu.hu");
        BlogUser admin = createUser( "admintest", RoleType.ADMIN, "admin", "admin@admin.hu");
        admin.setUserID(1);

        return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    }

    private BlogUser createUser( String username, RoleType roleType, String password, String email) {
        BlogUser user = new BlogUser();
        user.setUserName(username);
        user.setRoleType(roleType);
        user.setPassword(password);
        user.setEmailAddress(email);

        return user;
    }
}
