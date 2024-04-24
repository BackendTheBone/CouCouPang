package p.khj745700.coucoupang.application.service.product.description;

import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescription;
import p.khj745700.coucoupang.application.dto.request.product.ProductModifyRequest;
import p.khj745700.coucoupang.application.dto.request.product.ProductRegisterRequest;

public interface IModifyProductDescriptionService {

    public void modifyProductDescription(ProductModifyRequest productModifyRequest);
}
