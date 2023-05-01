package DataTypes;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

public class WeatherState
{

    private int temperature;
    private int wind;
    private float precipitation;
    private Date DateTime;
    public WeatherState(int temperature, int wind, float precipitation, Date date, int time)
    {
        this.temperature = temperature;
        this.wind = wind;
        this.precipitation = precipitation;

        DateTime = Date.from(date.toInstant().plus(Duration.ofHours(time)));

    }



}
