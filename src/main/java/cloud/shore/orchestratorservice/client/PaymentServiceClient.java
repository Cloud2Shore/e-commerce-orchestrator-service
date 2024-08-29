package cloud.shore.orchestratorservice.client;

import cloud.shore.orchestratorservice.model.PaymentDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "e-commerce-payment-service")
public interface PaymentServiceClient {

    @PostMapping("/pay/{orderId}")
    boolean processPayment(@PathVariable("orderId") String orderId, @RequestBody PaymentDetails paymentDetails);
}
