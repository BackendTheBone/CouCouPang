package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.config.constant.S3DirConstant;
import p.khj745700.coucoupang.application.domain.img.Image;
import p.khj745700.coucoupang.application.domain.img.ImageRepository;
import p.khj745700.coucoupang.application.dto.request.img.ImageUploadRequest;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.DuplicateMemberException;
import p.khj745700.coucoupang.application.exception.NotFoundMemberException;
import p.khj745700.coucoupang.application.utils.S3Uploader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImageDao {
    private final S3Uploader s3Uploader;
    private final ImageRepository imageRepository;


    public Image save(Image image) {

        return imageRepository.save(image);
    }

    public List<Image> saveAll(List<Image> images) {
        return imageRepository.saveAll(images);
    }

    @Getter
    private static class ExceptionHandler {
        private static final Function<String, Supplier<CustomException>> NOT_FOUND;
        private static final Function<String, Supplier<CustomException>> DUPLICATE;
        static {
            NOT_FOUND = (String info) -> {
                log.trace("사용자를 찾을 수 없습니다. pk:{}", info);
                return NotFoundMemberException::new;
            };
            DUPLICATE = (String info) -> {
                log.trace("사용자가 이미 존재합니다. pk:{}", info);
                return DuplicateMemberException::new;
            };
        }
    }
}
