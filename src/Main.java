
import Requester.SearchRequester;
import Requester.WeatherRequester;
import UI.MainFrame;

import javax.swing.*;

public class Main
{
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        var mainFrame=new MainFrame();
    }

}