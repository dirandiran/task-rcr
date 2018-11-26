package ru.home.taskrcr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.home.taskrcr.domain.Document;
import ru.home.taskrcr.domain.PrintingDocument;
import ru.home.taskrcr.services.DocService;
import ru.home.taskrcr.services.PrintDocService;
import ru.home.taskrcr.sharedSource.PrinterEmul;
import ru.home.taskrcr.sharedSource.SharedPrintResources;
import ru.home.taskrcr.util.PrinterUtil;

import java.util.List;

@Controller
public class MainController/* implements ErrorController */ {

    DocService docservice;
    PrintDocService printDocService;
    private static PrinterEmul printer = PrinterUtil.getPrinter();
    private static String sortTimeprintMethod = "ASC";
    private static final String PATH = "/error";
    private static boolean START = false;

    @Autowired
    public MainController(DocService docservice, PrintDocService printDocService) {
        this.docservice = docservice;
        this.printDocService = printDocService;
    }

    @GetMapping("/")
    public String listDoc(Model model) {
        List<Document> documents = sortDocs(sortTimeprintMethod);
        model.addAttribute("documents", documents);
        model.addAttribute("sort", sortTimeprintMethod);
        return "index";
    }

    @GetMapping("/sort/{sortType}")
    public String sortChoose(@PathVariable String sortType) {
        sortTimeprintMethod = sortType;
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void startprint() {
        printer.printing(docservice.findAllDocuments());
    }

    @PostMapping("/stop")
    public String stopprint() {
        printer.stopPrinting();
        System.out.println("Напечатаны: ");
        for (Integer id : SharedPrintResources.printingDocsDTO.keySet()) {
            System.out.println(SharedPrintResources.printingDocsDTO.get(id).getTypedocument());
            PrintingDocument printingDocument = new PrintingDocument(SharedPrintResources.printingDocsDTO.get(id).getTimeprint(),
                    SharedPrintResources.printingDocsDTO.get(id).getTypedocument(),
                    SharedPrintResources.printingDocsDTO.get(id).getFormatpage());
            printDocService.saveDocument(printingDocument);
            SharedPrintResources.printingDocsDTO.remove(id);
        }
        System.out.println("За " + SharedPrintResources.print_time);
        return "redirect:/result";
    }
    @GetMapping("/result")
    public String listResultDoc(Model model) {
        List<PrintingDocument> documents = printDocService.findAllByOrderByTimeprintAsc();
        model.addAttribute("documents", documents);
        model.addAttribute("printtime", SharedPrintResources.print_time + " сек.");
        return "result";
    }

    @PostMapping("/show")
    public String showResult() {
        for (Integer id : SharedPrintResources.printingDocsDTO.keySet()) {
            PrintingDocument printingDocument = new PrintingDocument(SharedPrintResources.printingDocsDTO.get(id).getTimeprint(),
                    SharedPrintResources.printingDocsDTO.get(id).getTypedocument(),
                    SharedPrintResources.printingDocsDTO.get(id).getFormatpage());
            printDocService.saveDocument(printingDocument);
            SharedPrintResources.printingDocsDTO.remove(id);
        }
        return "redirect:/result";
    }

    @PostMapping("/push")
    public String pushDocToPrint(@RequestParam int timeprint, String typedoc, String formatpage) {
        Document document = new Document();
        document.setTimeprint(timeprint);
        document.setTypedocument(typedoc);
        document.setFormatpage(formatpage);
        docservice.saveDocument(document);
        printer.pushDoc(document);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        printer.popDoc(docservice.getDocById(id));
        docservice.deleteDocument(id);
        return "redirect:/";
    }

    private List<Document> sortDocs(String sortTimeprintMethod) {
        List<Document> docs = null;
        switch (sortTimeprintMethod) {
            case "ASC":
                docs = docservice.findAllByOrderByTimeprintAsc();
                break;
            case "DESC":
                docs = docservice.findAllByOrderByTimeprintDesc();
                break;
        }
        return docs;
    }
}
