package pl.kamol84.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kamol84.twitter.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);
}
