import lotto.InputView;
import lotto.LottoController;
import lotto.OutputView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class LottoApplication {
    public static void main(String[] args) throws IOException {
        LottoController lottoController = new LottoController();
        lottoController.run();
    }
}