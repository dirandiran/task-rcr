package ru.home.taskrcr.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class PrintingDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public PrintingDocument(){}

    public PrintingDocument(int timeprint, String typedocument, String formatpage ){
        this.timeprint = timeprint;
        this.typedocument = typedocument;
        this.formatpage = formatpage;
    }
    private int timeprint;

    private String typedocument;

    private String formatpage;
}
