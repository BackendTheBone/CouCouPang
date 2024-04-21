package p.khj745700.coucoupang.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p.khj745700.coucoupang.application.aop.annotation.PreAuth;
import p.khj745700.coucoupang.application.domain.member.MemberType;
import p.khj745700.coucoupang.application.dto.request.product.ProductRegisterRequest;
import p.khj745700.coucoupang.application.service.product.IRegisterProductService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

}
