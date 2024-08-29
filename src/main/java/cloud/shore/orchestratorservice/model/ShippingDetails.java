package cloud.shore.orchestratorservice.model;

public record ShippingDetails(
        String address,
        String city,
        String state,
        String zipCode,
        String country,
        String phoneNumber
) {
}
