package cloud.shore.orchestratorservice.model;

public record PaymentDetails(
        String paymentMethod,
        String cardNumber,
        String cardExpiry,
        String cardCvv,
        String accountNumber,
        String bankName
) {
}

