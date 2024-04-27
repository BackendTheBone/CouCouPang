package p.khj745700.coucoupang.application.service.payment;

import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.dto.request.payment.RegisterPaymentRequest;

import java.util.List;

public interface IRegisterPaymentService {
    Long register(List<RegisterPaymentRequest> requests, Member member);
}
