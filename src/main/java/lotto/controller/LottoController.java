package lotto.controller;

import lotto.domain.*;
import lotto.dto.LottoResultDto;
import lotto.parser.BonusNumberParser;
import lotto.parser.WinningNumberParser;
import lotto.parser.AmountParser;
import lotto.util.InputUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final LottoMachine lottoMachine;
    private final RandomValueGenerator generator;

    public LottoController() {
        this.generator = new RandomValueGenerator();
        this.lottoMachine = new LottoMachine(generator);
    }

    public void lottoGame() {
        int purchaseAmount = getPurchaseAmount();
        Lottos purchasedLottos = buyAndDisplayLottos(purchaseAmount);
        WinningNumbers winning = getWinningNumbers();
        LottoResult lottoResult = calculateResults(purchasedLottos, winning);
        printResults(lottoResult, purchaseAmount);
    }

    private int getPurchaseAmount() {
        return InputUtils.readValidInput(InputView::inputPurchaseAmount, AmountParser::validate);
    }

    private Lottos buyAndDisplayLottos(int purchaseAmount) {
        Lottos purchasedLottos = lottoMachine.buyAutoLottos(purchaseAmount);

        OutputView.printPurchasedLottos(
                purchasedLottos.size(),
                purchasedLottos.getLottos().stream()
                        .map(Lotto::getNumbers)
                        .toList()
        );

        return purchasedLottos;
    }

    private WinningNumbers getWinningNumbers() {
        while (true) {
            try {
                List<Integer> winningNumbers = readWinningNumbers();
                int bonusNumber = readBonusNumber();

                return new WinningNumbers(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> readWinningNumbers() {
        return InputUtils.readValidInput(
                InputView::inputWinningNumbers,
                WinningNumberParser::parse
        );
    }

    private int readBonusNumber() {
        return InputUtils.readValidInput(
                InputView::inputBonus,
                BonusNumberParser::parse
        );
    }

    private LottoResult calculateResults(Lottos purchasedLottos, WinningNumbers winning) {
        LottoResult lottoResult = new LottoResult(winning);
        lottoResult.calculate(purchasedLottos);
        return lottoResult;
    }

    private void printResults(LottoResult lottoResult, int purchaseAmount) {
        List<LottoResultDto> resultDtos = LottoResultDto.from(lottoResult.getRankCount());
        OutputView.printLottoStatistics(resultDtos);

        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);
        OutputView.printEarningsRate(profitRate);
    }
}
