package p.khj745700.coucoupang.application.service.product.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.S3DirConstant;
import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.dao.*;
import p.khj745700.coucoupang.application.domain.img.Image;
import p.khj745700.coucoupang.application.domain.member.Seller;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.ProductImage;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescription;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescriptionImage;
import p.khj745700.coucoupang.application.dto.request.img.ImageUploadRequest;
import p.khj745700.coucoupang.application.dto.request.product.ProductRegisterRequest;
import p.khj745700.coucoupang.application.service.image.IRegisterImageService;
import p.khj745700.coucoupang.application.service.image.RegisterImageService;
import p.khj745700.coucoupang.application.service.product.IRegisterProductService;
import p.khj745700.coucoupang.application.service.product.description.IRegisterProductDescriptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterProductService implements IRegisterProductService {

    private final SessionDao sessionDao;
    private final SellerDao sellerDao;
    private final ProductDao productDao;
    private final ProductImageDao productImageDao;
    private final IRegisterImageService registerImageService;
    private final IRegisterProductDescriptionService registerProductDescriptionService;

    @Override
    @Transactional
    public Long addProduct(ProductRegisterRequest request) {
        String sellerUsername = sessionDao.getNotCheck(SessionConstants.USER_ID);
        Seller seller = sellerDao.findByUsernameIfNotExistThrowException(sellerUsername);
        Product newProduct = request.toProductEntity(seller);
        newProduct = productDao.save(newProduct);


        for(int i = 0 ; i < request.getMainImages().size(); i++) {
            ImageUploadRequest target = request.getDescriptionImages().get(i);
            Image newImage = registerImageService.saveImage(target, S3DirConstant.PRODUCT_DESCRIPTION);
            productImageDao.save(ProductImage.builder()
                    .product(newProduct)
                    .order(target.getOrder())
                    .image(newImage)
                    .build()
            );
        }

        registerProductDescriptionService.save(newProduct, request);
        return newProduct.getId();
    }
}
