package ru.home.taskrcr.dto;

import lombok.Getter;
import lombok.Setter;

import ru.home.taskrcr.sharedSource.SharedPrintResources;

import java.util.concurrent.Semaphore;


public class DocumentDTO extends Thread {
    private Semaphore sem;

    @Setter
    @Getter
    private int iD;

    @Getter
    @Setter
    private int timeprint;

    @Getter
    @Setter
    private String typedocument;

    @Getter
    @Setter
    private String formatpage;

    public DocumentDTO(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
            System.out.println("Документ типа " + typedocument + " получает разрешение на печать");
            System.out.println("Печатаем...");
            this.sleep(timeprint * 1000);
            System.out.println("Документ типа " + typedocument + " напечатан за " + timeprint + "сек.");
            SharedPrintResources.printingDocsDTO.put(iD, this);
            SharedPrintResources.print_time += timeprint;
            sem.release();
        } catch (InterruptedException exc) {
            this.stop();
            try {
                this.iD = (Integer)null;
                this.timeprint = (Integer)null;
                this.formatpage = null;
                this.sem = null;
                this.typedocument = null;
                this.finalize();
            }catch (Throwable throwable){

            }
            System.out.println(exc);
        }
    }
}
