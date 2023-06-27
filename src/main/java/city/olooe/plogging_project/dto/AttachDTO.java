package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.controller.FileController;
import city.olooe.plogging_project.dto.community.BoardDTO;
import city.olooe.plogging_project.model.AttachEntity;
import city.olooe.plogging_project.model.BadgeEntity;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.community.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttachDTO {

    private Long attachNo;
    private String uuid;
    private String path;
    private String filename;
    private Long bno;
    private Long chNo;
    private Long badgeNo;

    public AttachDTO(AttachEntity attachEntity) {
        this.uuid = attachEntity.getUuid();
        this.path = attachEntity.getPath();
        this.filename = attachEntity.getFilename();
    }

    public AttachDTO(MultipartFile multipartFile) {
        this.filename = multipartFile.getOriginalFilename();
        this.uuid = UUID.randomUUID().toString();
        this.path = getTodayStr();
    }

    public File getFile() {
        return new File(FileController.UPLOAD_PATH + File.separator + path,
                uuid + "." + getExt());
    }

    public AttachEntity toEntity() {
        return AttachEntity.builder()
                .attachNo(attachNo)
                .uuid(uuid)
                .filename(filename)
                .path(path)
                .bno(bno != null ? BoardEntity.builder().bno(bno).build() : null)
                .chNo(chNo != null ? ChallengeEntity.builder().chNo(chNo).build() : null)
                .badgeNo(badgeNo != null ? BadgeEntity.builder().badgeNo(badgeNo).build() : null)
                .build();
    }

    public String getTodayStr() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    public String getExt() {
        return filename.lastIndexOf(".") == -1 ? "" : filename.substring(filename.lastIndexOf(".") + 1);
    }
}