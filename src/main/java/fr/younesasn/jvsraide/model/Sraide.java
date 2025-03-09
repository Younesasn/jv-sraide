package fr.younesasn.jvsraide.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Data
public class Sraide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private String comment;

    @Getter
    @Setter
    @ManyToOne
    @JsonIgnoreProperties({"sraides"})
    @JoinColumn(name = "user_id")
    private User user;
}