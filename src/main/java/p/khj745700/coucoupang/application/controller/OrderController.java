package p.khj745700.coucoupang.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import p.khj745700.coucoupang.application.aop.annotation.LoginCheck;
import p.khj745700.coucoupang.application.dto.request.order.OrderModifyRequest;
import p.khj745700.coucoupang.application.dto.request.order.OrderRegisterRequest;
import p.khj745700.coucoupang.application.dto.response.order.OrderFindResponse;
import p.khj745700.coucoupang.application.service.order.IFindOrderService;
import p.khj745700.coucoupang.application.service.order.IModifyOrderService;
import p.khj745700.coucoupang.application.service.order.IRegisterOrderService;
import p.khj745700.coucoupang.application.service.order.IRemoveOrderService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    IRegisterOrderService registerOrderService;
    IFindOrderService findOrderService;
    IModifyOrderService modifyOrderService;
    IRemoveOrderService removeOrderService;

    @LoginCheck
    @PostMapping("/order")
    public ResponseEntity<?> register(@RequestBody List<OrderRegisterRequest> requests) {
        Long orderId = registerOrderService.register(requests);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<List<OrderFindResponse>> find(@PathVariable Long orderId) {
        List<OrderFindResponse> responses = findOrderService.find(orderId);
        return ResponseEntity.ok(responses);
    }

    @LoginCheck
    @PutMapping("/orders")
    public ResponseEntity<?> modify(@RequestBody List<OrderModifyRequest> requests) {
        modifyOrderService.modify(requests);
        return ResponseEntity.ok().build();
    }

    @LoginCheck
    @DeleteMapping("/orders")
    public ResponseEntity<?> remove(@RequestBody List<Long> orderItemIds) {
        removeOrderService.remove(orderItemIds);
        return ResponseEntity.ok().build();
    }

}
