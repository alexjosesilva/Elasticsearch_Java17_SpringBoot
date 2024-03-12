package com.example.demo.controller;

import com.example.demo.model.PdfDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PdfIndexerService;
import com.example.demo.repository.PdfDocumentRepository;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/index")
public class PdfIndexController {

    @Autowired
    private PdfIndexerService pdfIndexerService;

    @Autowired
    private PdfDocumentRepository pdfDocumentRepository;

    @GetMapping("/pdf")
    public String indexPdfFiles() {
        try {
            pdfIndexerService.indexPdfFiles();
            return "PDF files indexed successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error indexing PDF files: " + e.getMessage();
        }
    }

    @GetMapping("/search")
    public List<PdfDocument> searchPdfByText(@RequestParam("keyword") String keyword) {
        return pdfDocumentRepository.findByTextContaining(keyword);
    }
}

