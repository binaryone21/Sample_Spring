package seunghee.tools;

import javax.servlet.http.HttpServletRequest;

public class RequestTool {

    private static String RESOURCES_PATH = "/src/main/webapp/resources";

    /**
     * 프로젝트내 저장경로 지정
     * @param  req  HttpServletRequest
     * @return      savePath
     */
    public static String getResourcePath(HttpServletRequest req) {
        String savePath = req.getSession().getServletContext().getRealPath("/");
        for(int i=0; i<3; i++) {
            savePath = savePath.substring(0, savePath.lastIndexOf("/"));
        }
        return savePath + RESOURCES_PATH;
    }
}
