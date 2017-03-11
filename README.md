# DockerConnector-Library

This is a basic docker connector (Alpha 1).

You can:

* Get version
* List images

# Reference

## Create a connection with Docker

To create a connection with Docker only have to create a object of Docker. 
It only has a one parameter, the url of Docker API. If it is not specified
gets by default http://localhost:2375/

```Java
Docker dockerCon=new Docker();
```

You can show more information of what is doing Docker using the debug mode

```Java
dockerCon.setDebugMode(true);
```

## Get version

The function getVersion returns an object (Version) with all information of the version

```Java
Version version=dockerCon.getVersion();
```

Version object:

```Java
public class Version {
    public String Version;
    public String ApiVersion;
    public String MinAPIVersion;
    public String GitCommit;
    public String GoVersion;
    public String Os;
    public String Arch;
    public String KernelVersion;
    public Boolean Experimental;
    public String Buildtime;
}
```

## Get image list

The function getImages returns the list of images of Docker

```Java
Version version=dockerCon.getImages();
```

Image object:

```Java
public class Image {
    public int Containers;
    public int Created;
    public String Id;
    public String ParentId;
    public String[] RepoDigests;
    public String[] RepoTags;
    public int SharedSize;
    public int Size;
    public int VirtualSize;
}
```

# Complete test

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

