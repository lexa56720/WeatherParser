package UI;

import DataTypes.CityInfo;
import Utils.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchResult extends JPanel
{
    private CityInfo City;
    private JButton Info;

    public Event<CityInfo> CitySelected=new Event<>();
    public SearchResult(CityInfo city)
    {
        super();
        this.setLayout(new BorderLayout());
        this.addMouseListener(new MouseAdapter()
        {
            @Override public void mouseClicked(MouseEvent e)
            {
                CitySelected.NotifyAll(City,this);
            }
        });

        City = city;
        ComponentSetup();
    }

    private void ComponentSetup()
    {
        Info=new JButton(City.GetFullLocation());
        Info.setHorizontalAlignment(SwingConstants.LEFT);
        Info.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                CitySelected.NotifyAll(City,this);
            }
        });
        Info.setFont(new Font(Info.getFont().getName(),Font.PLAIN, 20));
        this.add(Info);
    }


}
