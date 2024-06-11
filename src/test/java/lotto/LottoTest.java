package lotto;

import common.converter.StringToLottoNumberConverter;
import common.converter.StringToLottoNumberSetConverter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoTest {

    @DisplayName("로또는 구입 금액이 있다")
    @Test
    void lottoPrice() {
        assertThat(Lotto.PRICE).isEqualTo(1000);
    }

    @DisplayName("구매 로또는 6개의 당첨 번호로 구성되어 있다")
    @ParameterizedTest
    @CsvSource({
            "1 2 3 4 5 6",
            "2 3 4 5 6 7"
    })
    void lottoNumbers(@ConvertWith(StringToLottoNumberSetConverter.class) final Set<LottoNumber> numbers) {
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).isEqualTo(numbers);
    }

    @DisplayName("구매 로또의 당첨 번호가 6개가 아니면 예외를 발생한다")
    @ParameterizedTest
    @CsvSource({
            "1 2 3 4 5",
            "2 3 4 5 6"
    })
    void notSixNumber(@ConvertWith(StringToLottoNumberSetConverter.class) final Set<LottoNumber> numbers) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(numbers));
    }

    private static Stream<Arguments> provideLottoTestData() {
        return Stream.of(
                Arguments.of("1 2 3 4 5 6", "1 2 3 4 5 6", "7", LottoRank.FIRST),
                Arguments.of("1 2 3 4 5 6", "1 2 3 4 5 12", "6", LottoRank.SECOND),
                Arguments.of("1 2 3 4 5 6", "1 2 3 4 5 12", "13", LottoRank.THIRD),
                Arguments.of("1 2 3 4 5 6", "1 2 3 4 11 12", "13", LottoRank.FOURTH),
                Arguments.of("1 2 3 4 5 6", "1 2 3 10 11 12", "13", LottoRank.FIFTH),
                Arguments.of("1 2 3 4 5 6", "1 2 9 10 11 12", "13", LottoRank.NONE)
        );
    }
}