package cloud.shore.orchestratorservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @PostMapping("/order/{orderId}/status")
    void updateOrderStatus(@PathVariable("orderId") String orderId, @RequestBody String status);
}
