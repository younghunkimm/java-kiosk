# Java CLI Kiosk

사용자의 입력을 받아 메뉴들을 선택하고, 장바구니에 담아 구매하는 기능의 키오스크 개발 프로젝트입니다.

## ✨ 버전

`JDK` Amazon Corretto 17.0.15

## 📅 개발 기간

2025.07.16 ~ 

## 📂 프로젝트 구조

<details>
  <summary>프로젝트 구조</summary>
  
  ```ruby
  lv1/
  └── Main.java
  
  lv2/
  ├── Main.java
  └── MenuItem.java
  
  lv3/
  ├── Kiosk.java
  ├── Main.java
  └── Menu.java
  
  lv4/
  ├── Kiosk.java
  ├── Main.java
  ├── Menu.java
  └── MenuItem.java
  
  lv6/
  ├── Cart.java
  ├── Kiosk.java
  ├── Main.java
  ├── Menu.java
  └── MenuItem.java
  
  lv7/
  ├── Cart.java
  ├── Kiosk.java
  ├── Main.java
  ├── Menu.java
  ├── MenuItem.java
  └── UserType.java
  ```

</details>



## 💡 클래스 다이어그램

<details>
  <summary>클래스 다이어그램</summary>

  ```mermaid
classDiagram
direction BT
class Cart {
  + remove(MenuItem) void
  + clear() void
  + put(MenuItem) void
  - Cart() 
   Cart instance
   BigDecimal totalPrice
   Map~MenuItem, Integer~ cartList
  - Map~MenuItem, Integer~ cartList
}
class Kiosk {
  - printMainMenuList() void
  + start() void
  - printMenuItemList(Menu) void
  + Kiosk(List~Menu~) 
}
class Main {
  + main(String[]) void
  + Main() 
}
class Menu {
  + add(MenuItem) void
  + printMenuItemAll() void
  + Menu(String) 
   String category
   List~MenuItem~ menuItems
  - String category
  - List~MenuItem~ menuItems
}
class MenuItem {
  + toString() String
  + MenuItem(String, String, String) 
   String name
   String description
   BigDecimal price
  - String description
  - String name
  - BigDecimal price
}
class UserType {
<<enumeration>>
  + fromOrdinal(int) UserType
  + values() UserType[]
  + getDiscountPrice(BigDecimal) BigDecimal
  + valueOf(String) UserType
  - UserType(String, String) 
   String name
   BigDecimal discount
  - BigDecimal discount
  - String name
}

Cart "1" *--> "cartList *" MenuItem 
Kiosk "1" *--> "cart 1" Cart 
Kiosk "1" *--> "menus *" Menu 
Kiosk  ..>  MenuItem : «create»
Main  ..>  Kiosk : «create»
Main  ..>  Menu : «create»
Main  ..>  MenuItem : «create»
Menu "1" *--> "menuItems *" MenuItem 
```
  
</details>


## ✅ LV1. 기본적인 키오스크 프로그래밍
 
- **햄버거 메뉴 출력 및 선택하기**
  - `Scanner`를 사용하여 여러 햄버거 메뉴를 출력합니다.
  - 제시된 메뉴 중 입력받은 숫자에 따라 다른 로직을 실행하는 코드를 작성합니다.
  - 반복문을 이용해서 특정 번호가 입력되면 프로그램을 종료합니다.

- LV1 구현 시 터미널에 이렇게 보여집니다.

  ```bash
  [ SHAKESHACK MENU ]
  1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
  2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
  4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거
  0. 종료      | 종료
  0 <- // 0을 입력
  
  프로그램을 종료합니다.
  ```

## ✅ LV2. 객체 지향 설계를 적용해 햄버거 메뉴를 클래스로 관리

- **`MenuItem` 클래스 생성하기**
  - 설명 : 개별 음식 항목을 관리하는 클래스입니다. 현재는 햄버거만 관리합니다.
  - 클래스는 `이름`, `가격`, `설명` 필드를 갖습니다.

- `main` 함수에서 `MenuItem` 클래스를 활용하여 햄버거 메뉴를 출력합니다.
  - `MenuItem` 객체 생성을 통해 `이름`, `가격`, `설명`을 세팅합니다.
  - `List`를 선언하여 여러 `MenuItem`을 추가합니다.
  - 반복문을 활용해 `menuItems`를 탐색하면서 하나씩 접근합니다.
 
## ✅ LV3. 객체 지향 설계를 적용해 순서 제어를 클래스로 관리하기

- **요구사항이 가지는 의도**
  - 객체 지향 개념을 학습하고, 데이터를 구조적으로 관리하며 프로그램을 설계하는 방법을 익힙니다.
  - `main` 함수에서 관리하던 전체 순서 제어를 `Kiosk` 클래스를 통해 관리합니다.

- **`Kiosk` 클래스 생성하기**
  - **설명**: 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스입니다.
  - `MenuItem`을 관리하는 리스트가 필드로 존재합니다.
  - `main` 함수에서 관리하던 입력과 반복문 로직은 이제 `start` 함수를 만들어 관리합니다.
  - `List<MenuItem> menuItems` 는 `Kiosk` 클래스 생성자를 통해 값을 할당합니다.
    - `Kiosk` 객체를 생성하고 사용하는 `main` 함수에서 객체를 생성할 때 값을 넘겨줍니다.

## ✅ LV4. 객체 지향 설계를 적용해 음식 메뉴와 주문 내역을 클래스 기반으로 관리하기

- **`Menu` 클래스 생성하기**
  - 설명 : MenuItem 클래스를 관리하는 클래스입니다.   
          예를 들어, 버거 메뉴, 음료 메뉴 등 각 카테고리 내에 여러 `MenuItem`을 포함합니다.

  - `List<MenuItem>` 은 `Kiosk` 클래스가 관리하기에 적절하지 않으므로 Menu 클래스가 관리하도록 변경합니다.

  - 여러 버거들을 포함하는 상위 개념 ‘버거’ 같은 `카테고리 이름` 필드를 갖습니다.

  - 메뉴 카테고리 이름을 반환하는 메서드가 구현되어야 합니다.

- Lv4를 구현하면 터미널에 이렇게 보여집니다.

  ```bash
  [ MAIN MENU ]
  1. Burgers
  2. Drinks
  3. Desserts
  0. 종료      | 종료
  1 <- // 1을 입력
  
  [ BURGERS MENU ]
  1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
  2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
  4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거
  0. 뒤로가기
  2 <- // 2를 입력
  선택한 메뉴: SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  
  [ MAIN MENU ]
  1. Burgers
  2. Drinks
  3. Desserts
  0. 종료      | 종료
  0 <- // 0을 입력
  프로그램을 종료합니다.
  ```

## ✅ LV5. 캡슐화 적용하기

- `MenuItem`, `Menu` 그리고 `Kiosk` 클래스의 필드에 직접 접근하지 못하도록 설정합니다.
- Getter와 Setter 메서드를 사용해 데이터를 관리합니다.

## 🔥 도전기능 LV1. 장바구니 및 구매하기 기능 추가

- 목표
  - 클래스 간 역할 분리를 이해하고, 적절히 협력하는 객체를 설계
  - 프로그램 상태 변경 및 데이터 저장을 연습
  - 사용자 입력에 따른 예외 처리와 조건 분기를 연습

- **장바구니 생성 및 관리 기능**
  - 사용자가 선택한 메뉴를 장바구니에 추가할 수 있는 기능을 제공합니다.
  - 장바구니는 메뉴명, 수량, 가격 정보를 저장하며, 항목을 동적으로 추가 및 조회할 수 있어야 합니다.
  - 사용자가 잘못된 선택을 했을 경우 예외를 처리합니다(예: 유효하지 않은 메뉴 번호 입력)

- **장바구니 출력 및 금액 계산**
  - 사용자가 결제를 시도하기 전에, 장바구니에 담긴 모든 메뉴와 총 금액을 출력합니다.
  - 출력 예시
    - 각 메뉴의 이름, 가격, 수량
    - 총 금액 합계

- **장바구니 담기 기능**
  - 메뉴를 클릭하면 장바구니에 추가할 지 물어보고, 입력값에 따라 “추가”, “취소” 처리합니다.
  - 장바구니에 담은 목록을 출력합니다.

- **주문 기능**
  - 장바구니에 담긴 모든 항목을 출력합니다.
  - 합산하여 총 금액을 계산하고, “주문하기”를 누르면 장바구니를 초기화합니다.

  ```bash
  [ MAIN MENU ]
  1. Burgers
  2. Drinks
  3. Desserts
  0. 종료      | 종료
  1 <- // 1을 입력
  
  [ BURGERS MENU ]
  1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
  2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
  4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거
  0. 뒤로가기
  2 <- // 2를 입력
  선택한 메뉴: SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  
  // 2번을 누르면 나오는 메뉴입니다.
  "SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"
  위 메뉴를 장바구니에 추가하시겠습니까?
  1. 확인        2. 취소
  1 <-
  
  // 1번을 누르면 나오는 메뉴입니다.
  SmokeShack 이 장바구니에 추가되었습니다.
  
  // 장바구니에 물건이 들어 있으면 아래와 같이 [ ORDER MENU ] 가 추가로 출력됩니다.
  // 만약에 장바구니에 물건이 들어 있지 않다면 [ ORDER MENU ] 가 출력되지 않습니다. 
  // 미출력일 때 4,5 번을 누르면 예외를 던저줘야 합니다.
  아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.
  
  [ MAIN MENU ]
  1. Burgers
  2. Drinks
  3. Desserts
  0. 종료      | 종료
  
  [ ORDER MENU ]
  4. Orders       | 장바구니를 확인 후 주문합니다.
  5. Cancel       | 진행중인 주문을 취소합니다.
  4 <- // 4를 입력
  
  // 4번을 누르면 나오는 메뉴입니다.
  아래와 같이 주문 하시겠습니까?
  
  [ Orders ]
  SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  
  [ Total ]
  W 8.9
  
  1. 주문      2. 메뉴판
  1 <-
  
  // 1번을 누르면 나오는 메뉴입니다.
  주문이 완료되었습니다. 금액은 W 8.9 입니다.
  ```

## 🔥 도전기능 LV2. Enum, Lambda, Stream 활용

- **요구사항이 가지는 의도**
  - **의도** : 고급 자바 기능을 활용해 프로그램의 효율성과 코드의 가독성을 개선하는 것을 목표로 합니다.
  - **목적**
    - Enum을 통해 상수를 안전하게 관리하고, 프로그램 구조를 간결하게
    - 제네릭을 활용하여 데이터 유연성을 높이고, 재사용 가능한 코드를 설계
    - 스트림 API를 사용하여 데이터를 필터링하고, 간결한 코드로 동작을 구현

- **Enum을 활용한 사용자 유형별 할인율 관리하기**
  - 사용자 유형의 Enum 정의 및 각 사용자 유형에 따른 할인율 적용
    - 예시 : 군인, 학생, 일반인
  - 주문 시, 사용자 유형에 맞는 할인율 적용해 총 금액 계산

- **람다 & 스트림을 활용한 장바구니 조회 기능**
  - 기존에 생성한 Menu의 MenuItem을 조회 할 때 스트림을 사용하여 출력하도록 수정
  - 기존 장바구니에서 특정 메뉴 빼기 기능을 통한 스트림 활용
    - 예시 : 장바구니에 SmokeShack 가 들어 있다면, stream.filter를 활용하여 특정 메뉴 이름을 가진 메뉴 장바구니에서 제거

- 구조 예시

  ```bash
  [ MAIN MENU ]
  1. Burgers
  2. Drinks
  3. Desserts
  0. 종료      | 종료
  1 <- // 1을 입력
  
  [ BURGERS MENU ]
  1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
  2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
  4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거
  0. 뒤로가기
  2 <- // 2를 입력
  선택한 메뉴: SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  
  // 2번을 누르면 나오는 메뉴입니다.
  "SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"
  위 메뉴를 장바구니에 추가하시겠습니까?
  1. 확인        2. 취소
  1 <-
  
  // 1번을 누르면 나오는 메뉴입니다.
  SmokeShack 이 장바구니에 추가되었습니다.
  
  // 장바구니에 물건이 들어 있으면 아래와 같이 [ ORDER MENU ] 가 추가로 출력됩니다.
  // 만약에 장바구니에 물건이 들어 있지 않다면 [ ORDER MENU ] 가 출력되지 않습니다. 
  // 미출력일 때 4,5 번을 누르면 예외를 던저줘야 합니다.
  아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.
  
  [ MAIN MENU ]
  1. Burgers
  2. Drinks
  3. Desserts
  0. 종료      | 종료
  
  [ ORDER MENU ]
  4. Orders       | 장바구니를 확인 후 주문합니다.
  5. Cancel       | 진행중인 주문을 취소합니다.
  4 <- // 4를 입력
  
  // 4번을 누르면 나오는 메뉴입니다.
  아래와 같이 주문 하시겠습니까?
  
  [ Orders ]
  SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
  
  [ Total ]
  W 8.9
  
  1. 주문      2. 메뉴판
  1 <-
  
  // 1번을 누르면 할인 정보를 제공해줍니다.
  할인 정보를 입력해주세요.
  1. 국가유공자 : 10% 
  2. 군인     :  5%
  3. 학생     :  3%
  4. 일반     :  0%
  4 <-
  
  // 4번을 누르면 나오는 메뉴입니다.
  주문이 완료되었습니다. 금액은 W 8.9 입니다.
  ```
