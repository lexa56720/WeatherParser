package UI;

import DataTypes.WeatherInfo;
import DataTypes.WeatherState;
import Utils.Event;
import Utils.IEventSubscriber;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class WeatherSummary extends JPanel implements IEventSubscriber<WeatherInfo>
{

    Event<WeatherInfo> SelectedDayChanged = new Event<>();
    private LinkedList<WeatherComponent> WeatherDays = new LinkedList<>();

    private JPanel DayList = new JPanel();
    ;
    public WeatherSummary()
    {
        super();
        setLayout(new BorderLayout());


        DayList.setLayout(new BoxLayout(DayList, BoxLayout.X_AXIS));

        var scrollPane = new JScrollPane(DayList);
        add(scrollPane, BorderLayout.CENTER);

        setBackground(Color.YELLOW);
    }

    public void Update(WeatherInfo infos[])
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
