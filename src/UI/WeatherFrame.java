package UI;

import javax.swing.*;
import java.awt.*;

public class WeatherFrame extends JPanel
{

    private WeatherDetails Details=new WeatherDetails();

    private WeatherSummary Summary=new WeatherSummary();
    public WeatherFrame()
    {
        super();
        this.setBackground(Color.blue);
        this.setLayout(new GridBagLayout());

        setupSummary();
        setupDetails();

    }


    private void setupSummary()
    {
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.3;
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
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridheight=1;
        this.add(Details, c);
    }



}
