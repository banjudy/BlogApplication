package progmatic.BlogSpringJu.JPArepos;

import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.BlogSpringJu.models.Blog;

public interface BlogRepo extends JpaRepository<Blog, Long> {
}
