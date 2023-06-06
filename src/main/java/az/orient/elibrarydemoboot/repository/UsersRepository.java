package az.orient.elibrarydemoboot.repository;

import az.orient.elibrarydemoboot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findUsersByEmailAndActive(String email, Integer active);

    Users findUsersByPasswordAndActive(String password, Integer active);
}
