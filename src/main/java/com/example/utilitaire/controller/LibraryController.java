package com.example.utilitaire.controller;

import com.example.utilitaire.objet.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    @FXML
    private Button btnAdd;
    @FXML
    private TableColumn tabTitle;
    @FXML
    private TableColumn tabAuthor;
    @FXML
    private TableColumn tabColumn;
    @FXML
    private TableColumn tabRow;
    @FXML
    private TableColumn tabPublication;
    @FXML
    private TableColumn tabPlotSummary;
    @FXML
    private TableView<Book> tblViewBooks;
    @FXML
    private Button btnSendBook;
    @FXML
    private Label lblError;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtColumn;
    @FXML
    private TextField txtPublication;
    @FXML
    private TextField txtRow;
    @FXML
    public TextField txtTitle;
    @FXML
    private TextArea txtAPSummary;
    @FXML
    private HBox bookInputForm;
    @FXML
    private AnchorPane ancLibrary;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Book> bookArrayList = new ArrayList<Book>();
        tabTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tabAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("autor"));
        tabColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("column"));
        tabRow.setCellValueFactory(new PropertyValueFactory<Book, Integer>("row"));
        tabPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("publication"));
        tabPlotSummary.setCellValueFactory(new PropertyValueFactory<Book, String>("plotSummary"));

        ancLibrary.getChildren().removeAll(bookInputForm);
        btnAdd.setOnMouseClicked(launchForm -> {
            lblError.setText("");
            txtTitle.clear();
            txtAuthor.clear();
            txtColumn.clear();
            txtRow.clear();
            txtPublication.clear();
            txtAPSummary.clear();

            if(!ancLibrary.getChildren().contains(bookInputForm)) {
                ancLibrary.getChildren().removeAll(bookInputForm);
                ancLibrary.getChildren().add(bookInputForm);
            }
        });

        btnSendBook.setOnMouseClicked(sendBook -> {
            Book newBook = null;

            try {
                newBook = new Book(txtTitle.getText(), txtAuthor.getText(), Integer.parseInt(txtColumn.getText()), Integer.parseInt(txtRow.getText()), txtPublication.getText(), txtAPSummary.getText());
                bookArrayList.add(newBook);
                System.out.println(bookArrayList);
                tblViewBooks.getItems().add(newBook);
                txtTitle.clear();
                txtAuthor.clear();
                txtColumn.clear();
                txtRow.clear();
                txtPublication.clear();
                txtAPSummary.clear();
                ancLibrary.getChildren().removeAll(bookInputForm);
            } catch (Exception e) {
                txtTitle.clear();
                txtAuthor.clear();
                txtColumn.clear();
                txtRow.clear();
                txtPublication.clear();
                txtAPSummary.clear();
                ancLibrary.getChildren().removeAll(bookInputForm);
                lblError.setText("Please enter numerical value for column and row");
            }
        });

        tblViewBooks.setOnMouseClicked(clickedRow -> {
            if(clickedRow.getClickCount() >= 1) {
                if (tblViewBooks.getSelectionModel().getSelectedItem() != null) {
                    if(!ancLibrary.getChildren().contains(bookInputForm)) {
                        ancLibrary.getChildren().removeAll(bookInputForm);
                        ancLibrary.getChildren().add(bookInputForm);
                    }
                    Book clickedBook = tblViewBooks.getSelectionModel().getSelectedItem();
                    txtTitle.setText(clickedBook.getTitle());
                    txtAuthor.setText(clickedBook.getAutor());
                    txtColumn.setText(String.valueOf(clickedBook.getColumn()));
                    txtRow.setText(String.valueOf(clickedBook.getRow()));
                    txtPublication.setText(clickedBook.getPublication());
                    txtAPSummary.setText(clickedBook.getPlotSummary());
                }
            }
        });
    }
}