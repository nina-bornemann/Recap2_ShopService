public class Main {
    public static void main(String[] args) {
        ;
        OrderListRepo orderRepo = new OrderListRepo();
        ShopService shop = new ShopService(orderRepo);

    }
}
