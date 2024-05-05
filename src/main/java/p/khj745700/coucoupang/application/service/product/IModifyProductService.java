package p.khj745700.coucoupang.application.service.product;

import p.khj745700.coucoupang.application.dto.request.product.ProductModifyRequest;

public interface IModifyProductService {
    //상품 수정 -> 상품 설명, 상품 사진, 상품 상세 사진, 상품 수량 추가, 상품 판매 상태 수정, 상품 가격 변경
    public void modifyProduct(ProductModifyRequest request);
}
