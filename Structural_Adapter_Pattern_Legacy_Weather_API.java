// Legacy system
class OldWeatherAPI {
    public double getTemperatureF() { return 100.0; } // Fahrenheit
}

// Target interface
interface WeatherService {
    double getTemperatureC();
}

// Adapter
class WeatherAdapter implements WeatherService {
    private OldWeatherAPI oldApi;
    public WeatherAdapter(OldWeatherAPI api) { this.oldApi = api; }

    @Override
    public double getTemperatureC() {
        double f = oldApi.getTemperatureF();
        return (f - 32) * 5/9;
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        OldWeatherAPI oldApi = new OldWeatherAPI();
        WeatherService service = new WeatherAdapter(oldApi);

        System.out.println("Temperature in Celsius: " + service.getTemperatureC());
    }
}
