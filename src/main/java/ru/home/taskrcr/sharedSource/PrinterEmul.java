package ru.home.taskrcr.sharedSource;

import ru.home.taskrcr.domain.Document;
import ru.home.taskrcr.dto.DocumentDTO;
import ru.home.taskrcr.services.PrintDocService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class PrinterEmul {
    private Map<Integer, DocumentDTO> documentDTOS;// = new ConcurrentLinkedDeque<>();
    Semaphore semaphore = new Semaphore(1, true);

    public PrinterEmul() {
        documentDTOS = new ConcurrentHashMap<>();
    }

    public synchronized void pushDoc(Document document) {
        DocumentDTO documentDTO = new DocumentDTO(semaphore);
        documentDTO.setID(document.getId());
        documentDTO.setTimeprint(document.getTimeprint());
        documentDTO.setFormatpage(document.getFormatpage());
        documentDTO.setTypedocument(document.getTypedocument());
        documentDTOS.put(document.getId(), documentDTO);
        documentDTO.start();
    }

    public synchronized void popDoc(Document document) {
        documentDTOS.remove(document.getId());
    }

    public synchronized void printing(List<Document> documents) {
        if (semaphore.availablePermits() == 0) {
            semaphore.release();
        }
        for (Document doc : documents) {
            DocumentDTO documentDTO = new DocumentDTO(semaphore);
            documentDTO.setID(doc.getId());
            documentDTO.setTimeprint(doc.getTimeprint());
            documentDTO.setFormatpage(doc.getFormatpage());
            documentDTO.setTypedocument(doc.getTypedocument());
            documentDTOS.put(doc.getId(), documentDTO);
            documentDTO.start();
        }
    }

    public synchronized void stopPrinting() {
        for (Integer id : documentDTOS.keySet()) {
            if (documentDTOS.get(id).isAlive())
                documentDTOS.get(id).interrupt();
            documentDTOS.remove(id);
        }
    }
}
