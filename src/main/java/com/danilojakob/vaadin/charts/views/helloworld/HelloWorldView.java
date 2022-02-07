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


        add(createTimeLineChart());
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
