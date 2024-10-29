package com.example.periodicsystemrecognition;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class HelloController {
    @FXML
    private TextField xInput;

    @FXML
    private TextField vInput;

    @FXML
    private Label resultLabel;

    @FXML
    private Label periodLabel;
    @FXML
    private Label zeroCrossingsLabel;

    @FXML
    private void calculatePeriod() {
        try {
            double x = Double.parseDouble(xInput.getText());
            double v = Double.parseDouble(vInput.getText());

            PeriodicSystemRecognition psr = new PeriodicSystemRecognition();
            List<Double> zeroCrossings = psr.calculatePeriod(x, v, 10.0);

            // Clear previous results
            periodLabel.setText("Період: ");
            zeroCrossingsLabel.setText("Перетворення нуля: ");

            if (zeroCrossings.size() >= 2) {
                double period = 2 * (zeroCrossings.get(1) - zeroCrossings.get(0));
                periodLabel.setText(String.format("Період: %.4f", period));
            } else {
                periodLabel.setText("Не достатньо даних для виявлення періоду.");
            }

            // Display zero crossing times
            StringBuilder crossingsText = new StringBuilder("Перетинання нулів: ");
            for (Double time : zeroCrossings) {
                crossingsText.append(String.format("%.4f ", time));
            }
            zeroCrossingsLabel.setText(crossingsText.toString());
        } catch (NumberFormatException e) {
            resultLabel.setText("Будь ласка, введіть дійсні числа для x та v.");
        }
    }
}
