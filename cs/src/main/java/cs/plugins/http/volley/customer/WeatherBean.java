package cs.plugins.http.volley.customer;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by shiwx on 2015/1/23.
 */
public class WeatherBean implements Serializable {
    private Map<String, String> weatherinfo;

    public Map<String, String> getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(Map<String, String> weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    @Override
    public String toString() {
        return "WeatherBean{" + "weatherinfo=" + weatherinfo + '}';
    }
}