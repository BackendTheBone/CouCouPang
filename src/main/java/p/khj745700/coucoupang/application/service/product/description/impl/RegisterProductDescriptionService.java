package p.khj745700.coucoupang.application.service.product.description.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.S3DirConstant;
import p.khj745700.coucoupang.application.dao.ImageDao;
import p.khj745700.coucoupang.application.dao.ProductDescriptionDao;
import p.khj745700.coucoupang.application.dao.ProductDescriptionImageDao;
import p.khj745700.coucoupang.application.domain.img.Image;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescription;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescriptionImage;
import p.khj745700.coucoupang.application.dto.request.img.ImageUploadRequest;
import p.khj745700.coucoupang.application.dto.request.product.ProductRegisterRequest;
import p.khj745700.coucoupang.application.service.image.IRegisterImageService;
import p.khj745700.coucoupang.application.service.product.description.IRegisterProductDescriptionService;

@Service
@RequiredArgsConstructor
public class RegisterProductDescriptionService implements IRegisterProductDescriptionService {
    private final ProductDescriptionDao productDescriptionDao;
    private final ProductDescriptionImageDao productDescriptionImageDao;
    private final IRegisterImageService registerImageService;

    @Transactional
    public ProductDescription save(Product product, ProductRegisterRequest productRegisterRequest) {
        ProductDescription newProductDescription = productDescriptionDao.save(productRegisterRequest.toProductDescriptionEntity(product));

        for(int i = 0 ; i < productRegisterRequest.getDescriptionImages().size(); i++) {
            ImageUploadRequest target = productRegisterRequest.getDescriptionImages().get(i);
            Image newImage = registerImageService.saveImage(target, S3DirConstant.PRODUCT_DESCRIPTION);
            productDescriptionImageDao.save(ProductDescriptionImage.builder()
                    .productDescription(newProductDescription)
                    .order(target.getOrder())
                    .image(newImage)
                    .build()
                );
        }
        return newProductDescription;
    }
}
