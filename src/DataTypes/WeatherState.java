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
