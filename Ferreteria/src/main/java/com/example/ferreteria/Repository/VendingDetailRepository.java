package com.example.ferreteria.Repository;

import com.example.ferreteria.Model.VendingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendingDetailRepository extends JpaRepository<VendingDetail,Long> {

//    List<VendingDetail> findByVendingId(Long vendingId);

//    @Query("SELECT vd FROM VendingDetail vd WHERE vd.proceedsId = ?1")
//    List<VendingDetail> findByProductId(Long productId);
}
