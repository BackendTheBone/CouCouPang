package p.khj745700.coucoupang.application.service.product.description.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.S3DirConstant;
import p.khj745700.coucoupang.application.dao.ProductDescriptionDao;
import p.khj745700.coucoupang.application.dao.ProductDescriptionImageDao;
import p.khj745700.coucoupang.application.domain.img.Image;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescription;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescriptionImage;
import p.khj745700.coucoupang.application.dto.request.img.ImageModifyRequest;
import p.khj745700.coucoupang.application.dto.request.product.ProductModifyRequest;
import p.khj745700.coucoupang.application.service.image.IRegisterImageService;
import p.khj745700.coucoupang.application.service.product.description.IModifyProductDescriptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModifyProductDescriptionService implements IModifyProductDescriptionService {

    private final ProductDescriptionDao productDescriptionDao;
    private final ProductDescriptionImageDao productDescriptionImageDao;
    private final IRegisterImageService registerImageService;

    @Transactional
    public void modifyProductDescription(ProductModifyRequest productModifyRequest) {
        ProductDescription target = productDescriptionDao.findByProductIdIfNotExistThrowException(productModifyRequest.getProductId());
        target.modifyProductDescription(productModifyRequest.toProductDescriptionEntity());

        //이미지 수정
        if (productModifyRequest.getDescriptionImages() != null && productModifyRequest.getDescriptionImages().size() > 0) {
            List<ProductDescriptionImage> productImages = productDescriptionImageDao.findAllByProductDescriptionId(target.getId());
            for (ImageModifyRequest imr : productModifyRequest.getDescriptionImages()) {
                if (imr.getIsDelete()) {
                    productDescriptionImageDao.remove(productImages.get(imr.getOrder()).getId());
                }
                Image newImage = registerImageService.saveImage(imr.toImageUploadRequest(), S3DirConstant.PRODUCT_DESCRIPTION);
                productDescriptionImageDao.save(ProductDescriptionImage.builder()
                        .productDescription(target)
                        .order(imr.getOrder())
                        .image(newImage)
                        .build()
                );
            }
        }
    }
}
