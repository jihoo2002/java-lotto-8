package lotto.dto;

import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public record LottoResultDto(String description, int prize, int count) {

    public static List<LottoResultDto> from(Map<LottoRank, Integer> rankCount) {
        return rankCount.entrySet().stream()
                .map(entry -> new LottoResultDto(
                        entry.getKey().getDescription(),
                        entry.getKey().getPrice(),
                        entry.getValue()
                ))
                .toList();
    }
}

