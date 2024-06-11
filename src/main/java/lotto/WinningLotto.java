package lotto;

public class WinningLotto {
    private final LottoNumber bonus;
    private final Lotto lotto;


    public WinningLotto(Lotto lotto, LottoNumber bonus) {
        validate(lotto, bonus);
        this.lotto = lotto;
        this.bonus = bonus;
    }

    private void validate(Lotto lotto, LottoNumber bonus) {
        if (lotto.contains(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호들과 중복될 수 없습니다.");
        }
    }

    public LottoRank match(Lotto lotto) {
        int unionCnt = lotto.matchCount(this.lotto);
        boolean bonusMatched = lotto.contains(bonus);

        return LottoRank.from(unionCnt, bonusMatched);
    }

    Lotto getLotto() {
        return lotto;
    }

    LottoNumber getBonus() {
        return bonus;
    }
}
