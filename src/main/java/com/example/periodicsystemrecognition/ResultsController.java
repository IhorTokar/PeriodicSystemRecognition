package com.example.periodicsystemrecognition;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ResultsController {
    @FXML
    private TableView<IterationData> resultsTable;

    @FXML
    private TableColumn<IterationData, Integer> iterationColumn;

    @FXML
    private TableColumn<IterationData, Double> nextXColumn;

    @FXML
    private TableColumn<IterationData, Double> nextVColumn;

    @FXML
    private TableColumn<IterationData, String> jacobianColumn;

    public void initialize() {
        iterationColumn.setCellValueFactory(new PropertyValueFactory<>("iteration"));
        nextXColumn.setCellValueFactory(new PropertyValueFactory<>("nextX"));
        nextVColumn.setCellValueFactory(new PropertyValueFactory<>("nextV"));
        jacobianColumn.setCellValueFactory(new PropertyValueFactory<>("jacobian"));
    }

    public void addData(IterationData data) {
        resultsTable.getItems().add(data);
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) resultsTable.getScene().getWindow();
        stage.close();
    }
}
