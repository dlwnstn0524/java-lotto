package lotto;

import common.converter.StringToLottoNumberConverter;
import common.converter.StringToLottoNumberSetConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {

    @DisplayName("당첨 로또는 6개의 당첨 번호와 1개의 보너스 번호가 있다")
    @ParameterizedTest
    @CsvSource({
            "1 2 3 4 5 6, 7"
    })
    void lottoNumbers(
            @ConvertWith(StringToLottoNumberSetConverter.class) final Set<LottoNumber> numbers,
            @ConvertWith(StringToLottoNumberConverter.class) final LottoNumber bonus
    ) {
        Lotto lotto = new Lotto(numbers);
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        assertThat(winningLotto.getLotto()).isEqualTo(lotto);
        assertThat(winningLotto.getBonus()).isEqualTo(bonus);

        assertThat(winningLotto).hasFieldOrPropertyWithValue("lotto", lotto);
        assertThat(winningLotto).hasFieldOrPropertyWithValue("bonus", bonus);

        assertDoesNotThrow(() -> new WinningLotto(lotto, bonus));
    }

    @DisplayName("당첨 로또는 6개의 당첨 번호와 1개의 보너스 번호는 중복되면 예외가 발생한다")
    @ParameterizedTest
    @CsvSource({
            "1 2 3 4 5 6, 6"
    })
    void notDuplicateNumbers(
            @ConvertWith(StringToLottoNumberSetConverter.class) final Set<LottoNumber> numbers,
            @ConvertWith(StringToLottoNumberConverter.class) final LottoNumber bonus
    ) {
        assertThatIllegalArgumentException().isThrownBy(() -> new WinningLotto(new Lotto(numbers), bonus));
    }

    @DisplayName("당첨 로또를 기준으로 구매 로또 등수를 확인할 수 있다")
    @ParameterizedTest(name = "{displayName} - {3} 등")
    @MethodSource("generateRank")
    void winningMatch(
            Lotto winningLottoNumber,
            LottoNumber bonus,
            Lotto lotto,
            LottoRank expectedLottoRank
    ) {
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonus);
        LottoRank lottoRank = winningLotto.match(lotto);
        assertThat(lottoRank).isEqualTo(expectedLottoRank);
    }

    private static Stream<Arguments> generateRank() {
        return Stream.of(
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7), new Lotto(1, 2, 3, 4, 5, 6), LottoRank.FIRST),
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(8), new Lotto(1, 2, 3, 4, 5, 8), LottoRank.SECOND),
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7), new Lotto(1, 2, 3, 4, 5, 8), LottoRank.THIRD),
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7), new Lotto(1, 2, 3, 4, 8, 9), LottoRank.FOURTH),
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7), new Lotto(1, 2, 3, 8, 9, 10), LottoRank.FIFTH),
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7), new Lotto(1, 2, 8, 9, 10, 11), LottoRank.NONE)
        );
    }
}