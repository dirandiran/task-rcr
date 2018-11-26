package ru.home.taskrcr.sharedSource;

import ru.home.taskrcr.dto.DocumentDTO;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedPrintResources {
    public static int print_time = 0;
    public static Map<Integer, DocumentDTO> printingDocsDTO = new ConcurrentHashMap<>();
}
