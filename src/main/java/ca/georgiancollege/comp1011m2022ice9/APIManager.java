package ca.georgiancollege.comp1011m2022ice9;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIManager
{
    /********************** SINGLETON SECTION ********************************/
    // Step 1. - private static instance member variable
    private static APIManager m_instance = null;
    // Step 2. - make the default constructor private
    private APIManager() {}
    // Step 3. - create a public static entry point / instance method
    public static APIManager Instance()
    {
        // Step 4. - check if the private instance member variable is null
        if(m_instance == null)
        {
            // Step 5. - instantiate a new DBManager instance
            m_instance = new APIManager();
        }
        return m_instance;
    }
    /************************************************************************/

    public Movie getMovieFromOMDBByTitleAndYear(String title, String year)
    {
        // Step 1. Construct URL
        String APIKey = "769bf72f";
        String URL = "https://www.omdbapi.com/?apikey=" + APIKey + "&t=" + title + "&y=" + year;

        // Step 2. Create client object
        HttpClient client = HttpClient.newHttpClient();

        // Step 3. Create request object
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try
        {
            // Step 4. Create response object
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Step 5. Create a Gson object
            Gson gson = new Gson();

            // Step 6. Deserialize the data using the fromJSON method
            return gson.fromJson(response.body(), Movie.class);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        return null;
    }
}
