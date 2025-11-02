package lotto.util;

import lotto.view.OutputView;

import java.util.function.Function;
import java.util.function.Supplier;

public class InputUtils {

    public static <T> T readValidInput(Supplier<String> inputSupplier, Function<String, T> parser) {
        while (true) {
            String input = getInput(inputSupplier);
            try {
                return parseInput(input, parser);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private static String getInput(Supplier<String> inputSupplier) {
        return inputSupplier.get();
    }

    private static <T> T parseInput(String input, Function<String, T> parser) {
        return parser.apply(input);
    }
}
