package az.orient.elibrarydemoboot.repository;

import az.orient.elibrarydemoboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByActive(Integer active);

    Book findBookByIdAndActive (Long id, Integer active);

}
