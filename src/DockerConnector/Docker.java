package DockerConnector;

/*
 * License: MIT @ Ivan Lausuch <ivan@lausuch.com>
 */



import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Docker connector
 * @author ilausuch
 */
public class Docker {
    private String url;
    private Boolean debugMode;
    
    /**
     * Default constructor that uses http://localhost:2375/ connection
     */
    public Docker(){
        this("http://localhost:2375/");
    }
    
    /**
     * Constructor with url definition
     * @param url URL of Docker api. Sample: http://localhost:2375/
     */
    public Docker(String url){
        this.url=url;
    }
    
    /**
     * Active or desactive debugMode
     * @param debugMode true to activate debug mode
     */
    public void setDebugMode(Boolean debugMode){
        this.debugMode=debugMode;
    }
    
    /**
     * Execute an HTTP GET query and return a QueryResult
     * @param section Second part of URL
     * @return QueryResult with raw string and responseCode
     * @throws Exception If something fails
     */
    private QueryResult get(String section) throws Exception{
        //Prepare query
        String completeUrl=this.url+section;
        URL obj = new URL(completeUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        //Send request
        int responseCode = con.getResponseCode();
        if (this.debugMode){
            System.out.println("Request URL : " + completeUrl);
            System.out.println("Response Code : " + responseCode);
        }
        
        ///Read result and store in a string
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        //return query result
        return new QueryResult(response.toString(),responseCode);
    }
    
    /**
     * Get current version of API
     * @return Version object
     * @throws Exception if something wrong happens
     */
    public Version getVersion() throws Exception{
        //Perform query
        QueryResult result = this.get("version");
        
        //If success map result into version object
        if (result.isSuccess()){
            Gson gson = new Gson();
            Version resultObject = gson.fromJson(result.getReader(), Version.class);
            return resultObject;
        }
        else
            throw new Exception("Cannot read result");
    }
    
    /**
     * Get current images
     * @return Image array with all images
     * @throws Exception  if something wrong happens
     */
    public Image[] getImages() throws Exception{
        //Perform query
        QueryResult result = this.get("images/json");
        
        //If success map result into Image object array
        if (result.isSuccess()){
            Gson gson = new Gson();
            Image resultObject[] = gson.fromJson(result.getReader(), Image[].class);
            return resultObject;
        }
        else
            throw new Exception("Cannot read result");
    }
}
