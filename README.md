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

### 1. Adder (순수 계산)

- [x] 빈 배열 입력 시 0 반환
- [x] 정수 배열의 합 계산
- [x] BigInteger를 이용한 큰 숫자 합 계산

### 2. NumberConverter (문자열→숫자 변환)

- [x] 문자열을 BigInteger로 변환
- [x] 앞뒤 공백 제거 후 변환
- [x] 매우 큰 숫자 변환 지원
- [x] 숫자가 아닌 문자열 입력 시 IllegalArgumentException 발생

### 3. NumberValidator (숫자 검증)

- [x] 음수 입력 시 IllegalArgumentException 발생
- [x] 양수 및 0 검증 통과
- [x] BigInteger 검증 지원

### 4. StringSplitter (문자열 분리)

- [x] 단일 구분자로 문자열 분리
- [x] 쉼표(,)와 콜론(:)을 기본 구분자로 사용하여 분리
- [x] 정규식을 활용한 복합 구분자 처리

### 5. DelimiterExtractor (구분자 추출)

- [x] 문자열이 "//"로 시작하는지 확인 (커스텀 구분자 여부)
- [x] "//"와 "\n" 사이의 문자를 커스텀 구분자로 추출
- [x] 커스텀 구분자 이후의 숫자 부분만 추출
- [x] 이스케이프된 개행문자(\\n) 처리 지원
- [x] 매직 넘버 상수화로 가독성 향상

### 6. DelimiterParser (전략 패턴 적용)

- [x] DelimiterParser 인터페이스 정의
- [x] CustomDelimiterParser: 커스텀 구분자 처리
- [x] DefaultDelimiterParser: 기본 구분자(쉼표, 콜론) 처리
- [x] SingleNumberParser: 단일 숫자 처리
- [x] 책임 연쇄 패턴으로 파서 선택

### 7. StringCalculator (전체 조합)

- [x] null 또는 빈 문자열 입력 시 0 반환
- [x] 숫자 하나만 입력 시 해당 숫자 반환
- [x] 쉼표 구분자로 두 개 이상의 숫자 합 계산 (예: "1,2" → 3)
- [x] 콜론 구분자로 두 개 이상의 숫자 합 계산 (예: "1:2:3" → 6)
- [x] 쉼표와 콜론 혼합 사용 (예: "1,2:3" → 6)
- [x] 커스텀 구분자 사용 (예: "//;\n1;2;3" → 6)
- [x] 이스케이프된 개행문자로 커스텀 구분자 사용 (예: "//;\\n1;2;3" → 6)
- [x] 매우 큰 숫자 계산 지원
- [x] 음수 입력 시 IllegalArgumentException 발생
- [x] 잘못된 형식 입력 시 IllegalArgumentException 발생
- [x] 의존성 주입을 통한 테스트 용이성 확보

### 8. Application (입출력)

- [x] "덧셈할 문자열을 입력해 주세요." 출력
- [x] 사용자로부터 문자열 입력 받기 (Console.readLine() 사용)
- [x] 계산 결과를 "결과 : {숫자}" 형식으로 출력
- [x] 예외 발생 시 IllegalArgumentException을 그대로 전파하여 프로그램 종료
- [x] createCalculator() 팩토리 메서드로 객체 생성 책임 분리

## 클래스 설계

```
calculator/
├── Application.java                     # 메인 실행, 입출력, 객체 조립 담당
├── StringCalculator.java                # 전체 계산 흐름 조합 (의존성 주입)
├── DelimiterExtractor.java              # 구분자 추출 책임
├── StringSplitter.java                  # 문자열 분리 책임
├── NumberConverter.java                 # 문자열→BigInteger 변환 책임
├── NumberValidator.java                 # 숫자 검증 책임
├── Adder.java                           # 순수 덧셈 계산 책임
└── parser/
    ├── DelimiterParser.java             # 파싱 전략 인터페이스
    ├── CustomDelimiterParser.java       # 커스텀 구분자 파싱 전략
    ├── DefaultDelimiterParser.java      # 기본 구분자 파싱 전략
    └── SingleNumberParser.java          # 단일 숫자 파싱 전략
```

## 객체지향 설계 원칙

### 단일 책임 원칙 (SRP)

- 각 클래스는 하나의 책임만 가짐
- NumberValidator: 숫자 검증만
- DelimiterExtractor: 구분자 추출만
- Adder: 덧셈 계산만
- Application: 객체 조립 및 입출력만

### 개방-폐쇄 원칙 (OCP)

- DelimiterParser 인터페이스를 통한 전략 패턴 적용
- 새로운 구분자 추가 시 새로운 Parser 구현체만 추가하면 됨
- 기존 코드 수정 최소화

### 의존성 역전 원칙 (DIP)

- StringCalculator는 구체적인 구현이 아닌 DelimiterParser 인터페이스에 의존
- 생성자 주입을 통한 의존성 주입
- 테스트 용이성 및 유연성 확보

### 낮은 결합도, 높은 응집도

- 인터페이스 활용으로 낮은 결합도 달성
- 각 클래스는 자신의 책임에 집중하여 높은 응집도 유지

## 커밋 전략

각 기능 단위로 테스트 작성 → 구현 → 커밋 (TDD 사이클)

```
1. docs: 프로젝트 개요 및 기능 목록 작성
2. test: Adder 빈 배열 0 반환 테스트 추가
3. feat: Adder 빈 배열 0 반환 구현
4. test: Adder 정수 배열 합 계산 테스트 추가
5. feat: Adder 정수 배열 합 계산 구현
6. test: NumberConverter 문자열 정수 변환 테스트 추가
7. feat: NumberConverter 문자열 정수 변환 구현
8. refactor: DelimiterExtractor 매직 넘버 상수화
9. refactor: Calculator를 Adder로 이름 변경
10. refactor: InputValidator를 NumberValidator로 이름 변경
11. feat: DelimiterParser 인터페이스 및 구현체 추가
12. refactor: StringCalculator에 의존성 주입 적용
13. refactor: Application에 객체 생성 팩토리 메서드 추가
...
```

## 테스트 실행

```bash
./gradlew clean test
```

모든 테스트가 통과함을 확인했습니다. ✅