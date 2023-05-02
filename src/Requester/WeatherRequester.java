package Requester;

import java.util.Calendar;
import java.util.Date;
import DataTypes.CityInfo;
import DataTypes.WeatherInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.stream.IntStream;

import static Parser.Parser.*;
import static Parser.Parser.GetPrecipitation;

public class WeatherRequester
{
    public static WeatherInfo[] RequestWeather(CityInfo city, int days)
    {
        var result = new WeatherInfo[days];

        IntStream.range(0, days).parallel().forEach(day ->
        {
            var weatherPage = RequestPage(FormUrl(day, city.GetUrl()));
            result[day] = ParsePage(weatherPage,city,day);
        });
        return result;
    }

    private static WeatherInfo ParsePage(Document doc,CityInfo city, int day)
    {
        var temperature = GetTemperature(doc);
        var wind = GetWind(doc);
        var precipitation = GetPrecipitation(doc);
        var skyState=GetSkyStates(doc);

        var time = new int[]{1, 4, 7, 10, 13, 16, 19, 22};
        var date=Date.from(GetDate().toInstant().plus(Duration.ofDays(day)));

        return new WeatherInfo(city,date,temperature,wind,precipitation,skyState,time);
    }

    public static Date GetDate()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
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
                return url + (day + 1) + "-day/";
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
