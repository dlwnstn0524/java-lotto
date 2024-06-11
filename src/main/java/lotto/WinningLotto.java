package lotto;

public class WinningLotto {

    private final LottoNumber bonus;
    private final Lotto lotto;


    public WinningLotto(Lotto lotto, LottoNumber bonus) {
        if (lotto.contains(bonus))
            throw new IllegalArgumentException("보너스 번호는 당첨 번호들과 중복될 수 없습니다.");
        this.lotto = lotto;
        this.bonus = bonus;
    }


    public LottoRank match(Lotto lotto) {
        int unionCnt = lotto.matchCount(this.lotto);

        if (unionCnt == 3) return LottoRank.FIFTH;
        if (unionCnt == 4) return LottoRank.FOURTH;
        if (unionCnt == 5) return matchFiveNumbers(lotto);
        if (unionCnt == 6) return LottoRank.FIRST;

        return LottoRank.NONE;
    }

    private LottoRank matchFiveNumbers(Lotto lotto) {
        return lotto.contains(bonus)?LottoRank.SECOND:LottoRank.THIRD;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}
