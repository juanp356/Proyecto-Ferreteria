package com.example.ferreteria.service;

import com.example.ferreteria.Model.Vending;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

//    public byte[] exportarPDF(String plantilla, Map<String, Object> params, List<?> datos) throws Exception {
//        InputStream jrxmlStream = new ClassPathResource("reportes/" + plantilla + ".jrxml").getInputStream();
//        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
//
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }
}
