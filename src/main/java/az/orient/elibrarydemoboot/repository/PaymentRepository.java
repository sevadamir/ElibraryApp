package az.orient.elibrarydemoboot.repository;

import az.orient.elibrarydemoboot.entity.Customer;
import az.orient.elibrarydemoboot.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByCustomerAndActive(Customer customer, Integer active);
    Payment findPaymentByIdAndActive(Long id, Integer active);
}
