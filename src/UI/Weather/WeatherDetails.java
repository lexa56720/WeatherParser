package UI.Weather;

import DataTypes.WeatherInfo;
import UI.Weather.Components.WeatherDetailComponent;

import javax.swing.*;
import java.awt.*;

public class WeatherDetails extends JPanel
{

    public WeatherDetails()
    {
        super();
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    }


    public void Update(WeatherInfo info)
    {
        removeAll();
        var states=info.GetWeatherStates();

        for(var state : states)
        {
            add(Box.createRigidArea(new Dimension(5, 0)));
            add(new WeatherDetailComponent(state,info.GetMaxWater()));
        }

        revalidate();
    }



}
