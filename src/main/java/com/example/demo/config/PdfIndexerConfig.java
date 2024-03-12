package com.example.demo.config;

import com.example.demo.service.PdfIndexerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class PdfIndexerConfig {

    @Autowired
    private PdfIndexerService pdfIndexerService;

    @PostConstruct
    public void init() throws IOException {
        pdfIndexerService.indexPdfFiles();
    }
}

