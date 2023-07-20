package me.emate.mateback.category.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "category")
@Entity
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "category_no")
    private Integer categoryNo;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_deleted")
    private boolean isDeleted;
}
