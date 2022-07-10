package course;

import java.util.Arrays;
import java.util.HashMap;

public class TeamjangCourse extends BaseCourse{
    public TeamjangCourse(String authKey){
        this.customKey = "0b50155632326ca34e42550f10d6fca2c42c7b62a47eee4841aabd0cd1a913ea";
        this.courseId = "203657";

        this.clipIdList = Arrays.asList(
                "268218"
                ,"268217"
                ,"268304"
                ,"268305"
                ,"268216"
                ,"282689"
                ,"490466"
                ,"282691"
                ,"282688"
                ,"282671"
                ,"490467"
                ,"282672"
                ,"282673");

        clipIdList.stream().forEach(item -> urlList.add(this.setApiUrl(courseId, item)));

        requestHeader = new HashMap<String, String>();
        requestHeader.put("Authorization", authKey);
        requestHeader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        requestHeader.put("Accept", "application/json, text/plain, */*");
        requestHeader.put("Content-Type", "application/json; charset=utf-8");

    }
}
