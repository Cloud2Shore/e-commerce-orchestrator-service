package cloud.shore.orchestratorservice.controller;

import cloud.shore.orchestratorservice.client.InventoryServiceClient;
import cloud.shore.orchestratorservice.client.OrderServiceClient;
import cloud.shore.orchestratorservice.client.PaymentServiceClient;
import cloud.shore.orchestratorservice.client.ShippingServiceClient;
import cloud.shore.orchestratorservice.model.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrchestratorController {

    private final InventoryServiceClient inventoryServiceClient;

    private final PaymentServiceClient paymentServiceClient;

    private final ShippingServiceClient shippingServiceClient;

    private final OrderServiceClient orderServiceClient;

    public OrchestratorController(InventoryServiceClient inventoryServiceClient, PaymentServiceClient paymentServiceClient, ShippingServiceClient shippingServiceClient, OrderServiceClient orderServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
        this.paymentServiceClient = paymentServiceClient;
        this.shippingServiceClient = shippingServiceClient;
        this.orderServiceClient = orderServiceClient;
    }

    @PostMapping("/processOrder")
    public ResponseEntity<String> processOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Step 1: Check Inventory
            boolean isInventoryAvailable = inventoryServiceClient.checkInventory(orderRequest.productId());
            if (!isInventoryAvailable) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventory not available");
            }

            // Step 2: Process Payment
            boolean isPaymentSuccessful = paymentServiceClient.processPayment(orderRequest.orderId(), orderRequest.paymentDetails());
            if (!isPaymentSuccessful) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed");
            }

            // Step 3: Initiate Shipping
            boolean isShippingInitiated = shippingServiceClient.initiateShipping(orderRequest.shippingDetails());
            if (!isShippingInitiated) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shipping failed");
            }

            // Step 4: Update Order Status
            orderServiceClient.updateOrderStatus(orderRequest.orderId(), "SHIPPED");

            return ResponseEntity.ok("Order processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing order");
        }
    }
}

