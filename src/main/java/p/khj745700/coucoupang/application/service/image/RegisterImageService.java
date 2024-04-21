package p.khj745700.coucoupang.application.service.image;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.S3DirConstant;
import p.khj745700.coucoupang.application.dao.ImageDao;
import p.khj745700.coucoupang.application.domain.img.Image;
import p.khj745700.coucoupang.application.dto.request.img.ImageUploadRequest;
import p.khj745700.coucoupang.application.utils.S3Uploader;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterImageService implements IRegisterImageService{
    private final ImageDao imageDao;
    private final S3Uploader s3Uploader;

    @Override
    @Transactional
    public Image saveImage(ImageUploadRequest request, S3DirConstant s3DirConstant) {
        String url = s3Uploader.upload(request.getImage(), request.getFileName(), s3DirConstant);
        Image image = Image.builder()
                .src(url)
                .build();
        return imageDao.save(image);
    }

    @Override
    @Transactional
    public List<Image> saveImages(List<ImageUploadRequest> requests, S3DirConstant s3DirConstant) {
        return requests.stream().map(request -> saveImage(request, s3DirConstant)).toList();
    }
}
