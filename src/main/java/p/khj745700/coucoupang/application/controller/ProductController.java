package p.khj745700.coucoupang.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import p.khj745700.coucoupang.application.aop.annotation.PreAuth;
import p.khj745700.coucoupang.application.domain.member.MemberType;
import p.khj745700.coucoupang.application.dto.request.product.ProductModifyRequest;
import p.khj745700.coucoupang.application.dto.request.product.ProductRegisterRequest;
import p.khj745700.coucoupang.application.service.product.IModifyProductService;
import p.khj745700.coucoupang.application.service.product.IRegisterProductService;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final IRegisterProductService registerProductService;
    private final IModifyProductService modifyProductService;

    @PostMapping("")
    public ResponseEntity<?> productAdd(@RequestBody ProductRegisterRequest request) {
        Long createdId = registerProductService.addProduct(request);
        return ResponseEntity.created(URI.create("/product/" + createdId)).build();
    }

    @PatchMapping("")
    public ResponseEntity<?> productModify(@RequestBody ProductModifyRequest request) {
        modifyProductService.modifyProduct(request);
        return ResponseEntity.ok().build();
    }
}
