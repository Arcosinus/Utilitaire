package com.example.utilitaire.controller;

import com.example.utilitaire.objet.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
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
    public TextField txtURL;
    @FXML
    private TextArea txtAPSummary;
    @FXML
    private HBox bookInputForm;
    @FXML
    private AnchorPane ancLibrary;
    @FXML
    private ImageView imgView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create necessary resources for the app
        ArrayList<Book> bookArrayList = new ArrayList<Book>();
        tabTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tabAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("autor"));
        tabColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("column"));
        tabRow.setCellValueFactory(new PropertyValueFactory<Book, Integer>("row"));
        tabPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("publication"));
        tabPlotSummary.setCellValueFactory(new PropertyValueFactory<Book, String>("plotSummary"));

        // Clear and empty form
        ancLibrary.getChildren().removeAll(bookInputForm);
        btnAdd.setOnMouseClicked(launchForm -> {
            lblError.setText("");
            txtTitle.clear();
            txtAuthor.clear();
            txtColumn.clear();
            txtRow.clear();
            txtPublication.clear();
            txtURL.clear();
            txtAPSummary.clear();

            if(!ancLibrary.getChildren().contains(bookInputForm)) {
                ancLibrary.getChildren().removeAll(bookInputForm);
                ancLibrary.getChildren().add(bookInputForm);
            }
        });

        // Create new instances of book and add them to the table
        btnSendBook.setOnMouseClicked(sendBook -> {
            Book newBook = null;

            if (Integer.parseInt(txtColumn.getText()) > 5 || Integer.parseInt(txtColumn.getText()) < 1) {
                txtTitle.clear();
                txtAuthor.clear();
                txtColumn.clear();
                txtRow.clear();
                txtPublication.clear();
                txtURL.clear();
                txtAPSummary.clear();
                ancLibrary.getChildren().removeAll(bookInputForm);
                lblError.setText("Please enter numerical value for column (1 to 5)");
            }
            if (Integer.parseInt(txtRow.getText()) > 7 || Integer.parseInt(txtRow.getText()) < 1) {
                txtTitle.clear();
                txtAuthor.clear();
                txtColumn.clear();
                txtRow.clear();
                txtPublication.clear();
                txtURL.clear();
                txtAPSummary.clear();
                ancLibrary.getChildren().removeAll(bookInputForm);
                lblError.setText("Please enter numerical value for row (1 to 7)");
            }

            Date myDate = new Date();
            String dateString = myDate.toString();
            String[] dateStringSplit = dateString.split(" ");
            Boolean tryCa = false;
            try {
                Integer.parseInt(txtPublication.getText());
                tryCa = true;
            } catch(Exception e) {
                System.out.println("Invalid date");
                lblError.setText("Please enter a valid date");
                tryCa = false;
                txtTitle.clear();
                txtAuthor.clear();
                txtColumn.clear();
                txtRow.clear();
                txtPublication.clear();
                txtURL.clear();
                txtAPSummary.clear();
                ancLibrary.getChildren().removeAll(bookInputForm);
            }

            if (Integer.parseInt(txtPublication.getText()) > Integer.parseInt(dateStringSplit[5]) && tryCa) {
                System.out.println("Code sees date");
                txtTitle.clear();
                txtAuthor.clear();
                txtColumn.clear();
                txtRow.clear();
                txtPublication.clear();
                txtURL.clear();
                txtAPSummary.clear();
                ancLibrary.getChildren().removeAll(bookInputForm);
                lblError.setText("Please enter a valid date");

            } else {
                if (tblViewBooks.getSelectionModel().getSelectedItem() == null) {
                    try {
                        newBook = new Book(txtTitle.getText(), txtAuthor.getText(), Integer.parseInt(txtColumn.getText()), Integer.parseInt(txtRow.getText()), txtPublication.getText(), txtURL.getText(), txtAPSummary.getText());
                        bookArrayList.add(newBook);
                        tblViewBooks.getItems().add(newBook);
                        txtTitle.clear();
                        txtAuthor.clear();
                        txtColumn.clear();
                        txtRow.clear();
                        txtPublication.clear();
                        txtURL.clear();
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
                }

                if(tblViewBooks.getSelectionModel().getSelectedItem() != null) {
                    Book clickedBook = tblViewBooks.getSelectionModel().getSelectedItem();
                    System.out.println(clickedBook);
                    clickedBook.setTitle(txtTitle.getText());
                    clickedBook.setAutor(txtAuthor.getText());
                    clickedBook.setColumn(Integer.parseInt(txtColumn.getText()));
                    clickedBook.setRow(Integer.parseInt(txtRow.getText()));
                    clickedBook.setPublication(txtPublication.getText());
                    clickedBook.setPlotSummary(txtAPSummary.getText());
                    tblViewBooks.getItems().removeAll(bookArrayList);
                    tblViewBooks.getItems().addAll(bookArrayList);
                    txtTitle.clear();
                    txtAuthor.clear();
                    txtColumn.clear();
                    txtRow.clear();
                    txtPublication.clear();
                    txtURL.clear();
                    txtAPSummary.clear();
                    ancLibrary.getChildren().removeAll(bookInputForm);
                }
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

        btnDelete.setOnMouseClicked(deleteBook -> {
            Book clickedBook = tblViewBooks.getSelectionModel().getSelectedItem();
            bookArrayList.remove(clickedBook);
            tblViewBooks.getItems().remove(clickedBook);
            txtTitle.clear();
            txtAuthor.clear();
            txtColumn.clear();
            txtRow.clear();
            txtPublication.clear();
            txtURL.clear();
            txtAPSummary.clear();
            ancLibrary.getChildren().removeAll(bookInputForm);
        });
    }
}