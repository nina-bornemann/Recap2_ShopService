[![Java CI with Maven](https://github.com/nina-bornemann/Recap2_ShopService/actions/workflows/maven.yml/badge.svg)](https://github.com/nina-bornemann/Recap2_ShopService/actions/workflows/maven.yml)

## ShopService (Extended)

A Java service to manage products and orders in a shop. It builds up to the former SHopService shared on this profile.
Supports placing orders, tracking order status, and storing timestamps for orders.

---

### 🚀 Features
- Manage a list of products (ProductRepo)
- Place orders by providing a list of product IDs
- Each order has:
  - Unique ID
  - Products
  - Status (PROCESSING, IN_DELIVERY, DELIVERED, etc.)
  - Timestamp of order creation

- Retrieve orders by status
- Update order status

---

### Usage

- Add an order (Throws an exception if a product ID does not exist.)
> List<String> productIds = List.of("P001", "P002");  
> Order newOrder = shopService.addOrder(productIds);

- Get orders by status
> List<Order> processingOrders = shopService.getAllOrdersOfStatus(Status.PROCESSING);

- Update order status
> shopService.updateOrder(orderId, Status.COMPLETED);  

---

### Notes
Uses in-memory repositories (ProductRepo, OrderMapRepo)
Each order stores a timestamp (Instant.now()) when created
Order objects are immutable; updating status creates a new object using withStatus()

### Requirements
Java 24+