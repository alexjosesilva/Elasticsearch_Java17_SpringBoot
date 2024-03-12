package com.example.demo.service;

import com.example.demo.repository.PdfDocumentRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import com.example.demo.model.PdfDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfIndexerService {

    @Autowired
    private PdfDocumentRepository pdfDocumentRepository; // Supondo que você tenha um repositório para armazenar os documentos indexados no Elasticsearch

    public void indexPdfFiles() throws IOException {
        File pdfFolder = ResourceUtils.getFile("classpath:pdf"); // Localize a pasta pdf no diretório de recursos

        if (pdfFolder.exists() && pdfFolder.isDirectory()) {
            File[] pdfFiles = pdfFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

            if (pdfFiles != null) {
                for (File pdfFile : pdfFiles) {
                    indexPdfFile(pdfFile);
                }
            }
        }
    }

    private void indexPdfFile(File pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            // Indexe o texto no Elasticsearch ou armazene em seu repositório
            PdfDocument pdfDocument = new PdfDocument();
            pdfDocument.setFileName(pdfFile.getName());
            pdfDocument.setText(text);
            pdfDocumentRepository.save(pdfDocument);
        }
    }
}
