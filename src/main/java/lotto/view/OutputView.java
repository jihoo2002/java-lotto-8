package lotto.view;

import lotto.dto.LottoResultDto;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");

    private static final String PURCHASED_COUNT = "%d개를 구매했습니다.";
    private static final String STATISTICS_HEADER = "당첨 통계";
    private static final String STATISTICS_DIVIDER = "---";
    private static final String EARNINGS_RATE = "총 수익률은 %.1f%%입니다.%n";
    private static final String STATISTICS_LINE_FORMAT = "%s (%s원) - %d개%n";

    public static void printPurchasedLottos(int count, List<List<Integer>> lottos) {
        System.out.println();
        System.out.println(String.format(PURCHASED_COUNT, count));
        lottos.forEach(numbers -> System.out.println(numbers));
        System.out.println();
    }

    public static void printLottoStatistics(List<LottoResultDto> results) {
        System.out.println(STATISTICS_HEADER);
        System.out.println(STATISTICS_DIVIDER);
        results.forEach(result -> {
            String formattedPrize = MONEY_FORMAT.format(result.prize());
            System.out.printf(STATISTICS_LINE_FORMAT,
                    result.description(), formattedPrize, result.count());
        });
    }

    public static void printEarningsRate(double rate) {
        System.out.printf(EARNINGS_RATE, rate);
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
