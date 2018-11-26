package ru.home.taskrcr.services;

import ru.home.taskrcr.domain.PrintingDocument;

import java.util.List;

public interface PrintDocService {
    PrintingDocument getBookById(Integer id);
    void saveDocument(PrintingDocument document);
    List<PrintingDocument> findAllDocuments();
    List<PrintingDocument> findAllByOrderByTimeprintAsc();
    List<PrintingDocument> findAllByOrderByTimeprintDesc();
}
