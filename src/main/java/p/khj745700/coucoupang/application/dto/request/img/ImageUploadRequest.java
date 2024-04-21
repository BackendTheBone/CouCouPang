package p.khj745700.coucoupang.application.dto.request.img;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImageUploadRequest {
    private String image;
    private String name;
    private String extension;
    private Integer order;


    public String getFileName() {
        return name + "." + extension;
    }
}
