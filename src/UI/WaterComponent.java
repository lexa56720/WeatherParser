package UI;

import javax.swing.*;
import java.awt.*;

public class WaterComponent extends JPanel
{

    private final float water;
    private final float maxWater;
    public WaterComponent(float water, float maxWater)
    {
        super();
        this.water = water;
        this.maxWater = maxWater;
        this.setLayout(new BorderLayout());
        setOpaque(false);
        add(new ExtendedJTextField(water + " мм", 20));
    }

    private int GetHeight()
    {
        var height = getSize().height;
        return (int) ((height / maxWater) * water);
    }

    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Color startColor = new Color(0.25f, 0.53f, 0.96f, 1f);
        Color endColor = new Color(0.25f, 0.53f, 0.96f, 0);


        var size = getSize();

        var height = GetHeight();

        GradientPaint gradient = new GradientPaint(size.width / 2, size.height, startColor, size.width / 2, size.height - height, endColor);
        ((Graphics2D) g).setPaint(gradient);
        g.fillRect(0, size.height - height, size.width, size.height);
    }
}
