package Parser;

import DataTypes.WeatherState;
import org.jsoup.nodes.Document;

public class Parser
{
    public static int[] GetTemperature(Document document)
    {
        var element = document.select("body > section.content.wrap" +
                " > div.content-column.column1 > section:nth-child(3) >" +
                " div.widget.widget-weather-parameters.widget-oneday >" +
                " div > div > div:nth-child(4) > div > div");
        var temperature = new int[element.first().childrenSize()];
        for (int i = 0; i < temperature.length; i++)
        {
            var node=element.first().child(i).child(0);
            temperature[i] = Integer.parseInt(node.text().replace('−','-'));

        }
        return temperature;
    }

    public static int[] GetWind(Document document)
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

    public  static float[] GetPrecipitation(Document document)
    {

        var element = document.select(".widget-row.widget-row-precipitation-bars.row-with-caption");
        var precipitation = new float[element.first().childrenSize() - 1];


        for (int i = 0; i < precipitation.length; i++)
        {
            var node = element.first().child(i + 1);
            precipitation[i] = Float.parseFloat(node.child(0).text().replace(',', '.'));
        }
        return precipitation;
    }

    public static WeatherState.SkyState[] GetSkyStates(Document document)
    {

        var element = document.select(".widget-row.widget-row-icon");
        var skyStates = new WeatherState.SkyState[element.first().childrenSize()];


        for (int i = 0; i < skyStates.length; i++)
        {
            var node = element.first().child(i);
            skyStates[i] =  ParseSkyState(node.child(0).attributes().get("data-text"));
        }
        return skyStates;
    }

    private static WeatherState.SkyState ParseSkyState(String s)
    {
        s=s.toLowerCase();
        if(s.contains("ясно"))
            return WeatherState.SkyState.Sunny;
        if(!s.contains("без осадков"))
            return WeatherState.SkyState.Raining;
        return WeatherState.SkyState.Cloudy;
    }

}
