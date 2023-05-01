
import Requester.SearchRequester;
import Requester.WeatherRequester;

public class Main
{
    public static void main(String[] args)
    {
        var cities=SearchRequester.GetResult("New");
        for(var city: cities)
            WeatherRequester.RequestWeather(city,10);
    }

}