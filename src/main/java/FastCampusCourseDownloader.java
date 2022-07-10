import com.sun.deploy.ui.DialogTemplate;
import course.BaseCourse;
import course.SpringBootCourse;
import course.TeamjangCourse;
import request.HttpRequest;
import util.FileUtil;
import util.StringUtil;
import vo.Video;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class FastCampusCourseDownloader {
    //default properties value
    private String destPath = "D:\\4. Media\\SpringBoot\\";
    private String courseId = "206067";
    private String authKey = "bearer a96e51c4c989f98329df5feca02f62be52fb";
    private Integer threadCnt = 10;
    private Integer offset = 0;

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    public FastCampusCourseDownloader destPath(String destPath){
        this.destPath = destPath;
        return this;
    }
    public FastCampusCourseDownloader authKey(String authKey){
        this.authKey = authKey;
        return this;
    }
    public FastCampusCourseDownloader courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }
    public FastCampusCourseDownloader offset(Integer offset){
        this.offset = offset;
        return this;
    }
    public FastCampusCourseDownloader threadCnt(Integer threadCnt){
        this.threadCnt = threadCnt;
        return this;
    }
    private BaseCourse getInst() throws Exception{
        switch(this.courseId){
            case "206067":
                return new SpringBootCourse(this.authKey);
            case "203657":
                return new TeamjangCourse(this.authKey);
            default:
                throw new RuntimeException("Invalid Course Id");
        }
    }
    public void run() throws Exception{
        BaseCourse course = getInst();
        FileUtil.setFileOutPath(this.destPath);
        Integer fileCnt = FileUtil.getFileCount();

        ExecutorService executor = Executors.newFixedThreadPool(this.threadCnt);
        for(String apiUrl : course.getApiUrl()){
            if(course.getApiUrl().indexOf(apiUrl) < fileCnt + this.offset){
                continue;
            }
            executor.execute(() -> {
                try{
                    Map<String, String> reponseMap = new HttpRequest().doGet(apiUrl, course.getRequestHeader())
                            .responseToString()
                            .jsonToMap()
                            .getResponseMap();
                    String jwt = reponseMap.get("jwt");

                    String reponseString = new HttpRequest()
                            .doGet(course.getPageUrl(jwt))
                            .responseToString()
                            .getResponseString();

                    Video video = new Video(
                            course.getMediaUrl(StringUtil.getMediaKey(reponseString))
                            , StringUtil.getMediaTitle(reponseString)
                    );

                    logger.info(StringUtil.format("{0} : download start.", video.getTitle()));
                    FileUtil.writeFile(video);
                    logger.info(StringUtil.format("{0} : download success.", video.getTitle()));
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
