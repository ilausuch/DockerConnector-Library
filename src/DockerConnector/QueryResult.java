package DockerConnector;

/*
 * License: MIT @ Ivan Lausuch <ivan@lausuch.com>
 */


import com.google.gson.Gson;
import java.io.StringReader;

/**
 * General query docker result
 * @author ilausuch
 */
public class QueryResult {
    private final String raw;
    private final int status;
    
    /**
     * Constructor
     * @param raw String raw result
     * @param status Status of operation
     */
    public QueryResult(String raw, int status){
        this.raw=raw;
        this.status=status;
    }
    
    /**
     * Returns the status
     * @return int with query status
     */
    public int getStatus(){
        return this.status;
    }
    
    /**
     * Check if status is success (200)
     * @return true if is succes
     */
    public Boolean isSuccess(){
        return this.status==200;
    }
    
    /**
     * Get raw result
     * @return 
     */
    public String getRawResult(){
        return this.raw;
    }
    
    /**
     * Create a string renderer to be used by json parser
     * @return StringReader
     */
    public StringReader getReader(){
        return new StringReader(this.raw);
    }
}
