package DataTypes;

import java.util.Arrays;
import java.util.Date;

public class WeatherInfo
{

    private CityInfo City;
    private WeatherState[] States;
    public WeatherInfo(CityInfo city, Date date, int[] temperature, int[] wind, float[] precipitation, WeatherState.SkyState[] skyStates, int[] time)
    {
        City = city;
        States=new WeatherState[time.length];
        for(int i=0;i< time.length;i++)
            States[i]=new WeatherState(temperature[i],wind[i],precipitation[i],date,skyStates[i],time[i]);
    }

    public CityInfo GetCity()
    {
        return City;
    }
    public WeatherState[] GetWeatherStates()
    {
        return States;
    }

}
