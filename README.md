# be-chess

소프티어 부트캠프 2기 체스 프로젝트

# 기능목록

## 도메인 관련 기능목록

### 체스 판

- 체스 판을 생성한다.
    - 체스 판 위에 말이 없는 상태에서 시작한다.


- 체스 판에 Pawn을 추가한다.
    - 체스 판에 Pawn 이외의 객체가 추가될 수 없다.


- 체스 판에 추가된 Pawn의 개수를 반환한다.


- 체스 판에 추가된 Pawn을 순서에 따라 반환한다.


- 체스판을 출력한다.
    - 체스판은 8 * 8로 구성되어 있다.
    - 검은색 Pawn은 대문자 P, 흰색 Pawn은 소문자 p로 표시한다.
  ```
  ........
  PPPPPPPP
  ........
  ........
  ........
  ........
  pppppppp
  ........
  ```


- 기물과 색에 해당하는 기물의 개수를 반환한다.
    - 기물의 색과, 종류를 인자로 받아 기물의 개수를 반환하는 로직을 구현한다.


- 주어진 위치의 기물을 조회한다.
  ```
  RNBQKBNR  8 (rank 8)
  PPPPPPPP  7
  ........  6
  ........  5
  ........  4
  ........  3
  pppppppp  2
  rnbqkbnr  1 (rank 1)
  abcdefgh
  ```


- 빈 체스판을 생성한다.


- 임의의 기물을 체스판 위에 추가한다.


- 체스 프로그램에서 현재까지 남아 있는 기물에 따라 점수를 계산할 수 있어야 한다.
    - 각 기물의 점수는 queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
    - pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
    - king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
    - 한 번에 한 쪽의 점수만을 계산해야 한다.

### 기물

- 기물을 생성한다.
    - 기물의 종류는 pawn, knight, rook, bishop, queen, king으로 구분할 수 있다.
    - 값(value) 객체여야 한다.
    - 인스턴스를 생성한 이후에는 인스턴스의 상태 값을 변경할 수 없어야 한다.
    - 기물을 생성할 때 어느 팀의 말인지 구분하기 위해 흰색(white)과 검은색(black)으로 구분해 생성할 수 있도록 한다.
    - 검은색 말이면 true를 반환한다.
    - 흰색 말이면 true를 반환한다.


- 기물의 색을 나타내는 Color enum을 적용한다.
    - 체스판은 흰색, 검은 색과 아무 기물도 없는 곳까지 3가지 색이 존재할 수 있다.
    - Piece 클래스에서만 존재를 알 수 있도록 Piece 클래스 내에 구현한다.


- 기물의 종류를 나타내는 Type enum을 적용한다.
    - 기물의 종류에 따른 식별 문자를 관리한다.
    - 식별 문자는 소문자만 관리하고 대문자의 경우 소문자를 대문자로 변환하여 활용한다


- 색과 이름을 받아 기물을 생성하는 팩토리 메소드를 구현한다.

## 입출력 관련 기능목록

- 체스 게임을 시작한다.
    - start 라는 명령을 입력하면 체스 게임을 시작하고, 체스판의 현재 상태를 콘솔 화면에 출력한다.


- 체스 게임을 종료한다.
    - end 라는 명령을 입력하면 체스 게임을 종료한다.


- appendNewLine("문자열") 클래스 메소드를 추가해 인자로 전달한 메소드에 new line 문자를 추가한다
