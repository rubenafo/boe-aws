package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Meta {

    @JsonIgnore
    private final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String pub;
    private int anno;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fecha;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaAnt;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaAntAnt;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaSig;
    private String fechaPub;
    private String pubDate;

    public Meta(Node node) {
        Element e = (Element) node;
        for (int i = 0; i < e.getChildNodes().getLength(); i++) {
            Node child = e.getChildNodes().item(i);
            if (child.hasChildNodes()) {
                String nodeName = child.getNodeName();
                String value = child.getFirstChild().getNodeValue();
                switch (nodeName) {
                    case "pub":
                        this.pub = value;
                        break;
                    case "anno":
                        this.anno = Integer.valueOf(value);
                        break;
                    case "fecha":
                        this.fecha = LocalDate.parse(value, DEFAULT_FORMAT);
                        break;
                    case "fechaAnt":
                        this.fechaAnt = LocalDate.parse(value, DEFAULT_FORMAT);
                        break;
                    case "fechaAntAnt":
                        this.fechaAntAnt = LocalDate.parse(value, DEFAULT_FORMAT);
                        break;
                    case "fechaSig":
                        this.fechaSig = LocalDate.parse(value, DEFAULT_FORMAT);
                        break;
                    case "fechaPub":
                        this.fechaPub = value; break;
                    case "pubDate":
                        this.pubDate = value; break;
                }
            }
        }
    }
}
