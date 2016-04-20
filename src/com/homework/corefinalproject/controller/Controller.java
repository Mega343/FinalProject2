
package com.homework.corefinalproject.controller;

        import com.homework.corefinalproject.extremum.ExtremumTypes;
        import com.homework.corefinalproject.extremum.ExtremumSearcher;
        import com.homework.corefinalproject.parser.formula.FormulaParser;
        import com.homework.corefinalproject.parser.function.Function;
        import com.homework.corefinalproject.util.UserInputReader;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.chart.LineChart;
        import javafx.scene.control.*;

        import java.net.URL;
        import java.util.ResourceBundle;

public class Controller implements Initializable{

    public ToggleGroup extremumType;
    public TextField formulaField;
    public TextField intervalStartField;
    public TextField intervalEndField;

    public TextField calculationAccuracyField;
    public Label resultLabel;

    public LineChart chart;

    public RadioButton extremumTypeMax;
    public RadioButton extremumTypeMin;

    private Function function;

    //private String formulaField;
    private int intervalStart;
    private int intervalEnd;
    private double calculationAccuracy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final int defaultIntervalStart = 1;
        final double defaultCalculationAccuracy = 0.01;
        final int defaultIntervalEnd = 10;

        intervalStartField.setText(String.valueOf(defaultIntervalStart));
        intervalEndField.setText(String.valueOf(defaultIntervalEnd));
        calculationAccuracyField.setText(String.valueOf(defaultCalculationAccuracy));

    }

    @FXML
    public void findExtremum(){

        try {
            readUserInput();
            makeFunction();
            makeResultDescription();
            makeChart();
        } catch (Exception e){
            resultLabel.setText(e.getMessage());
        }

    }

    private void readUserInput(){

        intervalStart = UserInputReader.parseInt(intervalStartField.getText(), "Interval start");
        intervalEnd = UserInputReader.parseInt(intervalEndField.getText(), "Interval end");
        calculationAccuracy = UserInputReader.parseDouble(calculationAccuracyField.getText(), "Calculation accuracy");

    }

    private void makeChart(){

        LineChartMaker chartMaker = new LineChartMaker();

        chartMaker.setChart(chart);
        chartMaker.setFunction(function);
        chartMaker.setInterval(intervalStart, intervalEnd);

        chartMaker.fillChart();

    }

    private void makeFunction(){

        FormulaParser parser = new FormulaParser(formulaField.getText());
        function = parser.parse();

    }

    private void makeResultDescription() {

        ExtremumSearcher searcher = new ExtremumSearcher();
        double result = searcher.findExtremum(intervalStart, intervalEnd, getCurrentExtremumType(), calculationAccuracy, function);

        resultLabel.setText(getCurrentExtremumType().toString()
                + " = "+ String.format("%.1f", result));

    }

    private ExtremumTypes getCurrentExtremumType(){

        if (extremumType.getSelectedToggle().equals(extremumTypeMin)) {
            return ExtremumTypes.MIN;
        } else {
            return ExtremumTypes.MAX;
        }
    }
}