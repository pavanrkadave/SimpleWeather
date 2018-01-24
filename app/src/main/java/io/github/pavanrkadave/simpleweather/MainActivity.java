package io.github.pavanrkadave.simpleweather;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshLayout;

    private static final String OPEN_WEATHERMAP_REQUEST_URL = "http://api.wunderground.com/api/35a98bca3cd6d927/conditions/q/zmw:00000.29.43226.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                startActivity(getIntent());
            }
        });

        /**
         * Create instance of {@link AsyncTask} called {@link WeatherAsyncTask}
         * to setup network connection on a background Thread.
         */
        WeatherAsyncTask task = new WeatherAsyncTask();
        task.execute(OPEN_WEATHERMAP_REQUEST_URL);
    }

    private void updateUI(WeatherEvent weatherEvent) {

        /* Setting the City Name to the textview */
        TextView cityName = findViewById(R.id.city_name);
        cityName.setText(weatherEvent.mCityName);

        /* Setting the temprature to the textview */
        TextView weatherTemprature = findViewById(R.id.weather_temprature);
        weatherTemprature.setText(weatherEvent.mTemprature + "° C");

        /* Setting the description to the textview */
        TextView description = findViewById(R.id.weather_description);
        description.setText(weatherEvent.mDescription);

        /* Loadinge the weather Icon to the image view */
        ImageView icon = findViewById(R.id.weather_icon_view);
        String url = weatherEvent.mIcon;
        Picasso.with(getApplicationContext()).load(url).into(icon);

        /* Loading the WeatherUnderground Logo to the ImageView */
        ImageView logo_weather_channel = findViewById(R.id.weather_channel_logo);
        Picasso.with(getApplicationContext()).
                load("http://icons.wxug.com/graphics/wu2/logo_130x80.png").into(logo_weather_channel);

    }

    private class WeatherAsyncTask extends AsyncTask<String, Void, WeatherEvent> {

        @Override
        protected WeatherEvent doInBackground(String... urls) {

            /**
             * Creating the instance of WeatherEvent and fetching the weather data
             * from the Utils class. through its static methods
             */
            WeatherEvent results = Utils.fetchWeatherData(urls[0]);

            /**
             * returns the instance of results to the onPostExecute Method.
             */
            return results;
        }

        @Override
        protected void onPostExecute(WeatherEvent result) {
            /**
             * Updating the UI Once the Asynctask is completed its task.
             */
            updateUI(result);
        }
    }

}
