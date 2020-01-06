package pack.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UtilService {

    @Value("${upload.path}")
    private String rootPicPath;

    public String generatePicPath(MultipartFile picPath){
        if (picPath!=null && !picPath.getOriginalFilename().isEmpty()){
            File uploadDir = new File(rootPicPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String resultPicPath = UUID.randomUUID().toString()+"."+picPath.getOriginalFilename();
            try {
                picPath.transferTo(new File(uploadDir + "/" + resultPicPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultPicPath;
        }
        return null;
    }
}
