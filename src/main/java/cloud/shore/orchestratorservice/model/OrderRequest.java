package cloud.shore.orchestratorservice.model;

public record OrderRequest(
        String productId,
        PaymentDetails paymentDetails,
        ShippingDetails shippingDetails,
        String orderId
) {
}

