package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int PRICE = 1000;
    public static final int NUMBERS_LENGTH = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(Set<LottoNumber> numbers) {
        validation(numbers);
        this.numbers = new HashSet<>(numbers);
    }

    public Lotto(int... nums) {
        this(Arrays.stream(nums).mapToObj(LottoNumber::of).collect(Collectors.toSet()));
    }

    private void validation(Set<LottoNumber> numbers) {
        if (numbers == null || numbers.size() != NUMBERS_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    public Set<LottoNumber> getNumbers() {
        return new HashSet<>(numbers);
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public int matchCount(Lotto lotto) {
        Set<LottoNumber> temp = new HashSet<>(numbers);

        temp.retainAll(lotto.numbers);

        return temp.size();
    }

}
