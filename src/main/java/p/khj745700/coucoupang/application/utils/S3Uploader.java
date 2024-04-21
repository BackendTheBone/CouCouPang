package p.khj745700.coucoupang.application.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.config.constant.S3DirConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {
    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final UUIDGenerator uuidGenerator;

    public String upload(String base64, String filename, S3DirConstant s3DirConstant) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(base64);
        File f = new File(filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            return upload(f, s3DirConstant);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String upload(File uploadFile, S3DirConstant s3DirConstant) {
        LocalDateTime now = LocalDateTime.now();
        String fileName = s3DirConstant.getBaseDir() + "/" + now.getYear() + "/" + now.getMonth() + "/" + uuidGenerator.get() + URLEncoder.encode(uploadFile.getName(), StandardCharsets.UTF_8); // UUID + fileName으로 작성
        String uploadImageUrl = putS3(uploadFile, fileName);

        removeNewFile(uploadFile);  // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)

        return uploadImageUrl;      // 업로드된 파일의 S3 URL 주소 반환
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)	// PublicRead 권한으로 업로드 됨
        );
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public void deleteFile(String uri, String dirPath){
        String key = getKey(uri, dirPath);
        deleteS3(key);
    }

    private String getKey(String uri, String dirPath){
        try{
            String uuid = uri.split(dirPath)[1];
            return URLDecoder.decode(dirPath + uuid, StandardCharsets.UTF_8);
        }catch (ArrayIndexOutOfBoundsException e){
            log.error("S3 delete object Failed (uri form issue)", e);
            return "";
        }
    }

    // S3 에서 delete 에 실패한 것은 critical 한 오류가 아니므로 logging 진행 이후 처리
    private void deleteS3(String key){
        try{
            amazonS3Client.deleteObject(bucket, key);
        }catch (Exception e){
            log.error("S3 delete object Failed", e);
        }
    }

    private void removeNewFile(File targetFile) {
        if(targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        }else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

}
