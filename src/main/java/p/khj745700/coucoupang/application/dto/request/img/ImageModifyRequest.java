package p.khj745700.coucoupang.application.dto.request.img;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImageModifyRequest {
    private String image;
    private String name;
    private String extension;
    private Integer order;
    private Boolean isDelete;

    public String getFileName() {
        return name + "." + extension;
    }

    public ImageUploadRequest toImageUploadRequest() {
        return ImageUploadRequest.builder()
                .extension(extension)
                .image(image)
                .name(name)
                .order(order)
                .build();
    }
}
