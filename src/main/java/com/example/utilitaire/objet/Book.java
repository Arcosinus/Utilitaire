package com.example.utilitaire.objet;

public class Book {
    private String title;
    private String autor;
    private int column;
    private int row;
    private String publication;
    private String plotSummary;
    private String url;

    // Book constructor
    public Book(String title, String author, int column, int row, String publication, String url, String plotSummary) {
        this.title = title;
        this.autor = author;
        this.column = column;
        this.row = row;
        this.publication = publication;
        this.url = url;
        this.plotSummary = plotSummary;
    }

    // Book getters
    public String getTitle() {
        return title;
    }
    public String getAutor() {
        return autor;
    }
    public String getPlotSummary() {
        return plotSummary;
    }
    public int getColumn() { return column; }
    public int getRow() {
        return row;
    }
    public String getUrl() { return url; }
    public String getPublication() {
        return publication;
    }

    // Book setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setPlotSummary(String plotSummary){
        this.plotSummary = plotSummary;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setUrl(String url) { this.url = url; }
    public void setPublication(String publication) {
        this.publication = publication;
    }

    // To string method
    public String toString() {
        return "Title : " + getTitle() + " " + "Author : " + getAutor() +  " " + "Column : " + getColumn() + " " + "Row : " + getRow() + " " + "Publication : " + getPublication() + " " + "Plot summary : " + getPlotSummary();
    }
}