# DockerConnector-Library

This is a basic docker connector (Alpha 1)

Test

```Java
package docker.test;

import DockerConnector.Version;
import DockerConnector.Docker;
import DockerConnector.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Docker connection test
 * @author ilausuch
 */
public class DockerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting test");
        
        //Prepare docker
        Docker dockerCon=new Docker();
        dockerCon.setDebugMode(true);
        
        try{
            //Get version and print
            Version version=dockerCon.getVersion();
            System.out.println("Version: "+version.Version);
            
            //Get Image list and print
            Image[] images=dockerCon.getImages();
            
            System.out.println("List of images");
            for (int i=0; i<images.length; i++){
                System.out.println("- Image: "+images[i].Id);
            }
            
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
        
    }
    
}


```

