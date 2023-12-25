package sendmail.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sendmail.tokens.Token;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u JOIN u.tokens t WHERE t.token = :token")
    User findByToken(@Param("token") String token);
}
