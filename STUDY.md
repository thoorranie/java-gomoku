개념
**---record---**
클래스처럼 생겼지만, 오직 '데이터 보관'만을 위해 존재하는 아주 가벼운 데이터 상자.
`public record CoordinateDTO(int row, int column) {}`
-> "2개의 정수형 데이터 row와 column을 보관하는, 한 번 만들어지면 수정 불가능한 CoordinateDTO라는 이름의 데이터 상자를 만들어라."
일반 class/interface와의 차이: 일반 클래스는 데이터를 넣고 빼고 계산하는 기능(메서드)을 길게 다 적어줘야 하지만, 레코드는 이름표 하나만 딱 붙여주면 자바가 알아서 데이터 보관용 코드를 다 짜줍니다.

---

**---enum---**
미리 정해둔 몇 가지 서로 관련된 고정된 값(상수)들의 집합을 정의하는 특별한 클래스.
`public enum Piece { BLACK, WHITE, EMPTY }`
-> "오목돌의 종류는 무조건 BLACK, WHITE, EMPTY 3가지 중 하나만 쓸 수 있도록 enum이라는 이름의 규칙 모음집을 만들어라."
interface와의 차이: interface는 "이런 행동(메서드)을 꼭 해라!"라는 '행동 규칙'이고, enum은 "이런 종류(데이터)만 써라!"라는 '선택지 목록'입니다.

---

**---DTO (Data Transfer Object)---**
구역과 구역 사이(예: 화면 ↔ 컨트롤러)를 이동할 때 데이터를 안전하게 담아서 나르는 '택배 상자'.
-> "사용자가 화면에 입력한 복잡한 위치 값을, 게임의 뇌(Model)가 계산하기 편하도록 숫자 2개(row, column)로 예쁘게 포장해서 전달해라."
일반 변수와의 차이: 숫자 2개를 따로따로 던지면 중간에 잃어버리거나 순서가 바뀔 수 있지만, DTO라는 택배 상자에 담아 이름표를 붙여서 보내면 훨씬 안전하고 정확하게 배달됩니다.

---

**---MVC 패턴 (Model - View - Controller)---**
역할을 3가지(뇌, 눈/입, 지휘자)로 나눠서 코드를 깔끔하게 정리하는 폴더 구조 규칙.
-> "눈과 입(View)이 입력을 받아오면, 지휘자(Controller)가 뇌(Model)한테 계산을 시키고, 결과를 다시 눈과 입(View)에게 전달해서 화면에 찍어라."
하나의 파일에 다 쓰는 방식과의 차이: 오목 게임을 '콘솔'에서 '웹 애플리케이션'으로 업그레이드할 때, 뇌(Model) 코드는 하나도 안 고치고 눈/입(View)만 웹용으로 쏙 갈아끼우면 돼서 유지보수가 엄청나게 쉬워집니다.

---

**---일급 컬렉션 (First-Class Collection) & Map---**
여러 데이터를 담는 사물함(Map)과, 그 사물함 하나만 전담해서 지키는 깐깐한 경비원(클래스)을 세우는 기법.
`private final Map<Coordinate, Piece> grid = new HashMap<>();`
-> "좌표를 열쇠로 주면 오목돌을 꺼내주는 사물함(Map)을 만들고, 오직 GomokuBoard(경비원)를 통해서만 돌을 넣고 뺄 수 있게 철저히 통제해라."
일반 배열(Piece[][])과의 차이: 배열은 누구나 마음대로 접근해서 값을 조작(`board[0][0]=WHITE`)할 수 있어 버그에 취약하지만, 일급 컬렉션은 경비원이 정한 룰(예: "이미 돌이 있는 곳엔 못 둬!")을 통과해야만 접근할 수 있어 매우 안전합니다.

---

**---@FunctionalInterface---**
인터페이스 위에 붙여서 "이 안에는 기능(메서드)이 딱 1개만 있어야 해!"라고 자바에게 감시를 부탁하는 경고 딱지.
`@FunctionalInterface public interface Controller { void run(); }`
-> "Controller 인터페이스 안에는 무조건 run()이라는 행동 딱 하나만 정의할 수 있도록 자바가 철저하게 검사해라."
일반 interface와의 차이: 일반 인터페이스는 100개든 기능을 정의할 수 있지만, 얘는 나중에 최신 기술(람다식)을 쓸 때 컴퓨터가 헷갈리지 않도록 일부러 '기능 1개'라는 제약을 거는 것입니다.

---

**---Stream API & 람다식 (->)---**
데이터들을 컨베이어 벨트(Stream) 위에 올려놓고, 기계(람다식 ->)를 통과시키며 한 줄로 깔끔하게 처리하는 최신 문법.
`directionStream.anyMatch(direction -> hasFiveStones(...))`
-> "가로, 세로, 대각선 방향들을 컨베이어 벨트에 올려놓고, 단 하나라도(anyMatch) 5목이 완성되는 조건(->)에 맞으면 즉시 참(true)을 줘라."
for문 / if문과의 차이: for/if문을 계속 겹쳐 쓰면 코드가 계속 안쪽으로 파고들어서(들여쓰기 3~4단계) 읽기 힘들어지지만, 스트림을 쓰면 파이프라인처럼 한 줄로 쫙 펴서 평평하게 코드를 짤 수 있습니다.

---

**---final (불변성)---**
변수 앞에 붙여서 "한 번 값이 정해지면 프로그램 끝날 때까지 절대 다른 걸로 바꿔치기 금지!"라고 못 박는 튼튼한 자물쇠.
`private final GomokuBoard board = new GomokuBoard();`
-> "처음 만들어진 오목판(GomokuBoard) 객체를 담은 이 변수는, 중간에 실수로라도 다른 오목판으로 교체되지 않도록 꽉 잠가둬라."
일반 변수와의 차이: 일반 변수는 누군가 실수로 `board = null;` 처럼 바꿔버리면 게임이 터지지만, final이 붙으면 코드 작성 단계에서 아예 빨간줄을 그어버려서 실수를 원천 차단합니다.

---

**---try - catch & throw (예외 처리)---**
에러가 날 것 같은 위험한 코드를 실행할 때, 에러를 수습(catch)하거나 직접 빨간불을 켜서 강제로 터뜨리는(throw) 비상벨 시스템.
`try { ... } catch (NumberFormatException e) { throw new IllegalArgumentException(); }`
-> "사용자 입력값을 숫자로 바꿔보고(try), 만약 숫자가 아닌 글자라서 터지면(catch), '잘못된 입력!'이라는 비상벨을 울리고(throw) 즉시 게임을 종료해라."
단순 if문 검사와의 차이: if문은 "값이 이상하면 이렇게 해~"라는 부드러운 우회로라면, try-catch-throw는 "시스템 비상! 즉시 하던 일 멈추고 예외 절차 돌입!" 같은 강력하고 구조적인 에러 대처법입니다.

---

**---else 금지 (Early Return / 빠른 종료)---**
`if-else`문에서 `else`를 쓰지 않고 코드를 짜는 객체지향 생활체조 원칙.
`if (isWin) { handleWin(); return false; } return handleDraw();`
-> "만약(if) 이겼으면 승리 처리를 하고 여기서 바로 일 끝내(return)! 그 밑으로는 쳐다보지도 마! (그리고 굳이 else라고 안 써도, 안 이겼으면 자연스럽게 다음 줄인 무승부 검사로 넘어가라.)"
일반 if-else와의 차이: `else`를 계속 쓰면 코드가 "이거면 저거고 아니면 저거고..." 하면서 들여쓰기가 깊어지고 복잡해집니다. 반면 빠꾸(?)를 먹일 조건들을 위에서 다 쳐내고(`return`), 진짜 하고 싶은 일은 맨 밑에 평평하게 두면 코드가 훨씬 읽기 편해집니다. (클럽 기도 보이가 "미성년자 나가! 취객 나가! ... 남은 사람 입장해~" 하는 것과 같습니다.)

---

**---상수 (static final) & 매직 넘버(Magic Number) 제거---**
숫자 15나 2 같은 것을 코드에 쌩으로 쓰지 않고, 이름을 붙여서 쓰는 원칙.
`static final int BOARD_SIZE = 15;`
-> "코드 중간에 뜬금없이 '15'라고 쓰면 이 숫자가 나이인지 갯수인지 헷갈리니까, 무조건 'BOARD_SIZE(오목판 크기)'라는 절대 안 변하는(static final) 이름표를 붙여서 써라."
쌩 숫자와의 차이: 나중에 19x19 바둑판으로 게임을 업그레이드하고 싶을 때, 코드 전체를 뒤져서 15를 19로 바꿀 필요 없이 저 `BOARD_SIZE` 숫자 딱 하나만 바꾸면 시스템 전체가 알아서 변경됩니다.

---

**---메서드 분리 (Method Extraction)---**
함수(메서드) 하나가 10줄을 넘지 않도록, 긴 코드를 여러 개의 짧은 코드로 쪼개는 기술.
`private boolean executeTurn() { printTurnStatus(); ... processInput(input); }`
-> "사장(Controller)이 혼자서 화면도 그리고, 입력도 받고, 계산도 다 하려니까 10줄이 넘어가네? 화면 그리는 건 printTurnStatus() 직원한테 시키고, 입력 처리는 processInput() 직원한테 일임해라!"
통짜 코드와의 차이: 코드를 책의 '목차'처럼 읽을 수 있게 됩니다. "아, 턴을 실행(executeTurn)할 때는 -> 상태를 출력하고 -> 입력을 받아서 처리하는구나!" 하고 세부 구현을 안 봐도 흐름이 한눈에 들어옵니다.

---

**---Scanner & System.in (콘솔 입력)---**
사용자가 키보드로 치는 글자를 프로그램 안으로 빨아들이는 진공청소기.
`private static final Scanner scanner = new Scanner(System.in);`
-> "컴퓨터의 기본 키보드 연결선(System.in)에다가, 입력된 글자를 자바가 이해할 수 있게 번역해 주는 번역기(Scanner)를 달아둬라."
작동 방식: 사용자가 "A1"을 치고 엔터를 누르면, 스캐너가 `scanner.nextLine()`을 통해 그 글자를 쏙 빨아들여서 자바 코드 안의 문자열(`String`) 변수로 만들어줍니다.

---

**---printf (포맷 출력)---**
그냥 글자를 찍는 `print`와 달리, 모양과 줄을 예쁘게 맞춰서 출력하는 마법의 틀.
`System.out.printf("%2d ", BOARD_SIZE - row);`
-> "숫자를 출력하긴 할 건데, 줄을 예쁘게 맞춰야 하니까 무조건 2칸(%2d)을 차지하도록 공간을 확보한 뒤에 숫자를 찍어라!"
일반 println과의 차이: 오목판을 그릴 때 1줄부터 15줄까지 나오는데, 1은 한 칸이고 15는 두 칸이라서 그냥 출력하면 오목판이 삐뚤빼뚤해집니다. `%2d`를 쓰면 1 앞에도 빈칸을 살짝 넣어줘서 오목판이 칼각으로 예쁘게 정렬됩니다.

------------------------------------------------

---Application.java---
프로그램의 전원을 켜는 메인 스위치.
public static void main(String[] args)
-> "지휘자인 BoardGameController를 만들고 게임의 전원(run)을 켜서 시작시켜라."
일반 클래스와의 차이: 이 프로젝트에서 유일하게 자바 프로그램의 공식 진입점(main 메서드)을 가지고 있는 실행 전용 클래스입니다.

---Controller (Interface)---
모든 지휘자가 무조건 가져야 할 행동 규칙(버튼).
public interface Controller { void run(); }
-> "어떤 지휘자가 오든, 무조건 'run(게임 시작)'이라는 버튼 하나는 무조건 만들어둬라."
구현체(class)와의 차이: 실제 어떻게 실행할지는 안 적혀 있고, "이런 기능이 있어야 해"라는 이름표만 존재합니다.

---BoardGameController---
게임의 전체 흐름을 통제하는 총괄 매니저(지휘자).
public class BoardGameController implements Controller
-> "눈/입(View)한테 입력받아오라고 시키고, 뇌(Model)한테 돌 둬보라고 시키고, 결과를 다시 화면에 찍으라고 중간에서 심부름과 지시를 내려라."
View/Model과의 차이: 직접 화면을 그리거나 오목 룰을 계산하지 않습니다. 오직 남들에게 '일해라'라고 순서를 정해서 시키는 통제탑 역할만 합니다.

---Board (Interface)---
오목판이 가져야 할 가장 기본적인 행동 규칙.
public interface Board { Piece getPiece(...); void placePiece(...); }
-> "오목판이라면 무조건 '어떤 돌인지 확인하기'와 '돌 놓기' 기능은 가지고 있어야 해."
GomokuBoard(구현체)와의 차이: 실제 데이터를 담는 사물함은 없고, "판이라면 이 기능은 필수야"라는 껍데기(규칙)만 있습니다.

---Coordinate (Record)---
가로/세로 위치 값을 담는 불변 데이터 상자.
public record Coordinate(int row, int column)
-> "가로, 세로 숫자를 하나로 묶어 다니고, '위로 한 칸 이동해(move)'라고 시키면 새로운 위치가 담긴 새 상자를 만들어줘라."
단순 int 변수 2개와의 차이: 숫자 2개를 따로 던지면 헷갈리지만, 묶어두면 안전하고 이동 계산까지 객체 스스로 할 수 있습니다.

---Direction (Enum)---
돌이 연속으로 있는지 찾을 때 쓸 4가지 탐색 방향.
public enum Direction { HORIZONTAL(0, 1), ... }
-> "오목 승리를 검사할 때 쓸 가로, 세로, 대각선 2개의 '이동 수치'를 미리 정해두고 이것만 써라."
일반 배열/리스트와의 차이: 값이 고정되어 있어 안전하고, 각 방향이 가로/세로로 얼만큼 이동해야 하는지(rowDelta, columnDelta) 데이터까지 품고 있습니다.

---Piece (Enum)---
오목판 위 공간의 상태(돌의 종류).
public enum Piece { BLACK, WHITE, EMPTY }
-> "이 칸에 흑돌이 있는지, 백돌이 있는지, 아니면 비어있는지 딱 3가지 중 하나만 표시해라."
문자열("X", "O")과의 차이: 문자열은 오타가 날 수 있지만, Enum은 오직 정해진 3가지만 넣을 수 있어 시스템이 안전해집니다.

---GomokuBoard---
진짜 사물함(Map)을 가지고 있는 실제 오목판.
public class GomokuBoard implements Board
-> "비어있는지 확인하고(validateEmpty), 안전하게 사물함에 돌을 넣어라(placePiece)."
2차원 배열(Piece[][])과의 차이: 배열은 밖에서 마음대로 조작할 수 있지만, 이 클래스는 이미 돌이 있는 곳에 두려고 하면 에러를 뿜어내는 '방어막'이 쳐져 있습니다.

---GomokuGame---
현재 게임의 핵심 상태를 기억하는 '게임 진행 요원'.
public class GomokuGame
-> "컨트롤러가 좌표를 주면, 오목판에 돌을 놓고, 승리 검사관(WinChecker)을 불러서 이겼는지 확인하고, 차례를 넘겨라."
BoardGameController와의 차이: 컨트롤러는 화면(View)과 소통하지만, GomokuGame은 화면이 뭔지 전혀 모르고 오직 게임의 '데이터 상태(턴, 플레이어, 오목판)'만 순수하게 관리합니다.

---WinChecker---
5목이 완성되었는지 깐깐하게 검사하는 심판.
public class WinChecker
-> "방금 둔 돌에서 시작해서, 지정된 4가지 방향으로 옆으로 계속 이동하며 같은 색 돌이 5개 연속인지 세어라(countStones)."
단순 for문과의 차이: 들여쓰기를 1단계로 줄이기 위해, 자기가 자기 자신을 부르는 '재귀 함수' 마법을 써서 깔끔하게 검사합니다.

---CoordinateDTO (Record)---
View(화면)에서 Controller(지휘자)로 데이터를 넘길 때 쓰는 택배 상자.
public record CoordinateDTO(int row, int column)
-> "사용자가 친 'A1'을 숫자 row:14, col:0 으로 바꿔서 상자에 예쁘게 담아 지휘자에게 전달해라."
일반 Coordinate와의 차이: Coordinate는 게임의 '진짜 뇌'에서 쓰는 데이터고, DTO는 외부(입력)에서 내부(로직)로 들어올 때 쓰는 '배달 전용' 껍데기입니다.

---GomokuUIConstants---
화면에 보여줄 모든 글자와 숫자를 모아둔 상수 창고.
static final String COLUMN_LABELS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
-> "게임 안내 문구나 바둑판 크기 같은 건 쌩으로 코드에 쓰지 말고, 무조건 여기서만 꺼내 써라."
하드코딩(코드에 직접 텍스트 치기)과의 차이: 나중에 문구를 "까만 돌 차례입니다"로 바꾸고 싶을 때, 코드 전체를 뒤질 필요 없이 여기 창고만 쓱 수정하면 전체가 적용됩니다.

---Symbol (Enum)---
게임 속 데이터(Piece)를 화면에 그릴 글자("X", "O")로 바꿔주는 번역기.
public enum Symbol { BLACK("X", Piece.BLACK), ... }
-> "뇌(Model)가 Piece.BLACK이라고 말하면, 눈(View)은 그걸 알아듣고 화면에 'X'라고 찍어라."
Piece에 직접 문자열을 넣는 것과의 차이: 뇌(Model)는 화면에 어떻게 그려질지 몰라야 MVC 원칙에 맞습니다. 화면용 기호는 철저히 View 구역(Symbol)에서만 관리합니다.

---ConsoleInputView / ConsoleOutputView---
사용자의 키보드 입력을 받고, 모니터에 결과를 예쁘게 그려주는 진짜 눈과 입.
public class ConsoleInputView implements InputView
-> "키보드로 친 글자가 올바른지 검사해서 상자(DTO)에 담아주고, 지휘자가 오목판을 주면 콘솔에 예쁘게(printf) 줄 맞춰서 그려라."
Model 클래스들과의 차이: 이 녀석들은 게임 룰(5목인지, 누구 턴인지)을 전혀 모릅니다. 멍청하게 지휘자가 시키는 대로 입력받고 출력만 하는 역할입니다.