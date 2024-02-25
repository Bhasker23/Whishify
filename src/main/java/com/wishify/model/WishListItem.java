package com.wishify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private Double price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "User.id")
    @JsonIgnore
    private User user;

}
