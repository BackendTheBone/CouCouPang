package p.khj745700.coucoupang.application.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.S3DirConstant;
import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.dao.ProductDao;
import p.khj745700.coucoupang.application.dao.ProductImageDao;
import p.khj745700.coucoupang.application.dao.SellerDao;
import p.khj745700.coucoupang.application.dao.SessionDao;
import p.khj745700.coucoupang.application.domain.img.Image;
import p.khj745700.coucoupang.application.domain.member.Seller;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.ProductImage;
import p.khj745700.coucoupang.application.dto.request.img.ImageModifyRequest;
import p.khj745700.coucoupang.application.dto.request.product.ProductModifyRequest;
import p.khj745700.coucoupang.application.service.image.IRegisterImageService;
import p.khj745700.coucoupang.application.service.product.IModifyProductService;
import p.khj745700.coucoupang.application.service.product.description.IModifyProductDescriptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModifyProductService implements IModifyProductService {

    private final ProductDao productDao;
    private final SessionDao sessionDao;
    private final SellerDao sellerDao;
    private final IModifyProductDescriptionService modifyProductDescriptionService;
    private final ProductImageDao productImageDao;
    private final IRegisterImageService registerImageService;

    @Override
    @Transactional
    public void modifyProduct(ProductModifyRequest request) {
        Product findProduct = productDao.findByIdIfNotExistThrowException(request.getProductId());
        String username = sessionDao.getNotCheck(SessionConstants.USER_ID);
        Seller seller = sellerDao.findByUsername(username).get();

        findProduct.validSameSeller(seller);
        findProduct.modifyProduct(request.toProductEntity());

        if (request.getMainImages() != null && request.getMainImages().size() > 0) {
            List<ProductImage> productImages = productImageDao.findAllByProductId(findProduct.getId());
            for (ImageModifyRequest imr : request.getMainImages()) {
                if (imr.getIsDelete()) {
                    productImageDao.remove(productImages.get(imr.getOrder()).getId());
                }
                Image newImage = registerImageService.saveImage(imr.toImageUploadRequest(), S3DirConstant.PRODUCT_DESCRIPTION);
                productImageDao.save(ProductImage.builder()
                        .product(findProduct)
                        .order(imr.getOrder())
                        .image(newImage)
                        .build()
                );
            }

        }
        modifyProductDescriptionService.modifyProductDescription(request);

    }
}
