package p.khj745700.coucoupang.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import p.khj745700.coucoupang.application.aop.annotation.LoginCheck;
import p.khj745700.coucoupang.application.dto.request.payment.ModifyPaymentRequest;
import p.khj745700.coucoupang.application.dto.request.payment.RegisterPaymentRequest;
import p.khj745700.coucoupang.application.dto.response.payment.PaymentFindResponse;
import p.khj745700.coucoupang.application.service.payment.IFindPaymentService;
import p.khj745700.coucoupang.application.service.payment.IModifyPaymentService;
import p.khj745700.coucoupang.application.service.payment.IRegisterPaymentService;
import p.khj745700.coucoupang.application.service.payment.IRemovePaymentService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    IRegisterPaymentService registerPaymentService;
    IFindPaymentService findPaymentService;
    IModifyPaymentService modifyPaymentService;
    IRemovePaymentService removePaymentService;

    @LoginCheck
    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody List<RegisterPaymentRequest> requests) {
        Long paymentId = registerPaymentService.register(requests);
        return ResponseEntity.created(URI.create("/payments/" + paymentId)).build();
    }

    @LoginCheck
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        List<PaymentFindResponse> responses = findPaymentService.find(id);
        return ResponseEntity.ok(responses);
    }

    @LoginCheck
    @PutMapping("")
    public ResponseEntity<?> modify(@RequestBody List<ModifyPaymentRequest> requests) {
        modifyPaymentService.modify(requests);
        return ResponseEntity.ok().build();
    }

    @LoginCheck
    @DeleteMapping("")
    public ResponseEntity<?> remove(@RequestBody List<Long> payProductId) {
        removePaymentService.remove(payProductId);
        return ResponseEntity.ok().build();
    }

}
