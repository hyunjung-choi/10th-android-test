# 🖼️ 이미지 스트림 아카이빙 앱

## 🤍 Summary

### Home Screen

- **로딩 중인 상태**
    - `SkeletonView` + `CircularProgressIndicator` 사용
- **북마크**
    - 북마크가 없는 상태 → 북마크 섹션 생략
    - 북마크가 있는 상태 → `Room` 에 저장 → 북마크 섹션 보여짐
- **무한 스크롤**
    - 페이지 단위로 API를 호출하여, 최하단에 도달 시 다음 페이지 이미지들을 불러옴

### Detail Screen

- 최신 이미지 또는 북마크의 사진 클릭 시 `DetailPhotoScreen`으로 이동
- 사진의 가로 사이즈는 고정적이며, 높이는 가변적으로 변경
- Description은 2줄, 태그는 4개까지 노출
- 우측 상단 북마크 버튼 기능

## 📸 ScreenShots

### Home Screen

<p>
  <img src="https://github.com/user-attachments/assets/db55910c-0c4e-43e5-9659-1b11ad601619", width="300" />
  <img src="https://github.com/user-attachments/assets/a22ed761-36ae-4861-b1f3-be23d472f02f", width="300" />
</p>

### Detail Screen

<p>
  <img src="https://github.com/user-attachments/assets/d5acc6df-0a72-48c3-8542-9e271f8ff472", width="300" />
  <img src="https://github.com/user-attachments/assets/d8d9dad4-4e7f-41eb-847d-833b82f64439", width="300" />
</p>

### Random Screen

<p>
  <img src="https://github.com/user-attachments/assets/2824a817-5b39-4d51-a47f-d06ddf7cc73a", width="300" />
</p>

## 📂 Project Directory Structure

```
📂 com.prography.android.test.hyunjung/
├── 📂 data/  
│   ├── 📂 local/       # Room DB 관련 코드  
│   ├── 📂 model/       # 데이터 모델 정의  
│   ├── 📂 network/     # API 서비스 인터페이스 및 네트워크 관련 코드  
│   └── 📂 repository/  # 데이터 레이어(레포지토리) 구현  
├── 📂 di/              # 의존성 주입(DI) 모듈  
├── 📂 navigation/      # 앱 내 내비게이션 관련 코드  
├── 📂 ui/  
│   ├── 📂 component/   # 재사용 가능한 UI 컴포넌트  
│   ├── 📂 detail/      # 상세 화면  
│   ├── 📂 home/        # 홈 화면  
│   ├── 📂 random/      # 랜덤 화면  
│   └── 📂 theme/       # 테마 관련 코드 및 스타일 정의  
└── 📂 utils/           # 유틸리티 클래스 및 헬퍼 함수  
```

## 🛠️ Tech Stack

- **Minimum SDK Version** 24

- **Target SDK Version** 35

- **Language**
    - Kotlin

- **UI**
    - Jetpack Compose

- **API**
    - [Unsplash](https://unsplash.com/developers)

- **Image**
    - Coil

- **Network**
    - Ktor

- **Asynchronous Processing**
    - Coroutines & Flow

- **DI**
    - Hilt
