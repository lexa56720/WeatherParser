package Requester;

import DataTypes.CityInfo;
import DataTypes.WeatherInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.util.List;

public class WeatherRequester
{
    public static WeatherInfo[] RequestWeather(CityInfo city, int days)
    {
        var result = new WeatherInfo[days];
        for (int day = 0; day < days; day++)
        {
            var weatherPage = RequestPage(FormUrl(day, city.GetUrl()));
            result[day] = ParsePage(weatherPage);
        }
        return result;
    }

    private static WeatherInfo ParsePage(Document doc)
    {
        var temperature = GetTemperature(doc);
        var wind = GetWind(doc);
        var precipitation=GetPrecipitation(doc);
        return null;
    }

    private static int[] GetTemperature(Document document)
    {
        var element = document.select("body > section.content.wrap" +
                " > div.content-column.column1 > section:nth-child(3) >" +
                " div.widget.widget-weather-parameters.widget-oneday >" +
                " div > div > div:nth-child(4) > div > div");
        var temperature = new int[element.first().childrenSize()];
        for (int i = 0; i < temperature.length; i++)
        {
            temperature[i] = Integer.parseInt(element.first().child(i).child(0).text());
        }
        return temperature;
    }

    private static int[] GetWind(Document document)
    {
        var element = document.select("div.widget-row.widget-row-wind-gust.row-with-caption");
        var wind = new int[element.first().childrenSize() - 1];

        for (int i = 0; i < wind.length; i++)
        {
            var node = element.first().child(i + 1);
            if (node.childrenSize() > 1)
                wind[i] = Integer.parseInt(node.child(1).text());
            else
                wind[i] = 0;
        }
        return wind;
    }

    private static float[] GetPrecipitation(Document document)
    {

        var element = document.select(  ".widget-row.widget-row-precipitation-bars.row-with-caption");
        var precipitation = new float[element.first().childrenSize()-1];


        for (int i = 0; i < precipitation.length; i++)
        {
            var node = element.first().child(i + 1);
            precipitation[i] = Float.parseFloat(node.child(0).text().replace(',','.'));
        }
        return precipitation;
    }
    private static String FormUrl(int day, String url)
    {
        switch (day)
        {
            case 0:
                return url;
            case 1:
                return url + "tomorrow/";
            default:
                return url + day + 1 + "-day/";
        }
    }
    private static Document RequestPage(String path)
    {
        try
        {
            return Jsoup.connect(path).get();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
