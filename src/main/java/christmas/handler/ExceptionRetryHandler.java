package christmas.handler;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionRetryHandler {
    private final Consumer<String> exceptionMessagePrinter;

    public ExceptionRetryHandler(Consumer<String> errorPrinter) {
        this.exceptionMessagePrinter = errorPrinter;
    }

    public <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                exceptionMessagePrinter.accept(e.getMessage());
            }
        }
    }
}
