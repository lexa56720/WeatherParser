package UI.Weather.Components;

import DataTypes.WeatherState;

import javax.swing.*;
import java.awt.*;

import static Utils.DateConverter.GetTime;

public class WeatherDetailComponent extends JPanel
{

    WeatherState State;
    public WeatherDetailComponent(WeatherState state,float maxWaterLevel)
    {
        super();
        State=state;

        setBackground(Color.WHITE);
        setLayout( new GridLayout(5,1));
        SetupLayout(maxWaterLevel);
    }

    private void SetupLayout(float maxWaterLevel)
    {
        add(CreateTextField(GetTime(State.getDateTime())));
        add(new JLabel(GetImage()));

        add(CreateTextField(State.getTemperature()+"°"));
        add(CreateTextField(State.getWind()+" м/c"));
        add(new WaterComponent(State.getPrecipitation(),maxWaterLevel));
    }
    private JTextField CreateTextField(String text)
    {
        var textField = new JTextField(text);
        textField.setEditable(false);
        textField.setOpaque(false);
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        return textField;
    }

    private ImageIcon GetImage()
    {
        String file = "";
        switch (State.getSkyState())
        {
            case Sunny -> file = "sunny.png";
            case Cloudy -> file = "cloudy.png";
            case Raining -> file = "raining.png";
        }
        ImageIcon imageIcon = new ImageIcon("Resources/" + file);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

}
