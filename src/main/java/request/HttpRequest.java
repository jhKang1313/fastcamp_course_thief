package request;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import util.ConvertUtil;
import util.StringUtil;

import java.util.Map;

public class HttpRequest {
    private CloseableHttpClient httpClient;
    private CloseableHttpResponse httpResponse;
    private String reponseString;
    private Map<String, String> reponseMap;
    public HttpRequest() throws Exception{
        httpClient = HttpClients.createDefault();
    }
    public HttpRequest doGet(String url) throws Exception{
        doGet(url, null);
        return this;
    }
    public HttpRequest doGet(String url, Map<String, String> header) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        if(header != null){
            for(String key :header.keySet()){
                httpGet.addHeader(key, header.get(key));
            }
        }
        httpResponse = httpClient.execute(httpGet);
        return this;
    }
    public HttpRequest jsonToMap() throws Exception{
        reponseMap =  ConvertUtil.jsonToMap(this.reponseString);
        return this;
    }
    public HttpRequest responseToString() throws Exception{
        this.reponseString = StringUtil.responseToString(this.httpResponse);
        return this;
    }
    public HttpRequest httpClientClose() throws Exception{
        this.httpClient.close();
        return this;
    }

    public Map<String, String> getResponseMap(){
        return this.reponseMap;
    }
    public String getResponseString(){
        return this.reponseString;
    }
}
