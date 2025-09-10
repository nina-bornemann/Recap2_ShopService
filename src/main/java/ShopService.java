import lombok.With;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public ShopService() {}
    public ShopService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new RuntimeException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, Status.PROCESSING, Instant.now());
        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getAllOrdersOfStatus(Status s) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.status().equals(s))
                .collect(Collectors.toList());
    }

    public void updateOrder(String orderId, Status status) {
        Order updatedOrder = orderRepo.getOrderById(orderId).withStatus(status);
        orderRepo.addOrder(updatedOrder);
        orderRepo.removeOrder(orderId);
    }
}
