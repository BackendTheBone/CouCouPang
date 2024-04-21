package p.khj745700.coucoupang.application.service.product;

import p.khj745700.coucoupang.application.dto.request.product.ProductRegisterRequest;

public interface IRegisterProductService {

    Long addProduct(ProductRegisterRequest request);
}
