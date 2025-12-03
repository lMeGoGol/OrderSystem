package app;

import factory.ProductFactory;
import process.OrderProcessor;
import repository.OrderRepository;
import order.Order;
import multithreading.AsyncExecutor;

import java.util.stream.IntStream;

public class AppMain {
    private final static ProductFactory productFactory = new ProductFactory();
    private final static OrderRepository orderRepository = new OrderRepository();
    private final static AsyncExecutor asyncExecutor = new AsyncExecutor();

    public static void main(final String[] args) {
        IntStream.range(0, 100)
                .mapToObj(number -> {
                    final var product = isEven(number)
                            ? productFactory.createElectronics()
                            : productFactory.createClothing();

                    return Order.builder()
                            .id(Integer.toString(number))
                            .product(product)
                            .build();
                }).forEach(orderRepository::saveOrder);

        orderRepository.findAllProducts()
                .stream()
                .map(OrderProcessor::new)
                .forEach(orderProcessor -> asyncExecutor.execute(orderProcessor::processOrder));
    }

    private static boolean isEven(final int i) {
        return i % 2 == 0;
    }
}
