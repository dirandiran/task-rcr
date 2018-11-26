package ru.home.taskrcr.util;

import ru.home.taskrcr.services.PrintDocService;
import ru.home.taskrcr.sharedSource.PrinterEmul;

public class PrinterUtil {

    private static final PrinterEmul printer;
    static {
        printer = new PrinterEmul();
    }

    public static PrinterEmul getPrinter(){
        return printer;
    }
}
