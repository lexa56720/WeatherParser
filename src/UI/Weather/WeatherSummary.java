package UI.Weather;

import DataTypes.WeatherInfo;
import UI.Weather.Components.WeatherComponent;
import Utils.Event;
import Utils.IEventSubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class WeatherSummary extends JPanel implements IEventSubscriber<WeatherInfo>
{

    Event<WeatherInfo> SelectedDayChanged = new Event<>();
    private LinkedList<WeatherComponent> WeatherDays = new LinkedList<>();

    private JPanel DayList = new JPanel();

    public WeatherSummary()
    {
        super();
        setLayout(new BorderLayout());


        DayList.setLayout(new BoxLayout(DayList, BoxLayout.X_AXIS));

        var scrollPane = new JScrollPane(DayList);
        scrollPane.setBorder(createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void Update(WeatherInfo[] infos)
    {
        Clear();
        Add(infos);
        revalidate();
    }

    private void Clear()
    {
        for(var day : WeatherDays)
            day.WeatherClicked.UnSubscribe(this);
        DayList.removeAll();
        WeatherDays.clear();
    }

    private void Add(WeatherInfo[] infos)
    {
        for (var info : infos)
        {
            var component=new WeatherComponent(info);
            component.WeatherClicked.Subscribe(this);
            WeatherDays.add(component);
            DayList.add(component);
        }
    }

    @Override public void EventRaised(WeatherInfo data, Object Sender)
    {
        SelectedDayChanged.NotifyAll(data,this);
    }
}
