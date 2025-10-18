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

### 1. Calculator (순수 계산)
- [ ] 빈 배열 입력 시 0 반환
- [ ] 정수 배열의 합 계산

### 2. NumberConverter (문자열→숫자 변환)
- [ ] 문자열을 정수로 변환
- [ ] 앞뒤 공백 제거 후 변환
- [ ] 숫자가 아닌 문자열 입력 시 IllegalArgumentException 발생

### 3. InputValidator (입력 검증)
- [ ] null 입력 시 false 반환
- [ ] 빈 문자열 입력 시 true 반환 (유효한 입력으로 간주)
- [ ] 음수 입력 시 IllegalArgumentException 발생

### 4. StringSplitter (문자열 분리)
- [ ] 단일 구분자로 문자열 분리
- [ ] 쉼표(,)로 문자열 분리
- [ ] 콜론(:)으로 문자열 분리
- [ ] 쉼표와 콜론을 동시에 구분자로 사용하여 분리

### 5. DelimiterExtractor (구분자 추출)
- [ ] 문자열이 "//"로 시작하는지 확인 (커스텀 구분자 여부)
- [ ] "//"와 "\n" 사이의 문자를 커스텀 구분자로 추출
- [ ] 커스텀 구분자 이후의 숫자 부분만 추출

### 6. StringCalculator (전체 조합)
- [ ] null 또는 빈 문자열 입력 시 0 반환
- [ ] 숫자 하나만 입력 시 해당 숫자 반환
- [ ] 쉼표 구분자로 두 개 이상의 숫자 합 계산 (예: "1,2" → 3)
- [ ] 콜론 구분자로 두 개 이상의 숫자 합 계산 (예: "1:2:3" → 6)
- [ ] 쉼표와 콜론 혼합 사용 (예: "1,2:3" → 6)
- [ ] 커스텀 구분자 사용 (예: "//;\n1;2;3" → 6)
- [ ] 음수 입력 시 IllegalArgumentException 발생
- [ ] 잘못된 형식 입력 시 IllegalArgumentException 발생

### 7. Application (입출력)
- [ ] "덧셈할 문자열을 입력해 주세요." 출력
- [ ] 사용자로부터 문자열 입력 받기 (Console.readLine() 사용)
- [ ] 계산 결과를 "결과 : {숫자}" 형식으로 출력
- [ ] 예외 발생 시 IllegalArgumentException을 그대로 전파하여 프로그램 종료

## 클래스 설계

```
calculator/
├── Application.java              # 메인 실행, 입출력 담당
├── StringCalculator.java         # 전체 계산 흐름 조합
├── validator/
│   └── InputValidator.java       # 입력 검증 책임
├── parser/
│   └── DelimiterExtractor.java   # 구분자 추출 책임
├── splitter/
│   └── StringSplitter.java       # 문자열 분리 책임
├── converter/
│   └── NumberConverter.java      # 문자열→숫자 변환 책임
└── calculator/
    └── Calculator.java           # 순수 계산 책임
```

## 객체지향 설계 원칙

### 단일 책임 원칙 (SRP)
- 각 클래스는 하나의 책임만 가짐
- InputValidator: 검증만
- DelimiterExtractor: 구분자 추출만
- Calculator: 계산만

### 개방-폐쇄 원칙 (OCP)
- 새로운 구분자 추가 시 기존 코드 수정 최소화
- 새로운 검증 규칙 추가 용이

### 의존성 역전 원칙 (DIP)
- 구체적인 구현이 아닌 추상화에 의존
- 필요시 인터페이스 도입 고려

## 개발 순서 (의존성 기반)

의존성이 적은 것부터 개발하여 안정적인 기반 구축

```
1. Calculator (순수 계산 로직 - 의존성 없음)
2. NumberConverter (문자열→숫자 변환 - 의존성 없음)
3. InputValidator (입력 검증 - 의존성 없음)
4. StringSplitter (문자열 분리 - 의존성 없음)
5. DelimiterExtractor (구분자 추출 - 의존성 없음)
6. StringCalculator (전체 조합 - 위 모든 클래스 의존)
7. Application (사용자 인터페이스 - StringCalculator 의존)
```

## 커밋 전략

각 기능 단위로 테스트 작성 → 구현 → 커밋 (TDD 사이클)

```
1. docs: 프로젝트 개요 및 기능 목록 작성
2. test: Calculator 빈 배열 0 반환 테스트 추가
3. feat: Calculator 빈 배열 0 반환 구현
4. test: Calculator 정수 배열 합 계산 테스트 추가
5. feat: Calculator 정수 배열 합 계산 구현
6. test: NumberConverter 문자열 정수 변환 테스트 추가
7. feat: NumberConverter 문자열 정수 변환 구현
...
```