package course;

import com.sun.deploy.ui.DialogTemplate;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
/chapters 의 reponse 쪽 긁어서 아래꺼 돌려주면 강의별 url 나옴
3단은 이거
for(var i = 0 ; i < arr.length ; i++){
    var object = arr[i].children;
    for(var j = 0 ; j < object.length ; j++){
        var clips = object[j].children;
        for(var k = 0 ; k < clips.length ; k++){
            console.log(clips[k].id);
        }
    }
};
2단은 이거
for(var i = 0 ; i < arr.length ; i++){
    var object = arr[i].children;
    for(var j = 0 ; j < object.length ; j++){
        console.log(object[j].id);
    }
};
 */
public class BaseCourse {
    protected Map<String, String> requestHeader;
    protected String courseId;
    protected List<String> clipIdList;
    protected List<String> urlList = new ArrayList<String>();
    protected String authKey;
    protected String customKey;

    public void printUrl(){
        urlList.stream().forEach(item -> System.out.println(item));
    }
    public String getSample(){
        return urlList.get(0);
    }

    public String getPageUrl(String jwt){
        return StringUtil.format("https://v.kr.kollus.com/s?jwt={0}&custom_key={1}&s=0", jwt, this.customKey);
    }
    public String setApiUrl(String courseId, String clipId) {
        return StringUtil.format("https://fastcampus.app/.api/classroom/courses/{0}/clips/{1}", courseId, clipId);
    }
    public String getMediaUrl(String key){
        return StringUtil.format("https://fastcamp.video.kr.kollus.com/kr/media-file.mp4?_s={0}", key);
    }
    public List<String> getApiUrl(){
        return this.urlList;
    }
    public Map<String, String> getRequestHeader(){
        return this.requestHeader;
    }

}
