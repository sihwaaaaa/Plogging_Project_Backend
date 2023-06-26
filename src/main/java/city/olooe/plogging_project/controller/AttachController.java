//package city.olooe.plogging_project.controller;
//
//import city.olooe.plogging_project.dto.AttachDTO;
//import city.olooe.plogging_project.dto.ResponseDTO;
//import city.olooe.plogging_project.model.AttachEntity;
//import city.olooe.plogging_project.service.AttachService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@RestController
//@RequestMapping("file")
//@RequiredArgsConstructor
//@Slf4j
//public class AttachController {
//
//    private final AttachService attachService;
//    public static final String UPLOAD_PATH = "upload";
//    @Autowired
//    private MultipartProperties multipartProperties;
//
//    @GetMapping
//    public MultipartProperties get() {
//        return multipartProperties;
//    }
//
//
//    @PostMapping(value = "upload", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<AttachDTO> uploadAjax(MultipartFile[] files) {
//        log.warn("file {}", (Object) files);
//
//        File uploadPath = new File(UPLOAD_PATH, getFolder());
//        if (!uploadPath.exists()) {
//            uploadPath.mkdirs();
//        }
//
//        List<AttachDTO> list = new ArrayList<>();
//        for (MultipartFile m : files) {
//            log.info(m.getOriginalFilename());
//
//            // 실제 스트림 전송
//            String uuidStr = UUID.randomUUID().toString();
//            String tName = uuidStr + "_" + m.getOriginalFilename();
//            File f = new File(uploadPath, tName);
//            Boolean image = false;
//            try {
//                image = isImage(f);
//                m.transferTo(f);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            list.add(AttachDTO.builder().uuid(uuidStr).path(getFolder()).filename(m.getOriginalFilename()).build());
//        }
//        return list;
//    }
//
//    private String getFolder() {
//        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//    }
//
//    // 확장자의 다양성 처리 file >> mime
//    private boolean isImage(File file) throws IOException {
//        List<String> excludes = Arrays.asList("ico", "webp");
//        int idx = file.toString().lastIndexOf(".");
//        if(idx == -1){
//            return false;
//        }
//        String ext = file.toString().substring(idx+1);
//        if(excludes.contains(ext)) {
//            return false;
//        }
//
//        String mime = Files.probeContentType(file.toPath());
//        return mime != null && mime.startsWith("image");
//    }
//
//    @PostMapping("/upload2")
//    public boolean uploadFile(List<MultipartFile> files) throws IllegalStateException, IOException {
//        String PATH = "upload/" + new Date().getTime(); // 업로드 할 위치 // 현재 날짜 값 폴더
//
//        try {
//            for (MultipartFile file : files) {
//                if(!files.isEmpty()){
//                    String originName = file.getOriginalFilename(); // 파일.type
//                    String[] tempStr = originName != null ? originName.split(".") : new String[0];
//                    originName = tempStr[0];
//                    String type = tempStr[1];
//
//                    File fileSave = new File(PATH, originName + "." + type); // 경로/파일.type
//
//                    if (!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
//                        fileSave.mkdirs();
//                    }
//
//                    file.transferTo(fileSave);
//                    // transferTo(File file) > multipartFile을 주어진 file의 경로로 이동 (copy)
//                } else {
//                    break;
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println(e);
//            return false;
//        }
//
//        return true;
//    }
//
//
//    @PostMapping("/upload3")
//    public ResponseEntity<?> createAttach(
//            @Validated @RequestParam("files") List<MultipartFile> files) throws Exception {
//        log.warn("파일 데이터 확인 {}", files);
//        attachService.addAttach(AttachEntity.builder().build(), files);
//
//        return ResponseEntity.ok().body(ResponseDTO.builder().data(files).build());
//    }
//
//    @GetMapping("get")
//    public String getAttach(@RequestParam Long attachNo) {
//        AttachEntity attachEntity = attachService.findAttach(attachNo).orElseThrow(RuntimeException::new);
//        String imgPath = attachEntity.getPath();
//        log.warn("이미지 경로 {} ", imgPath);
//        return "<img src=" + imgPath + ">";
//    }
//
//}