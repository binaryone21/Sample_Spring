package seunghee.tools;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import seunghee.common.DateTool;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageTool {

    /**
     * 이미지 업로드후, 업로드 경로
     * @param  imgPath  "/image/img"
     * @param  req      MultipartHttpServletRequest
     * @return          imgPaths
     */
    public static List<String> uploadImage(String imgPath, MultipartHttpServletRequest req) {
        List<String> imgPaths = new ArrayList<>();
        String resourcePath = RequestTool.getResourcePath(req);

        Iterator<String> fileNames = req.getFileNames();
        while(fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile image = req.getFile(fileName);
            if(image == null) continue;

            // 사용자가 저장한 이름
            String originFileName = image.getOriginalFilename();
            // 이미지의 타입(jpg, jpeg, png, ... )
            String imgType = originFileName.substring(originFileName.lastIndexOf(".") + 1);
            // DB 에 저장될 저장 경로
            String dbPath = imgPath + "/" + DateTool.getCurrent("yyyyMMddHHmmss_") + fileName + "." + imgType;
            // 파일이 실제 저장되는 경로
            String savePath = resourcePath + dbPath;

            // 파일 저장
            OutputStream os = null;
            try {
                File file = new File(savePath);

                // 저장경로 체크
                if(file.exists()) {
                    if(file.isDirectory()) throw new IOException("File '" + file + "' exists but is a directory");
                    if(!file.canWrite())   throw new IOException("File '" + file + "' cannot be written to");
                } else {
                    File parent = file.getParentFile();
                    if(parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                        throw new IOException("Directory '" + parent + "' could not be created");
                    }
                }
                os = new FileOutputStream(file, true);
                os.write(image.getBytes());
                os.close();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(os != null) { os.close(); }
                } catch (IOException e) {

                }
            }

            imgPaths.add(dbPath);
        }
        return imgPaths;
    }
}
