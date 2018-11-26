package ru.home.taskrcr.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.taskrcr.repository.DocRepository;
import ru.home.taskrcr.domain.Document;

import ru.home.taskrcr.services.DocService;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private DocRepository docRepository;

    @Autowired
    public void setDocRepository(DocRepository docRepository){
        this.docRepository = docRepository;
    }

    @Override
    public Document getDocById(Integer id) {
        return docRepository.findOne(id);
    }

    @Override
    public void saveDocument(Document document) {

        docRepository.save(document);
    }

    @Override
    public void deleteDocument(Integer id) {
        docRepository.delete(id);
    }

    @Override
    public void updateDocument(Integer id, int timeprint, String typedocument, String formatpage) {
        Document updDoc = docRepository.findOne(id);
        updDoc.setTimeprint(timeprint);
        updDoc.setTypedocument(typedocument);
        updDoc.setFormatpage(formatpage);
        docRepository.save(updDoc);
    }

    @Override
    public List<Document> findAllDocuments() {
        return docRepository.findAll();
    }

    @Override
    public List<Document> findAllByOrderByTimeprintAsc(){
        return docRepository.findAllByOrderByTimeprintAsc();
    }

    @Override
    public List<Document> findAllByOrderByTimeprintDesc(){
        return docRepository.findAllByOrderByTimeprintDesc();
    }
}
