package DataTypes;

import java.util.Arrays;
import java.util.Date;

public class WeatherInfo
{
    public WeatherInfo(CityInfo city, Date date,int[] temperature, int[] wind, float[] precipitation, int[] time)
    {

    }

    public int GetWeatherState(int time)
    {
        var index = Arrays.binarySearch(this.time, time);
        return temperature[index];
    }
    int[] GetTimes()
    {
        return time;
    }

}
