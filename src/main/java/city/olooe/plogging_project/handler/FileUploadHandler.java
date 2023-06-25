package city.olooe.plogging_project.handler;

import city.olooe.plogging_project.model.AttachEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileUploadHandler {

    public List<AttachEntity> parseFileInfo(Long attachNo, List<MultipartFile> files)
        throws Exception {
        List<AttachEntity> fileList = new ArrayList<>();

        if(files.isEmpty()) {
            return fileList;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());

        String absolutePath = new File("").getAbsolutePath() + "\\";

        String path = "upload/" + currentDate;

        File file = new File(path);

        if(!file.exists()) {
            file.mkdirs();
        }

        for(MultipartFile multipartFile : files) {
            if(!files.isEmpty()) {
                String contentType =multipartFile.getContentType();
                String originalFileExtenstion;

                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if(contentType.contains("image/jpeg")) {
                        originalFileExtenstion = ".jpg";
                    } else if(contentType.contains("image/png")) {
                        originalFileExtenstion = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtenstion = ".gif";
                    } else {
                        break;
                    }
                }
                String new_file_name = System.nanoTime() + originalFileExtenstion;

                AttachEntity attachEntity = AttachEntity.builder()
                        .attachNo(attachNo)
                        .filename(multipartFile.getOriginalFilename())
                        .path(path + "/" + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();
                fileList.add(attachEntity);

                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }
        return fileList;
    }


}
