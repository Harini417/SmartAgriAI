# 🌾 SmartAgriAI

SmartAgriAI is a full-stack web application that helps farmers manage crops efficiently using modern web technologies and AI-powered features. It provides crop management, weather monitoring, soil analysis, irrigation recommendations, yield prediction, and analytics through an intuitive dashboard.

---

## 🚀 Features

### 👤 User Authentication
- User Registration
- Secure Login
- Logout
- User-specific crop management

### 🌱 Crop Management
- Add Crop
- Edit Crop
- Delete Crop
- Search Crops
- Crop Type & Crop Name Selection
- Country → State → Hilly Region Selection
- Status Tracking

### 📊 Dashboard
- Crop Statistics
- Interactive Charts
- Crop Analytics
- Overview Cards

### 🌦 Weather Monitoring
- Weather Information
- Weather Alerts

### 🌍 Soil Monitoring
- Soil Details
- Soil Type Management

### 💧 Irrigation Recommendation
- Smart irrigation suggestions

### 🌾 Yield Prediction
- AI-based crop yield prediction

### 🤖 AI Crop Recommendation
- Recommend suitable crops based on user inputs

### ⚙ Settings
- User Profile
- Theme Switching (Light/Dark)
- Notification Preferences
- Change Password
- Logout

### 🔔 Notifications
- Crop-related notifications
- Success and error Snackbar alerts

---

## 🛠 Tech Stack

### Frontend
- React
- Vite
- Material UI (MUI)
- Axios
- React Router
- MUI DataGrid
- Lucide React
- Recharts

### Backend
- Spring Boot
- Spring Data JPA
- Spring Security
- Maven

### Database
- MySQL

---

## 📂 Project Structure

```
SmartAgriAI
│
├── frontend
│   ├── src
│   ├── public
│   └── package.json
│
├── backend
│   ├── src
│   ├── pom.xml
│   └── mvnw
│
└── README.md
```

---

## ⚙ Installation

### Clone the repository

```bash
git clone https://github.com/Harini417/SmartAgriAI.git
```

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

Backend runs at:

```
http://localhost:8080
```

---

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs at:

```
http://localhost:5173
```

---

## 🗄 Database

Create a MySQL database named:

```
smart_agriculture
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_agriculture
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

---

## 📸 Screenshots

You can add screenshots here after uploading them.

- Login Page
- Dashboard
- Crop Management
- Weather Monitoring
- Settings

---

## 🔮 Future Enhancements

- Disease Detection using AI
- Image Upload
- Mobile Responsive UI
- Email Notifications
- PDF & Excel Export
- Live Weather API
- Cloud Deployment


---

## 📄 License

This project is developed for educational and learning purposes.
