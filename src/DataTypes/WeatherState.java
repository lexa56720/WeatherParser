package DataTypes;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

public class WeatherState
{
    public enum SkyState
    {
        Cloudy,
        Sunny,
        Raining
    }
    public float getPrecipitation()
    {
        return precipitation;
    }
    public int getTemperature()
    {
        return temperature;
    }
    public int getWind()
    {
        return wind;
    }
    public Date getDateTime()
    {
        return DateTime;
    }
    public WeatherState.SkyState getSkyState()
    {
        return SkyState;
    }


    private int temperature;
    private int wind;
    private float precipitation;
    private Date DateTime;
    private SkyState SkyState;


    public WeatherState(int temperature, int wind, float precipitation, Date date,SkyState state, int time)
    {
        this.temperature = temperature;
        this.wind = wind;
        this.precipitation = precipitation;
        SkyState=state;

        DateTime = Date.from(date.toInstant().plus(Duration.ofHours(time)));

    }



}
