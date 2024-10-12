import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Product {
    int productId;
    String productName;
    String category;
    BigDecimal price;
    float rating;
    int stock;
    char initial; // First letter of product name
    Character grade; // A grade for product (A, B, C, etc.)

    public Product(int productId, String productName, String category, BigDecimal price, float rating, int stock, char initial, Character grade) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.stock = stock;
        this.initial = initial;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", stock=" + stock +
                ", initial=" + initial +
                ", grade=" + grade +
                '}';
    }
}

public class DataStructuresDemo {

    public static void main(String[] args) {
        // Predefined unique data for 100 products
        List<Product> products = generateUniqueProducts();

        // 1. Array
        Product[] productArray = products.toArray(new Product[0]);
        Product[] newFilteredProductArray = Arrays.stream(productArray)
                .filter(p -> p.price.compareTo(new BigDecimal("100.00")) < 0)                .limit(15)
                .toArray(Product[]::new);


        for(Product product : newFilteredProductArray) {
            System.out.println(product.toString());
        }



        // 2. ArrayList
//        ArrayList<Product> productArrayList = new ArrayList<>(products);
//        System.out.println("\nArrayList:");
//        System.out.println(productArrayList);
//
//        // 3. LinkedList
//        LinkedList<Product> productLinkedList = new LinkedList<>();
//        System.out.println("\nLinkedList:");
//        System.out.println(productLinkedList);
//
//        // 4. List
//        List<Product> productList = new ArrayList<>(products);
//        System.out.println("\nList:");
//        System.out.println(productList);

        // 5. Queue
//        Queue<Product> productQueue = new LinkedList<>();
//        System.out.println("\nQueue:");
//        System.out.println(productQueue);

        // 6. ArrayDeque
//        ArrayDeque<Product> productArrayDeque = new ArrayDeque<>(products);
//        System.out.println("\nArrayDeque:");
//        System.out.println(productArrayDeque);
//
//        // 7. Map (Using HashMap)
//        Map<Integer, Product> productMap = new HashMap<>();
//        for (Product product : products) {
//            productMap.put(product.productId, product);
//        }
//        System.out.println("\nMap (HashMap):");
//        System.out.println(productMap);
//
//        // 8. BlockingQueue
//        BlockingQueue<Product> productBlockingQueue = new LinkedBlockingQueue<>(products);
//        System.out.println("\nBlockingQueue:");
//        productBlockingQueue.forEach(System.out::println);
//
//        // 9. PriorityQueue (sorted by price)
//        PriorityQueue<Product> productPriorityQueue = new PriorityQueue<>(Comparator.comparing(p -> p.price));
//        productPriorityQueue.addAll(products);
//        System.out.println("\nPriorityQueue (sorted by price):");
//        while (!productPriorityQueue.isEmpty()) {
//            System.out.println(productPriorityQueue.poll());
//        }
//
//        // 10. Stack
//        Stack<Product> productStack = new Stack<>();
//        productStack.addAll(products);
//        System.out.println("\nStack:");
//        while (!productStack.isEmpty()) {
//            System.out.println(productStack.pop());
//        }
//
//        // 11. Vector
//        Vector<Product> productVector = new Vector<>(products);
//        System.out.println("\nVector:");
//        System.out.println(productVector);
    }

    public static List<Product> generateUniqueProducts() {
        List<Product> products = new ArrayList<>();

        // Adding 100 unique product data
        products.add(new Product(1, "Laptop", "Electronics", BigDecimal.valueOf(999.99), 4.5f, 150, 'L', 'A'));
        products.add(new Product(2, "Smartphone", "Electronics", BigDecimal.valueOf(699.99), 4.7f, 300, 'S', 'A'));
        products.add(new Product(3, "Tablet", "Electronics", BigDecimal.valueOf(399.99), 4.2f, 250, 'T', 'B'));
        products.add(new Product(4, "Monitor", "Electronics", BigDecimal.valueOf(249.99), 4.1f, 100, 'M', 'B'));
        products.add(new Product(5, "Headphones", "Accessories", BigDecimal.valueOf(149.99), 4.4f, 500, 'H', 'A'));
        products.add(new Product(6, "Keyboard", "Accessories", BigDecimal.valueOf(89.99), 4.6f, 400, 'K', 'A'));
        products.add(new Product(7, "Mouse", "Accessories", BigDecimal.valueOf(49.99), 4.3f, 350, 'M', 'B'));
        products.add(new Product(8, "Smartwatch", "Gadgets", BigDecimal.valueOf(299.99), 4.8f, 200, 'S', 'A'));
        products.add(new Product(9, "Camera", "Electronics", BigDecimal.valueOf(899.99), 4.5f, 75, 'C', 'A'));
        products.add(new Product(10, "Printer", "Appliances", BigDecimal.valueOf(199.99), 3.9f, 120, 'P', 'C'));

        // More unique products
        products.add(new Product(11, "Desk", "Furniture", BigDecimal.valueOf(199.49), 4.6f, 50, 'D', 'A'));
        products.add(new Product(12, "Chair", "Furniture", BigDecimal.valueOf(119.99), 4.5f, 300, 'C', 'A'));
        products.add(new Product(13, "Microwave", "Appliances", BigDecimal.valueOf(89.99), 4.2f, 110, 'M', 'B'));
        products.add(new Product(14, "Oven", "Appliances", BigDecimal.valueOf(599.99), 4.7f, 90, 'O', 'A'));
        products.add(new Product(15, "Refrigerator", "Appliances", BigDecimal.valueOf(999.49), 4.6f, 80, 'R', 'A'));
        products.add(new Product(16, "Air Conditioner", "Appliances", BigDecimal.valueOf(699.99), 4.4f, 65, 'A', 'A'));
        products.add(new Product(17, "Fan", "Appliances", BigDecimal.valueOf(49.99), 4.1f, 500, 'F', 'B'));
        products.add(new Product(18, "Heater", "Appliances", BigDecimal.valueOf(129.99), 4.2f, 120, 'H', 'B'));
        products.add(new Product(19, "Washing Machine", "Appliances", BigDecimal.valueOf(749.99), 4.5f, 45, 'W', 'A'));
        products.add(new Product(20, "Dryer", "Appliances", BigDecimal.valueOf(499.99), 4.4f, 50, 'D', 'A'));

        products.add(new Product(21, "Smart TV", "Electronics", BigDecimal.valueOf(1200.99), 4.5f, 80, 'S', 'A'));
        products.add(new Product(22, "Bluetooth Speaker", "Gadgets", BigDecimal.valueOf(199.99), 4.7f, 180, 'B', 'A'));
        products.add(new Product(23, "Gaming Console", "Electronics", BigDecimal.valueOf(499.99), 4.6f, 150, 'G', 'A'));
        products.add(new Product(24, "Router", "Electronics", BigDecimal.valueOf(99.99), 4.1f, 300, 'R', 'B'));
        products.add(new Product(25, "Projector", "Electronics", BigDecimal.valueOf(799.99), 4.3f, 90, 'P', 'A'));
        products.add(new Product(26, "Coffee Maker", "Appliances", BigDecimal.valueOf(89.99), 4.4f, 500, 'C', 'B'));
        products.add(new Product(27, "Blender", "Appliances", BigDecimal.valueOf(69.99), 4.2f, 200, 'B', 'A'));
        products.add(new Product(28, "Air Purifier", "Appliances", BigDecimal.valueOf(199.99), 4.6f, 120, 'A', 'A'));
        products.add(new Product(29, "Electric Kettle", "Appliances", BigDecimal.valueOf(49.99), 4.5f, 400, 'E', 'B'));
        products.add(new Product(30, "Vacuum Cleaner", "Appliances", BigDecimal.valueOf(299.99), 4.7f, 140, 'V', 'A'));

        // Remaining unique products
        products.add(new Product(31, "Hair Dryer", "Appliances", BigDecimal.valueOf(49.99), 4.4f, 80, 'H', 'B'));
        products.add(new Product(32, "Water Dispenser", "Appliances", BigDecimal.valueOf(149.99), 4.3f, 70, 'W', 'A'));
        products.add(new Product(33, "Fitness Tracker", "Gadgets", BigDecimal.valueOf(199.99), 4.8f, 300, 'F', 'A'));
        products.add(new Product(34, "Smart Glasses", "Gadgets", BigDecimal.valueOf(599.99), 4.7f, 50, 'S', 'A'));
        products.add(new Product(35, "Action Camera", "Electronics", BigDecimal.valueOf(399.99), 4.6f, 40, 'A', 'B'));
        products.add(new Product(36, "Drone", "Gadgets", BigDecimal.valueOf(899.99), 4.5f, 25, 'D', 'A'));
        products.add(new Product(37, "3D Printer", "Appliances", BigDecimal.valueOf(1499.99), 4.4f, 15, 'P', 'A'));
        products.add(new Product(38, "E-Reader", "Gadgets", BigDecimal.valueOf(129.99), 4.5f, 300, 'E', 'B'));
        products.add(new Product(39, "External Hard Drive", "Electronics", BigDecimal.valueOf(99.99), 4.4f, 400, 'H', 'A'));
        products.add(new Product(40, "Power Bank", "Gadgets", BigDecimal.valueOf(29.99), 4.2f, 450, 'P', 'B'));

        products.add(new Product(41, "Portable Speaker", "Gadgets", BigDecimal.valueOf(79.99), 4.6f, 350, 'P', 'A'));
        products.add(new Product(42, "LED Light", "Appliances", BigDecimal.valueOf(19.99), 4.8f, 600, 'L', 'A'));
        products.add(new Product(43, "Smart Lock", "Gadgets", BigDecimal.valueOf(249.99), 4.7f, 80, 'S', 'A'));
        products.add(new Product(44, "Digital Thermostat", "Appliances", BigDecimal.valueOf(299.99), 4.5f, 70, 'T', 'A'));
        products.add(new Product(45, "Security Camera", "Electronics", BigDecimal.valueOf(199.99), 4.4f, 150, 'S', 'A'));
        products.add(new Product(46, "Electric Scooter", "Gadgets", BigDecimal.valueOf(899.99), 4.3f, 30, 'E', 'A'));
        products.add(new Product(47, "Electric Toothbrush", "Appliances", BigDecimal.valueOf(49.99), 4.5f, 200, 'E', 'B'));
        products.add(new Product(48, "Electric Shaver", "Appliances", BigDecimal.valueOf(79.99), 4.4f, 150, 'S', 'B'));
        products.add(new Product(49, "Steam Iron", "Appliances", BigDecimal.valueOf(59.99), 4.3f, 120, 'S', 'B'));
        products.add(new Product(50, "Dehumidifier", "Appliances", BigDecimal.valueOf(199.99), 4.6f, 90, 'D', 'A'));

        products.add(new Product(51, "Dishwasher", "Appliances", BigDecimal.valueOf(699.99), 4.5f, 70, 'D', 'A'));
        products.add(new Product(52, "Electric Blanket", "Appliances", BigDecimal.valueOf(49.99), 4.2f, 200, 'E', 'B'));
        products.add(new Product(53, "Smart Doorbell", "Gadgets", BigDecimal.valueOf(149.99), 4.7f, 150, 'S', 'A'));
        products.add(new Product(54, "Portable Air Conditioner", "Appliances", BigDecimal.valueOf(399.99), 4.4f, 60, 'P', 'B'));
        products.add(new Product(55, "Waffle Maker", "Appliances", BigDecimal.valueOf(39.99), 4.3f, 180, 'W', 'B'));
        products.add(new Product(56, "Rice Cooker", "Appliances", BigDecimal.valueOf(99.99), 4.5f, 120, 'R', 'A'));
        products.add(new Product(57, "Juicer", "Appliances", BigDecimal.valueOf(59.99), 4.6f, 200, 'J', 'A'));
        products.add(new Product(58, "Food Processor", "Appliances", BigDecimal.valueOf(199.99), 4.6f, 150, 'F', 'A'));
        products.add(new Product(59, "Induction Cooktop", "Appliances", BigDecimal.valueOf(299.99), 4.7f, 110, 'I', 'A'));
        products.add(new Product(60, "Deep Fryer", "Appliances", BigDecimal.valueOf(129.99), 4.5f, 140, 'D', 'A'));


        products.add(new Product(61, "Ergonomic Chair", "Furniture", BigDecimal.valueOf(349.99), 4.6f, 100, 'E', 'A'));
        products.add(new Product(62, "Laser Printer", "Electronics", BigDecimal.valueOf(699.99), 4.5f, 75, 'L', 'A'));
        products.add(new Product(63, "Graphics Card", "Electronics", BigDecimal.valueOf(1199.99), 4.8f, 50, 'G', 'A'));
        products.add(new Product(64, "Electric Bike", "Gadgets", BigDecimal.valueOf(1299.99), 4.9f, 20, 'E', 'A'));
        products.add(new Product(65, "Standing Desk", "Furniture", BigDecimal.valueOf(599.99), 4.6f, 60, 'S', 'A'));
        products.add(new Product(66, "Mechanical Keyboard", "Accessories", BigDecimal.valueOf(149.99), 4.8f, 400, 'M', 'A'));
        products.add(new Product(67, "Wireless Charger", "Gadgets", BigDecimal.valueOf(49.99), 4.4f, 300, 'W', 'B'));
        products.add(new Product(68, "Noise Cancelling Earbuds", "Accessories", BigDecimal.valueOf(199.99), 4.7f, 150, 'N', 'A'));
        products.add(new Product(69, "Smart Mirror", "Gadgets", BigDecimal.valueOf(499.99), 4.6f, 75, 'S', 'A'));

        products.add(new Product(70, "Electric Skateboard", "Gadgets", BigDecimal.valueOf(999.99), 4.5f, 40, 'E', 'A'));
        products.add(new Product(71, "Bluetooth Keyboard", "Accessories", BigDecimal.valueOf(89.99), 4.3f, 250, 'B', 'B'));
        products.add(new Product(72, "4K Ultra HD Projector", "Electronics", BigDecimal.valueOf(1399.99), 4.9f, 30, 'P', 'A'));
        products.add(new Product(73, "Portable Power Station", "Gadgets", BigDecimal.valueOf(799.99), 4.8f, 50, 'P', 'A'));
        products.add(new Product(74, "Curved Monitor", "Electronics", BigDecimal.valueOf(699.99), 4.5f, 90, 'C', 'B'));
        products.add(new Product(75, "Smart Thermostat", "Gadgets", BigDecimal.valueOf(249.99), 4.6f, 100, 'S', 'A'));
        products.add(new Product(76, "Robot Vacuum", "Appliances", BigDecimal.valueOf(599.99), 4.7f, 80, 'R', 'A'));
        products.add(new Product(77, "Gaming Laptop", "Electronics", BigDecimal.valueOf(1999.99), 4.9f, 25, 'G', 'A'));
        products.add(new Product(78, "Video Doorbell", "Gadgets", BigDecimal.valueOf(199.99), 4.5f, 300, 'V', 'B'));
        products.add(new Product(79, "Electric Grill", "Appliances", BigDecimal.valueOf(149.99), 4.4f, 120, 'E', 'B'));

        products.add(new Product(80, "Portable Air Purifier", "Appliances", BigDecimal.valueOf(349.99), 4.6f, 90, 'P', 'A'));
        products.add(new Product(81, "Home Security System", "Gadgets", BigDecimal.valueOf(599.99), 4.8f, 65, 'H', 'A'));
        products.add(new Product(82, "Digital Camera", "Electronics", BigDecimal.valueOf(1299.99), 4.7f, 45, 'D', 'A'));
        products.add(new Product(83, "Cordless Drill", "Tools", BigDecimal.valueOf(89.99), 4.5f, 200, 'C', 'B'));
        products.add(new Product(84, "Solar Charger", "Gadgets", BigDecimal.valueOf(199.99), 4.6f, 150, 'S', 'A'));
        products.add(new Product(85, "Wireless Security Camera", "Gadgets", BigDecimal.valueOf(299.99), 4.7f, 100, 'W', 'A'));
        products.add(new Product(86, "Electric Scooter", "Gadgets", BigDecimal.valueOf(799.99), 4.5f, 55, 'E', 'A'));
        products.add(new Product(87, "Smart Garage Door Opener", "Gadgets", BigDecimal.valueOf(229.99), 4.4f, 140, 'S', 'B'));
        products.add(new Product(88, "Gaming Mouse", "Accessories", BigDecimal.valueOf(79.99), 4.5f, 450, 'G', 'A'));
        products.add(new Product(89, "Smart Plug", "Gadgets", BigDecimal.valueOf(29.99), 4.6f, 500, 'S', 'A'));

        products.add(new Product(90, "Smart Light Bulb", "Gadgets", BigDecimal.valueOf(39.99), 4.7f, 600, 'S', 'A'));
        products.add(new Product(91, "Air Fryer", "Appliances", BigDecimal.valueOf(199.99), 4.8f, 250, 'A', 'A'));
        products.add(new Product(92, "Mini Fridge", "Appliances", BigDecimal.valueOf(399.99), 4.6f, 75, 'M', 'B'));
        products.add(new Product(93, "Smart Water Bottle", "Gadgets", BigDecimal.valueOf(49.99), 4.3f, 350, 'S', 'B'));
        products.add(new Product(94, "Portable Solar Generator", "Gadgets", BigDecimal.valueOf(1199.99), 4.9f, 40, 'P', 'A'));
        products.add(new Product(95, "Robot Lawn Mower", "Gadgets", BigDecimal.valueOf(1799.99), 4.7f, 30, 'R', 'A'));
        products.add(new Product(96, "Electric Fireplace", "Appliances", BigDecimal.valueOf(499.99), 4.6f, 80, 'E', 'B'));
        products.add(new Product(97, "Wi-Fi Range Extender", "Electronics", BigDecimal.valueOf(79.99), 4.4f, 320, 'W', 'B'));
        products.add(new Product(98, "Wireless Earbuds", "Accessories", BigDecimal.valueOf(129.99), 4.8f, 200, 'W', 'A'));
        products.add(new Product(99, "Electric Standing Fan", "Appliances", BigDecimal.valueOf(129.99), 4.5f, 150, 'E', 'B'));
        products.add(new Product(100, "Portable Speaker", "Gadgets", BigDecimal.valueOf(99.99), 4.7f, 300, 'P', 'A'));
        products.add(new Product(101, "Gaming Headset", "Accessories", BigDecimal.valueOf(249.99), 4.7f, 250, 'G', 'A'));


        return products;
    }

}
