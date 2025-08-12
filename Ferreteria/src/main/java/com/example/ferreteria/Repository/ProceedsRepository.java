package com.example.ferreteria.Repository;

import com.example.ferreteria.Model.Proceeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProceedsRepository extends JpaRepository<Proceeds,Long>  {

    @Query("SELECT COUNT(c) FROM Proceeds c")
    Long countProceeds();
}
