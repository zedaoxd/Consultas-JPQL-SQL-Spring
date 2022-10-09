package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value =
            "SELECT products.name " +
            "FROM products " +
            "INNER JOIN providers ON providers.id = products.id_providers " +
            "WHERE products.amount BETWEEN :start AND :end " +
            "AND providers.name LIKE CONCAT(:startsWith, '%')"
    )
    List<ProductMinProjection> seacrh1(Integer start, Integer end, String startsWith);

    @Query(value =
            "SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(p.name) " +
            "FROM Product p " +
            "WHERE p.amount BETWEEN :start AND :end " +
            "AND p.provider.name LIKE CONCAT(:startsWith, '%')"
    )
    List<ProductMinDTO> seacrh2(Integer start, Integer end, String startsWith);
}
