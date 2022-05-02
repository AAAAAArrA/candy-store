package com.example.chocolateshop.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "chocolates")
@AllArgsConstructor
@NoArgsConstructor
public class Chocolate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
    mappedBy = "chocolates")
    private List<Image> images = new ArrayList<>();
    private int previewImageId;
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
    public void addImageToChocolate(Image image){
        image.setChocolates(this);
        images.add(image);
    }
}
