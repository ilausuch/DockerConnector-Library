package DockerConnector;

/*
 * License: MIT @ Ivan Lausuch <ivan@lausuch.com>
 */


/**
 * Equivalent to Image JSON result
 * @author ilausuch
 */
public class Image {
    public int Containers;
    public int Created;
    public String Id;
    //public labels
    public String ParentId;
    public String[] RepoDigests;
    public String[] RepoTags;
    public int SharedSize;
    public int Size;
    public int VirtualSize;
}
