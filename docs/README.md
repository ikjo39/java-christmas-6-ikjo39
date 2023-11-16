# 🎄 미션 - 크리스마스 프로모션

## 🗂️ 패키지 구조

<div>
  <table>
    <tr>
        <th>패키지명</th>
        <th>이름</th>
        <th>설명</th>
    </tr>
    <tr>
          <td rowspan="1">⭐ controller</td>
          <td>EventPlannerController</td>
          <td>UI와 모든 모델의 동작을 제어</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="7">⭐ model</td>
        <td>VisitDate</td>
        <td>예정 방문 날짜를 갖는 클래스</td>
    </tr>
    <tr>
        <td>OrderSheets</td>
        <td>주문서목록을 갖는 일급 컬렉션 클래스</td>
    </tr>
    <tr>
        <td>OrderedMenu</td>
        <td>메뉴와 수량을 갖는 클래스</td>
    </tr>
    <tr>
        <td>OrderedMenus</td>
        <td>메뉴와 수량들의 List를 갖는 일급 컬렉션 클래스</td>
    </tr>
    <tr>
        <td>TotalPrice</td>
        <td>할인 전 총 주문 금액을 갖는 클래스</td>
    </tr>
    <tr>
        <td>EventManager</td>
        <td>모든 이벤트들을 갖는 클래스</td>
    </tr>
    <tr>
        <td>DiscountedPriceBadgeManager</td>
        <td>할인된 총 주문금액과 뱃지를 관리하는 클래스 </td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="6">⭐ model/event</td>
        <td>Event</td>
        <td>이벤트들이 상속할 추상화 클래스</td>
    </tr>
    <tr>
        <td>ChristmasEvent</td>
        <td>크리스마스 이벤트 클래스</td>
    </tr>
    <tr>
        <td>GiveawayEvent</td>
        <td>증정 메뉴 이벤트 클래스</td>
    </tr>
    <tr>
        <td>SpecialEvent</td>
        <td>특별 이벤트 클래스</td>
    </tr>
    <tr>
        <td>WeekdayEvent</td>
        <td>주중 이벤트 클래스</td>
    </tr>
    <tr>
        <td>WeekendEvent</td>
        <td>주말 이벤트 클래스</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="3">⭐ dto</td>
        <td>EventBenefit</td>
        <td>이벤트 혜택 결과를 갖는 레코드</td>
    </tr>
    <tr>
        <td>EventBenefits</td>
        <td>EventBenfit List를 갖는 일급 컬력션 레코드</td>
    </tr>
    <tr>
        <td>Giveaway</td>
        <td>증정 메뉴와 이벤트 활성화 조건을 갖는 레코드</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="1">⭐ handler</td>
        <td>ExceptionRetryHandler</td>
        <td>입력 값 예외시 올바른 입력 전까지 재시도 해주는 처리기</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="2">⭐ view</td>
        <td>InputView</td>
        <td>표준 입력을 담당하는 클래스</td>
    </tr>
    <tr>
        <td>OutputView</td>
        <td>표준 출력을 담당하는 클래스</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="9">⭐ constant</td>
        <td>EventBadge</td>
        <td>총 혜택금액에 따라 부여할 이벤트 뱃지의 이름과 최소 금액을 갖는 열거형 클래스</td>
    </tr>
    <tr>
        <td>EventDate</td>
        <td>이벤트 연도와 달을 다루는 열거형 클래스</td>
    </tr>
    <tr>
        <td>EventDateConfig</td>
        <td>각 이벤트별 시작일과 종료일을 갖는 열거형 클래스</td>
    </tr>
    <tr>
        <td>EventDiscountAmount</td>
        <td>각 이벤트별 할인 금액을 갖는 열거형 클래스</td>
    </tr>
    <tr>
        <td>EventNameFormat</td>
        <td>각 이벤트별 출력 문구 형식을 갖는 열거형 클래스</td>
    </tr>
    <tr>
        <td>ExceptionMessage</td>
        <td>예외 메시지 출력 문구를 갖는 열거형 클래스</td>
    </tr>
    <tr>
        <td>Menu</td>
        <td>12월 메뉴이름과 가격을 갖는 열거형 클래스</td>
    </tr>
    <tr>
        <td>MenuCategory</td>
        <td>12월 메뉴 카테고리를 갖는 열거형 클래스</td>
    </tr>
    <tr>
        <td>OutputMessage</td>
        <td>출력 메시지 문구를 갖는 열거형 클래스</td>
    </tr>
  </table>
</div>

---

## 🧭 구현한 기능 목록

- ✅ 플래너 소개글을 화면에 출력한다.

- ✅ 예상 식당 방문 날짜를 입력받아 저장한다.
    - 예상 방문일 요청 문구를 화면에 출력한다.
    - 1이상 31 이하의 숫자로만 입력받는다.
    - 그 외 입력은 "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." 를 보여준다.
    - 입력값 예외가 발생하지 않을 때까지 값을 입력받는다.

- ✅ 주문할 메뉴와 개수를 입력받는다. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
    - 주문할 메뉴와 개수를 요청하는 문구를 출력한다.
    - 메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    - 메뉴판에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    - 메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요. 이외의 입력값은 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    - 중복 메뉴를 입력한 경우(e.g. 시저샐러드-1,시저샐러드-1), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    - 메뉴가 음료로만 구성된 경우, "[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요."
    - 메뉴 개수가 20개를 초과한 경우, "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요."
    - 입력값 예외가 발생하지 않을 때까지 값을 입력받는다.

- ✅ 방문 예정 날짜가 추가된 지정된 문구를 출력한다.

- ✅ 주문메뉴 목록을 출력한다.

- ✅ 할인 전 총 주문 금액을 출력한다.

- ✅ 할인 전 총 주문 금액이 10,000원 이하면 이벤트가 적용되지 않는다.

- ✅ 증정 메뉴를 출력한다. 증정 메뉴가 없다면 "없음"을 보여준다.
    - 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정한다.
    - 증정 이벤트에 해당하지 않는 경우 "없음"으로 출력한다.

- ✅ 혜택 내역을 출력한다.
    - 적용된 이벤트만 출력한다.
    - 적용된 이벤트가 하나도 없다면 "없음"으로 출력한다.
    - 혜택 내역에 여러 개의 이벤트가 적용된 경우, 출력 순서는 자유롭게 출력한다.

- ✅ 총혜택 금액을 출력한다.
    - 총혜택 금액은 할인 금액의 합계 + 증정 메뉴의 가격 이다.

- ✅ 할인 후 에상 결제 금액을 출력한다.
    - `할인 전 총주문 금액 - 할인 금액`이다.

- ✅ 이벤트 배지를 출력한다.
    - 이벤트 배지가 부여되지 않는 경우, "없음"으로 출력한다.

---

## ❓ 예외 케이스 추가 부분

- 방문 예정일을 날짜로 변환 중 DateFormatException 발생 시 아래 문구 출력 후 입력 반복
    - "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
- 메뉴가 음료로만 구성된 경우 아래 문구 출력 후 입력 반복
    - "[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요."
- 메뉴 개수가 20개를 초과한 경우 아래 문구 출력 후 입력 반복
    - "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요."
