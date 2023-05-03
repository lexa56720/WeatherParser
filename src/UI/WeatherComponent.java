package UI;

import DataTypes.WeatherInfo;
import Utils.DateConverter;
import Utils.Event;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class WeatherComponent extends JButton
{
    public Event<WeatherInfo> WeatherClicked = new Event<>();
    private final WeatherInfo Info;

    private JLabel Icon;

    public WeatherComponent(WeatherInfo info)
    {
        super();
        setLayout(new GridBagLayout());
        Info = info;

        SetupLayout();
        addMouseListener(new MouseAdapter()
        {
            @Override public void mouseClicked(MouseEvent e)
            {
                WeatherClicked.NotifyAll(Info, this);
            }
        });
    }

    private void HandleMouseEvent(MouseEvent e)
    {
        e.setSource(this);
        this.processMouseEvent(e);
    }

    private JTextField CreateTextField(String text, Color color)
    {
        var textField = new ExtendedJTextField(text,18);
        textField.addMouseListener(new MouseInputListener()
        {
            @Override public void mouseClicked(MouseEvent e)
            {
                HandleMouseEvent(e);
            }
            @Override public void mousePressed(MouseEvent e)
            {
                HandleMouseEvent(e);
            }
            @Override public void mouseReleased(MouseEvent e)
            {
                HandleMouseEvent(e);
            }
            @Override public void mouseEntered(MouseEvent e)
            {
                HandleMouseEvent(e);
            }
            @Override public void mouseExited(MouseEvent e)
            {
                HandleMouseEvent(e);
            }
            @Override public void mouseDragged(MouseEvent e)
            {
                HandleMouseEvent(e);
            }
            @Override public void mouseMoved(MouseEvent e)
            {
                HandleMouseEvent(e);
            }
        });
        return textField;
    }
    private void SetupLayout()
    {
        var grid = GetGrid();
        grid.gridwidth = 2;
        grid.gridheight = 1;
        grid.weighty = 1;
        grid.anchor = GridBagConstraints.CENTER;

        var dateLabel = CreateTextField(DateConverter.ConvertDate(Info.GetDate()), Color.WHITE);
        add(dateLabel, grid);


        grid.anchor = GridBagConstraints.PAGE_START;
        grid.gridy++;
        Icon = new JLabel(GetImage());
        add(Icon, grid);


        grid.gridy++;
        grid.gridwidth = 1;
        grid.weightx = 1;
        grid.gridx = 0;
        var maxTempLabel = CreateTextField(Info.GetMaxTemp() + "°", Color.WHITE);
        maxTempLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(maxTempLabel, grid);

        grid.anchor = GridBagConstraints.EAST;
        grid.gridx = 1;
        var minTempLabel = CreateTextField(Info.GetMinTemp() + "°", Color.WHITE);
        minTempLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(minTempLabel, grid);

    }

    private ImageIcon GetImage()
    {
        String file = "";
        switch (Info.GetSkyState())
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
    private GridBagConstraints GetGrid()
    {
        var grid = new GridBagConstraints();
        grid.gridwidth = 1;
        grid.gridheight = 1;
        grid.weightx = 1;
        grid.weighty = 1;
        grid.gridy = 0;
        grid.gridx = 0;
        grid.insets.top = 5;
        grid.insets.bottom = 5;
        grid.insets.right = 5;
        grid.insets.left = 5;

        grid.fill = GridBagConstraints.BOTH;
        return grid;
    }

}
