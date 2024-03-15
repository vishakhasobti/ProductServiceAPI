package com.scalar.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass // dont need parent of superclass just to have attributes ,
public class BaseModel {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)  // Auto inc ID
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;
}
