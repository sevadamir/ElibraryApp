package az.orient.elibrarydemoboot.entity;

import java.util.Date;

import com.sun.istack.NotNull;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table (name = "book")
@Data
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String author;
    private String lang;
    private Integer year;
    private Integer amount;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault(value = "1")
    private Integer active;
}
