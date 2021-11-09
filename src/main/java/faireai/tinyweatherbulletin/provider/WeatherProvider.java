package faireai.tinyweatherbulletin.provider;

import faireai.tinyweatherbulletin.domain.openweather.forecasts.OpenWeatherForecastsResponse;
import faireai.tinyweatherbulletin.domain.openweather.geo.OpenWeatherGeoResponse;

public interface WeatherProvider {

    OpenWeatherGeoResponse getGeoByCityName(String cityName);

    OpenWeatherForecastsResponse getForecastsByGeo(double latitude, double longitude);

}
