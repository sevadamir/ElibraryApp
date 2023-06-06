package az.orient.elibrarydemoboot.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String email;
    @CreationTimestamp
    private Date paymentDate;
    @ColumnDefault(value = "1")
    private Integer active;

}
