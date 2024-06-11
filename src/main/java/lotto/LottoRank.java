package lotto;

public enum LottoRank {
    FIRST(2000000000L),
    SECOND(30000000L),
    THIRD(1500000),
    FOURTH(50000),
    FIFTH(5000),
    NONE(0);

    private final long price;

    LottoRank(long price) {
        this.price = price;
    }

    public static LottoRank from(int unionCount, boolean bonusMatched) {
        if (unionCount == 3) return LottoRank.FIFTH;
        if (unionCount == 4) return LottoRank.FOURTH;
        if (unionCount == 5 && !bonusMatched) return LottoRank.THIRD;
        if (unionCount == 5 && bonusMatched) return LottoRank.SECOND;
        if (unionCount == 6) return LottoRank.FIRST;

        return LottoRank.NONE;
    }

    public long getPrice() {
        return price;
    }
}
