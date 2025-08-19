package com.example.ferreteria.Repository;

import com.example.ferreteria.Model.Vending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VendingRepository extends JpaRepository<Vending,Long> {

    @Query("SELECT COUNT(c) FROM Vending c")
    Long countVending();

    @Query(value = """
        SELECT MONTH(vending_date) AS mes, SUM(total_amount) AS total
        FROM vending
        GROUP BY MONTH(vending_date)
        ORDER BY mes
        """, nativeQuery = true)
    List<Object[]> getTotalSalesPerMonth();


    @Query("SELECT SUM(c.totalAmount) FROM Vending c")
    Double countTotalVending();

    @Query("SELECT AVG(v.totalAmount) FROM Vending v")
    int getAverageSales();

//    @Query("SELECT v FROM Vending v WHERE v.vending_date = :vending_date")
//    List<Vending> findByDate(LocalDate vending_date);

//    @Query("SELECT v FROM Vending v WHERE v.vending_date BETWEEN :inicio AND :fin")
//    List<Vending> findBetweenDate(LocalDate inicio, LocalDate fin);

//    List<Vending> findByVendingDateBetween(LocalDateTime startDate, LocalDateTime endDate);
//
//    List<Vending> findByClientId(Long clientId);
//
//    List<Vending> findByEmployeeId(Long employeeId);

//    @Query("SELECT v FROM Vending v ORDER BY v.vendingDate DESC")
//    List<Vending> findAllOrderByDateDesc();
}
