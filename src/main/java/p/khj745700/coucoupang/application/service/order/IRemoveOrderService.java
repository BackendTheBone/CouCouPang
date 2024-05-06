package p.khj745700.coucoupang.application.service.order;

import java.util.List;

public interface IRemoveOrderService {
    void remove(List<Long> orderItemIds);
}
