package com.example.ferreteria.service;

import com.example.ferreteria.Repository.ProceedsRepository;
import org.springframework.stereotype.Service;

@Service
public class ProceedsService {

    private final ProceedsRepository proceedsRepository;

    public ProceedsService(ProceedsRepository proceedsRepository){
        this.proceedsRepository = proceedsRepository;
    }

    public Long GetTotalProceeds(){
        return proceedsRepository.countProceeds();
    }
}
