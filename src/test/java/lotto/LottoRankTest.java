package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class LottoRankTest {
    @DisplayName("로또 당첨 금액 테스트")
    @ParameterizedTest(name = "{0}의 당첨 금액은 {1}원이다.")
    @CsvSource({
            "FIRST, 2000000000",
            "SECOND, 30000000",
            "THIRD, 1500000",
            "FOURTH, 50000",
            "FIFTH, 5000",
            "NONE, 0",
    })
    void price(LottoRank lottoRank, long expectedPrice) {
        assertThat(lottoRank.getPrice()).isEqualTo(expectedPrice);
    }

    @DisplayName("로또 등수 테스트")
    @ParameterizedTest(name = "{displayName} - 맞춘 개수 {0}개, 보너스 맞춘 여부 {1}, 결과 {2}등")
    @MethodSource("rank")
    void from(int unionCount, boolean bonusMatched, LottoRank expected) {
        LottoRank actual = LottoRank.from(unionCount, bonusMatched);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> rank(){
        return Stream.of(
                Arguments.of(6, false, LottoRank.FIRST),
                Arguments.of(5, true, LottoRank.SECOND),
                Arguments.of(5, false, LottoRank.THIRD),
                Arguments.of(4, false, LottoRank.FOURTH),
                Arguments.of(3, false, LottoRank.FIFTH),
                Arguments.of(2, false, LottoRank.NONE),
                Arguments.of(1, false, LottoRank.NONE),
                Arguments.of(0, false, LottoRank.NONE)
        );
    }
}