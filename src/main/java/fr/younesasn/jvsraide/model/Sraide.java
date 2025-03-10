package fr.younesasn.jvsraide.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sraide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    @ManyToOne
    @JsonIgnoreProperties({"sraides"})
    @JoinColumn(name = "user_id")
    private User user;
}