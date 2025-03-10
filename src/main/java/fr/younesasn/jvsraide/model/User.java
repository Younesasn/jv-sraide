package fr.younesasn.jvsraide.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String description;
    private Integer following;
    private Integer follower;
    @DateTimeFormat(pattern = "dd/mm/yy")
    private Date subscribeDate;
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Sraide> sraides;
}
