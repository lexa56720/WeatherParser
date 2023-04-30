import DataTypes.CityInfo;
import Requester.SearchRequester;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.*;
import java.net.http.HttpRequest;

public class Main
{
    public static void main(String[] args)
    {
        var cities=SearchRequester.GetResult("New");
        for(var city: cities)
            System.out.println(city.GetFullLocation());
    }

}