# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 기능 목록
- [x] 구매 로또는 구입 금액이 있다
- [x] 당첨 로또는 6개의 당첨 번호와 1개의 보너스 번호가 있다
- [x] 구매 로또는 6개의 당첨 번호로 구성되어 있다
- [x] 구매 로또의 당첨 번호가 6개가 아니면 예외를 발생한다
- [x] 로또의 숫자는 중복되지 않는다
- [x] 입력한 금액으로 구매할 수 있는 최대 개수 만큼 로또를 구매한다
- [x] 로또를 구매하면 6개 당첨 번호가 자동으로 생성된다.
- [x] 당첨 로또의 번호는 입력 받는다
- 다음과 같은 조건일 때 구매 로또는 당첨된다
  - [x] 2개 미만 - 꽝!
  - [x] 3개 일치 - 5등, 5,000원
  - [x] 4개 일치 - 4등, 50,000원
  - [x] 5개 일치 - 3등, 1,500,000원
  - [x] 5개 일치 + 보너스 번호 일치 - 2등, 30,000,000 원
  - [x] 6개 일치 - 1등, 2,000,000,000 원
- [x] (전체 수익금액) / (구입금액)으로 수익률과 등수별 맞춘 횟수 출력한다

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)