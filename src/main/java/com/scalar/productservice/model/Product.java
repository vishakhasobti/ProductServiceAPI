package com.scalar.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity   // create table of this class , eveery table must have the Primary key
public class Product  extends BaseModel{

    private String title;
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;
    private String description;
    private String imageUrl;
}
// cardinality b/w product and category :

/*steps:
L->R
1---> 1
product>category
m  --<-  1
//  m : 1

 */
