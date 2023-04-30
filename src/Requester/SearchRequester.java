package Requester;

import DataTypes.CityInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchRequester
{

    public static CityInfo[] GetResult(String request)
    {
        try
        {
            var response = SendGetRequest("https://www.gismeteo.ru/mq/search/" + request + "/20/");
            var citiesData = ParseResponse(response);
            var citiesInfo=new CityInfo[citiesData.length];
            for(var i=0;i<citiesInfo.length;i++)
                citiesInfo[i]=new CityInfo(citiesData[i]);
            return citiesInfo;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    private static String[] ParseResponse(String response)
    {

        var cities = response.split("\\{" + "\"source\"");
        var result = new String[cities.length - 1];
        for (int i = 0; i < result.length; i++)
            result[i] = "{" + "\"source\"" + cities[i+1].substring(0, cities[i+1].length() - 1);
        result[result.length-1]=result[result.length-1].substring(0, result[result.length-1].length()-1);
        return result;
    }


    private static String SendGetRequest(String path) throws IOException
    {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        if (responseCode != 200)
        {
            throw new RuntimeException("Failed to send GET request: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }

        in.close();
        connection.disconnect();
        return response.toString();
    }

}
