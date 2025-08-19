package com.example.ferreteria.Repository;

import com.example.ferreteria.Model.Purchase_orders;
import com.example.ferreteria.Model.Vending;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface Purchase_ordersRepository {
    @Query("SELECT COUNT(c) FROM Vending c")
    Long counPucharse_orders();

    @Query(value = """
        SELECT MONTH(vending_date) AS mes, SUM(total_amount) AS total
        FROM vending
        GROUP BY MONTH(vending_date)
        ORDER BY mes
        """, nativeQuery = true)
    List<Object[]> getTotalSalesPerMonth();


    @Query("SELECT SUM(c.total_amount) FROM Vending c")
    Double countTotalPucharse_orders();

    @Query("SELECT AVG(v.total_amount) FROM Vending v")
    int getAverageSales();

    @Query("SELECT v FROM Vending v WHERE v.vending_date = :vending_date")
    List<Vending> findByDate(LocalDate Pucharse_orders_date);
}
