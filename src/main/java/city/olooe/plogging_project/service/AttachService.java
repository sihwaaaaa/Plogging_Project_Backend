package city.olooe.plogging_project.service;

import city.olooe.plogging_project.handler.FileUploadHandler;
import city.olooe.plogging_project.model.AttachEntity;
import city.olooe.plogging_project.persistence.AttachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachService {

    private final AttachRepository attachRepository;
    private final FileUploadHandler fileUploadHandler;

    public AttachEntity addAttach(AttachEntity attachEntity,
                                  List<MultipartFile> files) throws Exception {
        List<AttachEntity> list = fileUploadHandler.parseFileInfo(attachEntity.getAttachNo(), files);

        if (list.isEmpty()) {

        }
        else {
           List<AttachEntity> pictureBeans = new ArrayList<>();
           for(AttachEntity attaches : list) {
               pictureBeans.add(attachRepository.save(attaches));
           }
        }
        return attachRepository.save(attachEntity);
    }

    public List<AttachEntity> findAttaches() {
        return attachRepository.findAll();
    }

    public Optional<AttachEntity> findAttach(Long attachNo) {
        return attachRepository.findById(attachNo);
    }

}
