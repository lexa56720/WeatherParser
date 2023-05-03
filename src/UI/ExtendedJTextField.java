package UI;

import javax.swing.*;
import java.awt.*;

public class ExtendedJTextField extends JTextField
{
    public ExtendedJTextField(String text,int fontSize)
    {
        super(text);
        setEditable(false);
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.PLAIN,  fontSize));
    }
}
