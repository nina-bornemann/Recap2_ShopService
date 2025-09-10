import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), Status.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertNull(actual);
    }

    @Test
    void getAllProcessingOrders_isEqual_whenProcessing() {
        Product p1 = new Product("3", "Banana");
        Product p2 = new Product("4", "Pineapple");
        List<Order> orders = new ArrayList<>();
        Order o1 = new Order("2", List.of(p1), Status.PROCESSING);
        Order o2 = new Order("5", List.of(p2), Status.IN_DELIVERY);
        orders.add(o1);
        orders.add(o2);
        OrderListRepo orderRepo = new OrderListRepo(orders);
        ShopService shopService = new ShopService(orderRepo);
        List<Order> expected = List.of(o1);
        List<Order> actual = shopService.getAllOrdersOfStatus(Status.PROCESSING);
        assertEquals(expected, actual);
    }
}
