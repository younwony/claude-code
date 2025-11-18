# Simple Web Application

Spring Boot와 Thymeleaf를 사용한 간단한 웹 애플리케이션입니다.

## 기술 스택

- **Java**: 17
- **Spring Boot**: 3.2.0
- **Template Engine**: Thymeleaf
- **Build Tool**: Gradle
- **CSS**: Custom responsive design

## 프로젝트 구조

```
simple-webapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/webapp/
│   │   │       ├── WebApplication.java
│   │   │       └── controller/
│   │   │           └── HomeController.java
│   │   └── resources/
│   │       ├── static/
│   │       │   └── css/
│   │       │       └── style.css
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   └── about.html
│   │       └── application.properties
│   └── test/
│       └── java/
├── build.gradle
└── settings.gradle
```

## 실행 방법

### 필수 요구사항

- Java 17 이상
- Gradle 8.5 이상 (또는 Gradle Wrapper 사용)

### 애플리케이션 실행

1. 프로젝트 클론 또는 다운로드

2. 프로젝트 디렉토리로 이동
```bash
cd simple-webapp
```

3. (선택사항) Gradle Wrapper 생성
```bash
gradle wrapper --gradle-version 8.5
```

4. Gradle을 사용하여 애플리케이션 실행
```bash
./gradlew bootRun
# Windows의 경우: gradlew.bat bootRun
```

5. 브라우저에서 접속
```
http://localhost:8080
```

## 주요 기능

- **홈 페이지** (`/`): 환영 메시지와 현재 시간 표시
- **소개 페이지** (`/about`): 애플리케이션 정보 및 기술 스택 소개
- **반응형 디자인**: 모바일 및 데스크톱 환경 지원
- **모던 UI**: 그라디언트와 애니메이션 효과

## 개발 모드

Spring Boot DevTools가 포함되어 있어 코드 변경 시 자동으로 애플리케이션이 재시작됩니다.

## 빌드

프로젝트를 빌드하려면:

```bash
./gradlew clean build
```

실행 가능한 JAR 파일이 `build/libs/` 디렉토리에 생성됩니다.

```bash
java -jar build/libs/simple-webapp-1.0.0.jar
```

## 라이선스

이 프로젝트는 학습 및 데모 목적으로 작성되었습니다.
