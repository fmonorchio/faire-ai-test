package faireai.tinyweatherbulletin.domain.openweather;

import lombok.Data;

import java.io.Serializable;

@Data
public class Weather implements Serializable {

    public int id;

    public String main;

    public String description;

    public String icon;

}
