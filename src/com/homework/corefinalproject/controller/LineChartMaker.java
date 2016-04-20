package com.homework.corefinalproject.controller;

import com.homework.corefinalproject.parser.function.Function;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class LineChartMaker {

    private LineChart chart;
    private Function function;

    private int intervalStart;
    private int intervalEnd;

    public void setChart(LineChart chart) {
        this.chart = chart;
    }

    public void setInterval(int intervalStart, int intervalEnd) {

        if (intervalEnd <= intervalStart) {
            throw new IllegalArgumentException("Incorrect interval limits: start "
                    + intervalStart + ", end " + intervalEnd);
        }

        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;

    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public void fillChart(){

        chart.getData().clear();
        setChartViewProperties();

        ObservableList<XYChart.Series<Number, Number>> chartData = chart.getData();
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();

        int stepSize = 1;
        for(int i = intervalStart; i <= intervalEnd; i+= stepSize){

            function.setX(i);
            double y = function.calculate();

            //infinity is line break
            if (y == Double.NEGATIVE_INFINITY ||
                    y == Double.POSITIVE_INFINITY){

                chartData.add(series);
                series = new XYChart.Series<Number, Number>();

            } else {
                series.getData().add(new XYChart.Data<Number, Number>(i,y));
            }

        }

        chartData.add(series);

    }

    private void setChartViewProperties() {

        chart.getXAxis().setLabel("x");
        chart.getYAxis().setLabel("y");

        chart.setLegendVisible(false);
        chart.setCreateSymbols(false);

    }


}