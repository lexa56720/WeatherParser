package UI;

import DataTypes.CityInfo;
import Utils.IEventSubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame
{
    SearchPanel Search = new SearchPanel();
    WeatherFrame Weather = new WeatherFrame();

    JFrame Frame;

    public MainFrame()
    {
        Frame = new JFrame("Погода");
        Frame.setLayout(new GridBagLayout());
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SetupSearch();
        SetupWeather();

        Search.CitySelected.Subscribe((data, Sender) -> Weather.EventRaised(data,Sender));

        Frame.setSize(1000, 800);
    }

    private void SetupSearch()
    {
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets=new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth =1;
        Frame.add(Search, c);
    }

    private void SetupWeather()
    {
        var c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.insets=new Insets(5,0,5,5);
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = 1;
        Frame.add(Weather, c);
    }
}
