# 🖼️ 10th Android Test

## 🤍 Summary

### 메인 화면

- **로딩 중인 상태**
    - `SkeletonView` + `CircularProgressIndicator` 사용
- **북마크**
    - 북마크가 없는 상태 → 북마크 섹션 생략
    - 북마크가 있는 상태 → `Room` 에 저장 → 북마크 섹션 보여짐
- **무한 스크롤**
    - 페이지 단위로 API를 호출하여, 최하단에 도달 시 다음 페이지 이미지들을 불러옴

## 📸 ScreenShots

<p>
  <img src="https://github.com/user-attachments/assets/7709b133-f468-459a-947a-39ddcdddf047", width="300" />
  <img src="https://github.com/user-attachments/assets/53f18a6b-4bef-47b0-8cdb-66a4d1c77eec", width="300" />
</p>

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
