package DataTypes;

import java.util.Date;

public class WeatherInfo
{

    private CityInfo City;
    private java.util.Date Date;
    private WeatherState[] States;
    public WeatherInfo(CityInfo city, Date date, int[] temperature, int[] wind, float[] precipitation, WeatherState.SkyState[] skyStates, int[] time)
    {
        City = city;
        Date = date;
        States = new WeatherState[time.length];
        for (int i = 0; i < time.length; i++)
            States[i] = new WeatherState(temperature[i], wind[i], precipitation[i], date, skyStates[i], time[i]);
    }

    public CityInfo GetCity()
    {
        return City;
    }
    public WeatherState[] GetWeatherStates()
    {
        return States;
    }

    public WeatherState.SkyState GetSkyState()
    {
        int sunCount = 0;
        int cloudCount = 0;

        for (var state : States)
        {
            if (state.getSkyState() == WeatherState.SkyState.Raining)
                return WeatherState.SkyState.Raining;

            if (state.getSkyState() == WeatherState.SkyState.Sunny)
                sunCount++;
            else if (state.getSkyState() == WeatherState.SkyState.Cloudy)
                cloudCount++;
        }

        if (sunCount >= cloudCount)
            return WeatherState.SkyState.Sunny;

        return WeatherState.SkyState.Cloudy;
    }
    public int GetMaxTemp()
    {
        int max = Integer.MIN_VALUE;

        for (var state : States)
            if (state.getTemperature() > max)
                max = state.getTemperature();
        return max;
    }

    public int GetMinTemp()
    {
        int min = Integer.MAX_VALUE;

        for (var state : States)
            if (state.getTemperature() < min)
                min = state.getTemperature();
        return min;
    }

    public float GetMaxWater()
    {
        float max = 0;
        for (var state : States)
            if (state.getPrecipitation() > max)
                max = state.getPrecipitation();
        return max;
    }

    public java.util.Date GetDate()
    {
        return Date;
    }
}
