package com.book.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByProductId(Long productId);

//    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.sku = :sku AND " +
//           "(:customerGroup IS NULL OR p.customerGroup = :customerGroup) AND " +
//           "(:country IS NULL OR p.country = :country) AND p.currency = :currency")
//    Optional<Price> findDynamicPrice(@Param("productId") Long productId, 
//                                     @Param("sku") String sku, 
//                                     @Param("customerGroup") String customerGroup, 
//                                     @Param("country") String country, 
//                                     @Param("currency") String currency);
   /* List<Price> findByProductId(Long productId);
    Price findByProductIdAndSkuAndCustomerGroupAndCountryAndCurrency(
            Long productId, String sku, String customerGroup, String country, String currency);
    boolean existsByProductIdAndSkuAndCustomerGroupAndCountryAndCurrency(
            Long productId, String sku, String customerGroup, String country, String currency);*/
    Price findFirstBySku(String sku);
	
}
    

