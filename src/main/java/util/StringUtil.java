package util;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringUtil{
    public static String format(String sourceString, String...strings) {
        for (int i = 0; i < strings.length; i++) {
            sourceString = sourceString.replaceAll("\\{" + i + "\\}", strings[i]);
        }
        return sourceString;
    }
    public static String responseToString(CloseableHttpResponse httpResponse) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer stringBuffer = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            stringBuffer.append(inputLine);
        }
        reader.close();
        return stringBuffer.toString();
    }
    public static String getMediaKey(String htmlStr) throws Exception{
        return htmlStr.substring(htmlStr.indexOf("media-file.mp4?_s=") +18, htmlStr.indexOf("poster_url") - 3);
    }
    public static String getMediaTitle(String htmlStr) throws Exception{
        return htmlStr.substring(htmlStr.indexOf("<title>") + 7, htmlStr.indexOf("</title>"));
    }
}
