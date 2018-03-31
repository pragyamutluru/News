package com.pragyamutluru.news;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by suresh on 25/1/18.
        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
   /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<News> extractNews(String JSON_RESPONSE) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<News> newsList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject rootObj= new JSONObject(JSON_RESPONSE);
            JSONArray articlesObj= rootObj.optJSONArray("articles");
            if(articlesObj!=null){
                Log.i("QUERY_AMOUNT",Integer.toString(articlesObj.length()));
                for(int i=0; i<articlesObj.length(); i++){
                    JSONObject iObj= articlesObj.getJSONObject(i);
                    News iNews= new News();
                    String title= iObj.optString("title");
                    iNews.setTitle(title);
                    String description= iObj.optString("description");
                    iNews.setDescription(description);
                    String image= iObj.optString("urlToImage");
                    if(image.startsWith("/")){
                        image="https:"+image;
                    }
                    iNews.setImageUrl(image);
                    String date= iObj.optString("publishedAt");
                    iNews.setDate(date);
                    String webUrl= iObj.optString("url");
                    iNews.setWebUrl(webUrl);
                    JSONObject sourceObj=iObj.optJSONObject("source");
                    if(sourceObj!=null){
                    String source=sourceObj.optString("name");
                    iNews.setSource(source);}

                    newsList.add(iNews);


                }
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        // Return the list of earthquakes
        return newsList;
    }



}