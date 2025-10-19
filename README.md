# 문자열 덧셈 계산기

## 프로젝트 개요

입력한 문자열에서 숫자를 추출하여 덧셈을 수행하는 계산기입니다.
기본 구분자(쉼표, 콜론)와 사용자 정의 커스텀 구분자를 지원하며,
잘못된 입력에 대한 예외 처리를 포함합니다.

## 주요 기능

- 쉼표(,) 또는 콜론(:)을 구분자로 하는 숫자 덧셈
- 커스텀 구분자 지정 기능 (`//구분자\n숫자` 형식)
- 음수 및 잘못된 입력에 대한 예외 처리
- 빈 문자열 입력 시 0 반환
- BigInteger를 활용한 큰 숫자 연산 지원

## 실행 예시

```
덧셈할 문자열을 입력해 주세요.
1,2:3
결과 : 6
```

```
덧셈할 문자열을 입력해 주세요.
//;\n1;2;3
결과 : 6
```

## 개발 환경

- JDK 21
- JUnit 5
- AssertJ

## 기능 목록

### 1. Number (숫자 도메인 객체)

- [x] 문자열을 BigInteger로 파싱
- [x] 앞뒤 공백 제거 후 변환
- [x] 매우 큰 숫자 변환 지원
- [x] 생성 시점에 음수 검증 (불변 객체)
- [x] 숫자가 아닌 문자열 입력 시 IllegalArgumentException 발생

### 2. Numbers (일급 컬렉션)

- [x] Number 리스트를 내부 상태로 보유
- [x] 빈 배열 입력 시 0 반환
- [x] sum() 메서드로 숫자들의 합 계산
- [x] BigInteger를 이용한 큰 숫자 합 계산
- [x] 생성 시점에 모든 Number 검증 완료

### 3. StringSplitter (문자열 분리)

- [x] 단일 구분자로 문자열 분리
- [x] 쉼표(,)와 콜론(:)을 기본 구분자로 사용하여 분리
- [x] 정규식을 활용한 복합 구분자 처리

### 4. DelimiterExtractor (구분자 추출)

- [x] 문자열이 "//"로 시작하는지 확인 (커스텀 구분자 여부)
- [x] "//"와 "\n" 사이의 문자를 커스텀 구분자로 추출
- [x] 커스텀 구분자 이후의 숫자 부분만 추출
- [x] 이스케이프된 개행문자(\\n) 처리 지원
- [x] 매직 넘버 상수화로 가독성 향상

### 5. DelimiterParser (전략 패턴 적용)

- [x] DelimiterParser 인터페이스 정의
- [x] CustomDelimiterParser: 커스텀 구분자 처리
- [x] DefaultDelimiterParser: 기본 구분자 처리 및 상수화
- [x] SingleNumberParser: 단일 숫자 처리
- [x] 책임 연쇄 패턴으로 파서 선택

### 6. InputParser (파싱 책임 분리)

- [x] DelimiterParser들의 조합 로직 캡슐화
- [x] 입력 형식 예외를 Parser 레이어에서 처리
- [x] StringCalculator에서 파싱 책임 분리

### 7. StringCalculator (계산 흐름 조율)

- [x] null 또는 빈 문자열 입력 시 0 반환
- [x] InputParser로 파싱 책임 위임
- [x] Numbers로 변환 및 계산 책임 위임
- [x] 계산 흐름 조율만 담당

### 8. CalculatorFactory (객체 조립)

- [x] 모든 객체 생성 및 조립 책임 담당
- [x] 팩토리 패턴으로 복잡도 캡슐화

### 9. Application (입출력)

- [x] "덧셈할 문자열을 입력해 주세요." 출력
- [x] 사용자로부터 문자열 입력 받기 (Console.readLine() 사용)
- [x] 계산 결과를 "결과 : {숫자}" 형식으로 출력
- [x] 예외 발생 시 IllegalArgumentException을 그대로 전파하여 프로그램 종료
- [x] 입출력만 담당 (객체 조립은 Factory로 분리)

## 클래스 구조

```
calculator/
├── Application.java                     # 입출력 담당
├── CalculatorFactory.java               # 객체 생성 및 조립
├── StringCalculator.java                # 계산 흐름 조율 및 변환
├── Number.java                          # 숫자 도메인 (파싱과 검증)
├── Numbers.java                         # 숫자 컬렉션 (상태와 계산)
├── DelimiterExtractor.java              # 구분자 추출
├── StringSplitter.java                  # 문자열 분리
└── parser/
    ├── InputParser.java                 # 파싱 책임
    ├── DelimiterParser.java             # 파싱 전략 인터페이스
    ├── CustomDelimiterParser.java       # 커스텀 구분자 파싱
    ├── DefaultDelimiterParser.java      # 기본 구분자 파싱
    └── FallbackParser.java              # 폴백 파싱
```

## 객체지향 설계 원칙

### 단일 책임 원칙 (SRP)

- 각 클래스는 하나의 책임만 가짐
- Number: 숫자 파싱과 검증
- Numbers: 숫자 컬렉션 관리 및 계산
- InputParser: 파싱 로직 조합
- StringCalculator: 계산 흐름 조율
- CalculatorFactory: 객체 조립
- Application: 입출력

### 개방-폐쇄 원칙 (OCP)

- DelimiterParser 인터페이스를 통한 전략 패턴 적용
- 새로운 구분자 추가 시 새로운 Parser 구현체만 추가하면 됨
- 기존 코드 수정 최소화

### 의존성 역전 원칙 (DIP)

- StringCalculator는 구체적인 구현이 아닌 InputParser에 의존
- InputParser는 DelimiterParser 인터페이스에 의존
- 생성자 주입을 통한 의존성 주입
- 테스트 용이성 및 유연성 확보

### 일급 컬렉션 패턴

- Numbers 클래스로 숫자 배열을 래핑
- 상태와 행위를 함께 가지는 객체
- 불변 객체로 안전성 확보

### 도메인 객체의 자가 검증

- Number 객체가 생성 시점에 스스로 검증
- 항상 유효한 상태 보장
- 외부 Validator 불필요

### 낮은 결합도, 높은 응집도

- 인터페이스 활용으로 낮은 결합도 달성
- 각 클래스는 자신의 책임에 집중하여 높은 응집도 유지

## 테스트 실행

```bash
./gradlew clean test
```

모든 테스트가 통과함을 확인했습니다. ✅