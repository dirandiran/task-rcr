package ru.home.taskrcr.services;

import org.springframework.stereotype.Service;
import ru.home.taskrcr.domain.Document;

import java.util.List;

public interface DocService {
    Document getDocById(Integer id);
    void saveDocument(Document document);
    void deleteDocument(Integer id);
    void updateDocument(Integer id, int timeprint, String typedocument, String formatpage);
    List<Document> findAllDocuments();
    List<Document> findAllByOrderByTimeprintAsc();
    List<Document> findAllByOrderByTimeprintDesc();
}
