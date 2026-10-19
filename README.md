# IoT-Sensor-Recording-actuation

A full-stack Internet of Things (IoT) data logging and hardware actuation platform. This multi-tier architecture automatically captures mobile hardware sensor analytics, streams them over network sockets to a Java Servlet Server backend, saves the events to a MySQL database, and interfaces with Phidget hardware controllers to trigger structural motor movements.

## 📂 Repository Architecture

The project is structured into three primary operational sub-systems:

```text
📁 IoT-Sensor-Recording-actuation-master/
│
├── 📁 MobileAssignment-Server/      # 🖥️ JAVA BACKEND SERVER (EE Engine)
│   ├── 📁 WebContent/WEB-INF/lib/   # Contains 'gson-2.8.1.jar' & 'mysql.jar'
│   └── 📁 src/                      # Core Web Servlets & DB Layer:
│       ├── 📄 sensorData.java       # Structured data object blueprints
│       └── 📄 sensorToDB.java       # JDBC MySQL persistence mapping logic
│
├── 📁 MobileAssignment-Ver3/        # 🕹️ HARDWARE HARD-ACTUATION SUITE
│   ├── 📁 libs/                     # Phidget22 & JLine embedded driver bundles
│   └── 📁 src/                      # Micro-controller loop automation:
│       ├── 📄 SliderMotor.java      # Hardware controller & motor throttle loops
│       └── 📄 SensorToServer.java   # Native socket networking client thread
│
├── 📁 sensorDB / sensorDB1/         # 📱 ANDROID CLIENT MOBILE APPS
│   ├── 📁 app/src/main/             # Native Android applications tracking sensors
│   └── 📄 build.gradle              # Android build configuration manifests
│
└── 📄 createsensortable.sql        # 🗄️ DATABASE CONFIGURATION SETUP
```

## 🚀 Features Across Sub-systems

### 1. Android Mobile Application (`sensorDB` / `sensorDB1`)
* **Live Sensor Streaming:** Tracks local Android mobile hardware telemetry fields in real-time.
* **JSON Serialization:** Uses Google `Gson` to marshal runtime variables into highly compressed string tokens for transmission.

### 2. Backend Web Engine (`MobileAssignment-Server`)
* **Java Web Servlet:** Exposes endpoints to process socket requests from mobile clients.
* **Database Persistence:** Utilizes a decoupled JDBC layer (`sensorToDB`) with transactional connection pools to securely insert entries into a remote MySQL database structure.

### 3. Actuation Broker Client (`MobileAssignment-Ver3`)
* **Phidget API Bridge:** Links software actions to real-world responses via the `phidget22` motor driver bundle.
* **Automated Slider Motor Control:** Interprets sensor thresholds to drive mechanical actuators or slider engines (`SliderMotor.java`) over multi-threaded console workers.

## 🛠️ Tech Stack & Prerequisites

* **Backend & Actuator:** Java Enterprise Edition (Java EE / Jakarta EE), Apache Tomcat 9+, JDBC, Sockets.
* **Android:** Native Android SDK (Min SDK 21+), Gradle Build System.
* **Database:** MySQL Server 8.0+.
* **Hardware Integration:** Phidgets Interface Kit or relative stepper/DC motor controller cards.

## ⚙️ Installation & Deployment

### Step 1: Database Setup
Import the system configuration schema into your local or remote MySQL Server cluster:
```bash
mysql -u your_username -p < createsensortable.sql
```

### Step 2: Backend Server Deployment
1. Import `MobileAssignment-Server` into an IDE such as **Eclipse IDE for Enterprise Java Developers** or **IntelliJ IDEA Ultimate**.
2. Configure your database login credentials (username, password, and port matching `createsensortable.sql`) within `src/sensorToDB.java`.
3. Add the target project to your **Apache Tomcat** server run-time settings.
4. Clean, build, and deploy the artifact to expose your web socket endpoints.

### Step 3: Actuator Loop Hookup
1. Ensure the Phidgets physical micro-controller hardware array is securely bridged to your host interface over USB.
2. Run the actuation runner within `MobileAssignment-Ver3` to begin polling network events:
   ```bash
   java -cp "libs/*:src/" SensorToServer
   ```

### Step 4: Android App Orchestration
1. Open the `sensorDB` folder as a project workspace inside **Android Studio**.
2. Adjust your target endpoint host URL inside `MainActivity.java` to point to your live Tomcat server instance.
3. Click **Sync Project with Gradle Files**, compile, and deploy the application to your testing device or emulator instance.

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).


