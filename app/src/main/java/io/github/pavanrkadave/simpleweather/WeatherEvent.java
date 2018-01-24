package io.github.pavanrkadave.simpleweather;

/**
 * Created by pavan on 1/24/2018.
 */

public class WeatherEvent {

    /* Name of the City */
    public final String mCityName;

    /* Current temprature in the given city */
    public final String mTemprature;

    /* Description of the weather in the city */
    public final String mDescription;

    /* Weather Icon*/
    public final String mIcon;


    /**
     * Constructs a new {@link WeatherEvent}.
     *
     * @param mCityName is the name of the city
     * @param mTemprature is the current temprature of the city.
     * @param mDescription is the description of the weather.
     */
    public WeatherEvent(String mCityName, String mTemprature, String mDescription,String mIcon) {
        this.mCityName = mCityName;
        this.mTemprature = mTemprature;
        this.mDescription = mDescription;
        this.mIcon = mIcon;
    }
}
