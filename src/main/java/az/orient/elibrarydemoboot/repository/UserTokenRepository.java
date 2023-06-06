package az.orient.elibrarydemoboot.repository;

import az.orient.elibrarydemoboot.entity.User;
import az.orient.elibrarydemoboot.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    UserToken findUserTokenByUserAndTokenAndActive(User user, String token, Integer active);
    UserToken findUserTokenByUserIdAndTokenAndActive(Long userId, String token, Integer active);

}
