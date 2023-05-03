package UI;

import DataTypes.CityInfo;
import DataTypes.WeatherInfo;
import DataTypes.WeatherState;
import Requester.WeatherRequester;
import Utils.IEventSubscriber;

import javax.swing.*;
import java.awt.*;

public class WeatherFrame extends JPanel implements IEventSubscriber<CityInfo>
{
    private WeatherDetails Details=new WeatherDetails();
    private WeatherSummary Summary=new WeatherSummary();

    public WeatherFrame()
    {
        super();
        this.setLayout(new GridBagLayout());

        Summary.SelectedDayChanged.Subscribe((data, Sender) -> Details.Update(data));

        setupSummary();
        setupDetails();
    }


    private void setupSummary()
    {
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridheight=1;
        this.add(Summary, c);
    }

    private void setupDetails()
    {
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.5;
        c.weightx = 0;
        c.gridwidth = 1;
        c.gridheight=1;
        this.add(Details, c);
    }


    private void Update(WeatherInfo[] infos)
    {
        Summary.Update(infos);
        Details.Update(infos[0]);
    }


    @Override public void EventRaised(CityInfo data, Object Sender)
    {
        var weather= WeatherRequester.RequestWeather(data,10);
        Update(weather);
    }
}
