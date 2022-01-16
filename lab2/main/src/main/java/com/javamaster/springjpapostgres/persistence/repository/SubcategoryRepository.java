package com.javamaster.springjpapostgres.persistence.repository;

import com.javamaster.springjpapostgres.persistence.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer>, JpaSpecificationExecutor<Subcategory> {
    Subcategory findById(Long id);
}
