
# 📱 MyAssssmentApplication

An Android application developed for NIT3213 that includes Login, Dashboard, and Details screens. It interacts with the vu-nit3213-api for user authentication and data retrieval.

## ✅ Features

- 🔐 Login with student's first name and student ID (e.g., s8136414)
- 📊 Dashboard displaying a list of entities using RecyclerView
- 📄 Details screen showing full information for selected item
- 🧠 MVVM architecture using ViewModel and LiveData
- 💉 Dependency Injection using Hilt
- 🧪 Unit tested ViewModels with JUnit and Mockito

## 🧰 Technologies Used

- Kotlin + AndroidX
- Retrofit2 + Gson
- Hilt for DI
- ViewModel + LiveData
- Coroutines
- RecyclerView
- JUnit, Mockito, kotlinx-coroutines-test

## 🚀 Setup Instructions

### 1. Clone the Repository

```
git clone https://github.com/devtanvir388/MyAssssmentApplication.git
cd MyAssssmentApplication
```

### 2. Open in Android Studio

- File > Open > Select project folder

### 3. SDK Setup

Ensure your `local.properties` contains:

```
sdk.dir=C:\Users\YourUsername\AppData\Local\Android\Sdk
```

> Do NOT push `local.properties` to GitHub

### 4. Build the Project

```
./gradlew clean build
```

Or click **Build > Rebuild Project** in Android Studio.

### 5. Run the App

- Select an emulator or device
- Click the green ▶️ Run button

## 🧪 Running Unit Tests

Use Android Studio:
- Right-click on `DashboardViewModelTest.kt` or `LoginViewModelTest.kt`
- Select **Run**

Or run from terminal:

```
./gradlew test
```

## 🌐 API Information

Base URL:
```
https://nit3213api.onrender.com/
```

Login Endpoints (choose your location):
- `/sydney/auth`
- `/footscray/auth`
- `/ort/auth`

Dashboard Endpoint:
- `/dashboard/{keypass}`

## 📦 Dependencies Summary

Included in `build.gradle.kts`:
- Hilt (2.48)
- Lifecycle ViewModel (2.6.2)
- Retrofit2 + Gson
- Navigation Components (2.7.5)
- Coroutines (1.7.3)
- JUnit, Mockito, Coroutines Test

## 🙌 Author

**Tanvir Islam Fahad**  
Student ID: `s8136414`  
GitHub: [github.com/devtanvir388](https://github.com/devtanvir388)
