package com.example.demo.repository;

import com.example.demo.model.PdfDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfDocumentRepository extends ElasticsearchRepository<PdfDocument, String> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
    List<PdfDocument> findByTextContaining(String keyword);
}
