package p.khj745700.coucoupang.application.service.image;

import p.khj745700.coucoupang.application.config.constant.S3DirConstant;
import p.khj745700.coucoupang.application.domain.img.Image;
import p.khj745700.coucoupang.application.dto.request.img.ImageUploadRequest;

import java.util.List;

public interface IRegisterImageService {
    Image saveImage(ImageUploadRequest request, S3DirConstant s3DirConstant);
    List<Image> saveImages(List<ImageUploadRequest> requests, S3DirConstant s3DirConstant);
}
