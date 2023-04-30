package DataTypes;

import DataTypes.City.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CityInfo
{
    private String Name;
    private String Country;
    private String District;
    private int Id;
    private String Url;
    private Root Root;


    public CityInfo(String data)
    {
        ObjectMapper om = new ObjectMapper();
        try
        {
            Root = om.readValue(data, Root.class);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
        Url = "https://www.gismeteo.ru/" + Root.url;
        Id = Root.id;
        Name = Root.translations.ru.city.name;
        Country = Root.translations.ru.country.name;
        if (Root.translations.ru.district != null)
            District = Root.translations.ru.district.name;
        else
            District = null;
    }
    public String GetName()
    {
        return Name;
    }
    public String GetCountry()
    {
        return Country;
    }
    public String GetDistrict()
    {
        return District;
    }
    public int GetId()
    {
        return Id;
    }
    public String GetUrl()
    {
        return Url;
    }

    public String GetFullLocation()
    {
        if (District != null)
            return Country + ", " + District + ", " + Name;
        else
            return Country + ", " + Name;
    }

}
