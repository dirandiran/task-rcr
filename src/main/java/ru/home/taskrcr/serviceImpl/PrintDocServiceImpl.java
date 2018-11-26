package ru.home.taskrcr.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.taskrcr.domain.PrintingDocument;
import ru.home.taskrcr.repository.PrintingDocRepository;
import ru.home.taskrcr.services.PrintDocService;

import java.util.List;

@Service
public class PrintDocServiceImpl implements PrintDocService {

    PrintingDocRepository printingDocRepository;

    @Autowired
    public PrintDocServiceImpl(PrintingDocRepository printingDocRepository){
        this.printingDocRepository = printingDocRepository;
    }

    @Override
    public PrintingDocument getBookById(Integer id) {
        return printingDocRepository.getOne(id);
    }

    @Override
    public void saveDocument(PrintingDocument document) {
        printingDocRepository.save(document);
    }

    @Override
    public List<PrintingDocument> findAllDocuments() {
        return printingDocRepository.findAll();
    }

    @Override
    public List<PrintingDocument> findAllByOrderByTimeprintAsc() {
        return printingDocRepository.findAllByOrderByTimeprintAsc();
    }

    @Override
    public List<PrintingDocument> findAllByOrderByTimeprintDesc() {
        return printingDocRepository.findAllByOrderByTimeprintDesc();
    }
}
