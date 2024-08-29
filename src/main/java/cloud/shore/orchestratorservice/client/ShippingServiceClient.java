package cloud.shore.orchestratorservice.client;

import cloud.shore.orchestratorservice.model.ShippingDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shipping-service")
public interface ShippingServiceClient {

    @PostMapping("/shipping/initiate")
    boolean initiateShipping(@RequestBody ShippingDetails shippingDetails);
}
