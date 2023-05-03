package UI;

import UI.Search.SearchPanel;
import UI.Weather.WeatherFrame;

import javax.swing.*;
import java.awt.*;

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
        c.weightx = 0.5;
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
        c.weightx = 0.5;
        c.gridwidth = 1;
        Frame.add(Weather, c);
    }
}
