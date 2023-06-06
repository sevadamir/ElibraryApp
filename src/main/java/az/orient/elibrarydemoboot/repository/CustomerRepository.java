package az.orient.elibrarydemoboot.repository;

import az.orient.elibrarydemoboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByActive (Integer active);
    Customer findCustomerByIdAndActive(Long customerId, Integer active);

    Customer findCustomerByEmailAndActive (String email, Integer active);

    Customer findByActivationCodeAndActive(String activationCode, Integer active);

    @Query("SELECT c.email FROM Customer c")
    List<String> getEmails();
    @Query("SELECT c.activationCode FROM Customer c")
    List<String> getActivationCode();
    @Query("SELECT c.name FROM Customer c")
    List<String> getName();
}
