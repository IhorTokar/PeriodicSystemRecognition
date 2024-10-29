package com.example.periodicsystemrecognition;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PeriodicSystemRecognition {
    private static final double H = 0.005; // Time step

    public List<Double> calculatePeriod(double x0, double v0, double maxTime) {
        List<Double> zeroCrossingTimes = new ArrayList<>();
        List<IterationData> iterationsData = new ArrayList<>();
        double x = x0;
        double v = v0;
        double t = 0;

        while (t < maxTime) {
            double nextX = x;
            double nextV = v;
            for (int i = 0; i < 10; i++) {
                double fnX = nextX - x - H * nextV;
                double fnV = nextV - v + H * nextX;

                double jacobian[][] = {
                        {1, -H},
                        {H, 1}
                };
                // Save iteration data
                String jacobianString = String.format("%.4f, %.4f", jacobian[0][0], jacobian[1][1]);
                iterationsData.add(new IterationData(i, nextX, nextV, jacobianString));

                nextX -= fnX / jacobian[0][0];
                nextV -= fnV / jacobian[1][1];
            }
            if (x * nextX < 0) {
                zeroCrossingTimes.add(t);
            }
            x = nextX;
            v = nextV;
            t += H;
        }

        showResultsWindow(iterationsData);

        return zeroCrossingTimes;
    }

    private void showResultsWindow(List<IterationData> iterationsData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultsView.fxml"));
            Parent root = loader.load();

            ResultsController controller = loader.getController();
            for (IterationData data : iterationsData) {
                controller.addData(data);
            }

            Stage stage = new Stage();
            stage.setTitle("Результати ітерації");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PeriodicSystemRecognition psr = new PeriodicSystemRecognition();
        List<Double> zeroCrossings = psr.calculatePeriod(1.0, 0.0, 10.0);
        System.out.println("Кількість перетинань нулів: " + zeroCrossings);
    }
}
