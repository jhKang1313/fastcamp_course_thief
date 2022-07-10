package vo;

import util.StringUtil;

public class Video {
    private String url;
    private String title;
    public Video(String url, String title){
        this.url = url;
        this.title = title;
    }
    public void printInfo(){
        System.out.println(StringUtil.format("{0} : {1}", title, url));
    }

    public String getTitle() {
        return title;
    }
    public String getUrl(){
        return url;
    }
}
