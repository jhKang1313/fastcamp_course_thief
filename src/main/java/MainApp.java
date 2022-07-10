import course.BaseCourse;
import course.TeamjangCourse;
import vo.Video;
import request.HttpRequest;
import util.FileUtil;
import util.StringUtil;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MainApp {
    public static void main(String[] args) throws Exception{
        new FastCampusCourseDownloader().courseId("")
                .destPath("D:\\4. Media\\SpringBoot\\")     //clip file destination path
                .authKey("bearer a96e51c4c989f98329df5feca02f62be52fb")     //request header authentication key
                .courseId("206067")     //course Id
                .threadCnt(10)      // thread count
                .offset(0)          // clip offset
                .run();             // program run
    }
}
