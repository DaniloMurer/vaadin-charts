package com.danilojakob.vaadin.charts.views.helloworld;

import com.danilojakob.vaadin.charts.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends VerticalLayout {

    public HelloWorldView() {
        setSpacing(true);


        add(createTimeLineChart(), createStackedColumnChart(), createDonutChart());
    }

    private Chart createDonutChart() {
        Chart donutChart = new Chart(ChartType.PIE);
        Configuration donutChartConfiguration = donutChart.getConfiguration();
        PlotOptionsPie plotOptionsPie = new PlotOptionsPie();
        plotOptionsPie.setInnerSize("80%");
        donutChartConfiguration.setTitle("Donut Chart");
        donutChartConfiguration.getTooltip().setEnabled(true);
        donutChartConfiguration.getLegend().setEnabled(true);
        donutChartConfiguration.setPlotOptions(plotOptionsPie);
        DataSeries donutSeries = new DataSeries();
        donutSeries.add(new DataSeriesItem("Firefox", 45.0));
        donutSeries.add(new DataSeriesItem("IE", 26.8));
        donutSeries.add(new DataSeriesItem("Chrome", 12.8));
        donutSeries.add(new DataSeriesItem("Safari", 8.5));
        donutSeries.add(new DataSeriesItem("Opera", 6.2));
        donutSeries.add(new DataSeriesItem("Others", 0.7));
        donutChartConfiguration.addSeries(donutSeries);

        return donutChart;

    }

    private Chart createStackedColumnChart() {
        Chart stackedColumnChart = new Chart(ChartType.COLUMN);
        Configuration stackedColumnChartConfiguration = stackedColumnChart.getConfiguration();
        ListSeries seriesA = new ListSeries("Series A", 300, 150, 550);
        ListSeries seriesB = new ListSeries("Series B", 200, 50, 500);
        DataSeries seriesC = new DataSeries("Series C");
        for (int i = 0; i < 3; i++) {
            DataSeriesItem item = new DataSeriesItem();
            item.setY(300);
            item.setX(i);
            item.setColorIndex(5);
            seriesC.add(item);
        }
        stackedColumnChartConfiguration.setTitle("Stacked Column Chart");
        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setStacking(Stacking.NORMAL);
        stackedColumnChartConfiguration.setPlotOptions(plotOptionsColumn);
//        seriesA.setPlotOptions(plotOptionsColumn);
//        seriesB.setPlotOptions(plotOptionsColumn);
//        seriesC.setPlotOptions(plotOptionsColumn);
        stackedColumnChartConfiguration.addSeries(seriesA);
        stackedColumnChartConfiguration.addSeries(seriesB);
        stackedColumnChartConfiguration.addSeries(seriesC);

        return stackedColumnChart;
    }

    private Chart createTimeLineChart() {
        Chart timelineChart = new Chart(ChartType.LINE);
        Configuration timelineChartConfiguration = timelineChart.getConfiguration();
        timelineChartConfiguration.setTitle("Timeline Chart");


        XAxis xAxis = new XAxis();
        xAxis.setCategories("01.01.2022", "02.01.2022", "03.01.2022", "04.01.2022", "05.01.2022", "06.01.2022");
        timelineChartConfiguration.addxAxis(xAxis);
        timelineChartConfiguration.getyAxis().setVisible(false);

        ListSeries listSeries = new ListSeries("Timeline", 0, 0, 0, 0, 0, 0);
        listSeries.setId("timeline");
        timelineChartConfiguration.addSeries(listSeries);
        timelineChartConfiguration.getTooltip().setEnabled(true);

        DataSeries flagSeries = new DataSeries("Flag Series");
        PlotOptionsFlags plotOptionsFlags = new PlotOptionsFlags();
        plotOptionsFlags.setOnSeries("timeline");
        plotOptionsFlags.setShape(FlagShape.CALLOUT);
        flagSeries.setPlotOptions(plotOptionsFlags);
        flagSeries.add(new FlagItem(1, "02.01.22 - First Step Done"));
        flagSeries.add(new FlagItem(4, "05.01.22 - Second Step Done"));
        flagSeries.add(new FlagItem(5, "06.01.22 - Third Step Done"));

        timelineChartConfiguration.addSeries(flagSeries);
        return timelineChart;
    }

}
