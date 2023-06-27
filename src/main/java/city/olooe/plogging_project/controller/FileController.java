package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.AttachDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RestController
@RequestMapping("attach")
@RequiredArgsConstructor
public class FileController {

    public static final String UPLOAD_PATH = "upload";
    private final MultipartProperties multipartProperties;


    @PostMapping("/test")
    public AttachDTO test(MultipartFile files) {
        return new AttachDTO(files);
    }

    @GetMapping
    public MultipartProperties get() {
        return multipartProperties;
    }

    @PostMapping("/upload")
    public AttachDTO upload(MultipartFile files) throws IOException {
            AttachDTO dto = new AttachDTO(files);
            saveFile(files, dto);
        return dto;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(AttachDTO attach) {

        File file = attach.getFile();

        ResponseEntity<byte[]> result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return result;
    }




    private void saveFile(MultipartFile mf, AttachDTO dto) throws IllegalStateException, IOException {
        File path = new File(UPLOAD_PATH, dto.getPath());
        File file = dto.getFile();
        if (!path.exists()) {
            path.mkdirs();
        }
        mf.transferTo(new File(file.getAbsolutePath()));
    }

}
