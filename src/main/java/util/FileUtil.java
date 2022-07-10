package util;

import vo.Video;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class FileUtil {
    //default path
    private static String OUTPUT_FILE_PATH;
    private static Logger logger = Logger.getLogger(FileUtil.class.getSimpleName());
    public static void setFileOutPath(String path){
        OUTPUT_FILE_PATH = path;
    }
    public static Integer getFileCount() throws Exception{
        File folder = new File(OUTPUT_FILE_PATH);
        if(folder.exists() && folder.isDirectory()){
            return folder.listFiles().length;
        }
        return 0;
    }
    public static void writeFile(Video video) throws Exception{
        try (BufferedInputStream in = new BufferedInputStream(new URL(video.getUrl()).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_PATH + video.getTitle() + ".mp4")) {
            Integer len = 1024 * 8;
            byte dataBuffer[] = new byte[len];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, len)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            logger.info(StringUtil.format("{0} : download failure.", video.getTitle()));
        }
    }
}
