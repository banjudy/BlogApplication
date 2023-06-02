package progmatic.BlogSpringJu.JPArepos;

import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.BlogSpringJu.models.BlogUser;


public interface UserRepo extends JpaRepository<BlogUser, Long> {
}
