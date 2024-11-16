<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <h1>Zentasks: List It & Do It</h1>
  <p>
    ManageTask is an application designed for personal and team task management, developed using <strong>Clean Architecture</strong> to ensure maintainable, scalable, and testable code. The app focuses on helping users manage tasks effectively with diverse features like task status management, progress tracking, and reporting.
  </p>

  <h2>📌 Key Features</h2>
  <ul>
    <li>
      <strong>Task Status Management</strong>
      <ul>
        <li>Assign statuses to tasks: <em>Pending</em>, <em>In Progress</em>, <em>Completed</em>, <em>Canceled</em>.</li>
        <li>Quickly update task statuses.</li>
      </ul>
    </li>
    <li>
      <strong>Group Task Management</strong>
      <ul>
        <li>Organize tasks by project or team.</li>
        <li>Assign tasks to specific team members.</li>
      </ul>
    </li>
    <li>
      <strong>Progress Status Reporting</strong>
      <ul>
        <li>Summarize progress for each group, project, or individual.</li>
        <li>Display completion rates via charts.</li>
      </ul>
    </li>
    <li>
      <strong>Daily Task List</strong>
      <ul>
        <li>Show all tasks to be completed for the day.</li>
        <li>Suggest task prioritization based on deadlines.</li>
      </ul>
    </li>
  </ul>

  <h2>⚙️ Architecture - Clean Architecture</h2>
  <p>The app is structured following the <strong>Clean Architecture</strong> pattern, with three main layers:</p>
  <ol>
    <li>
      <strong>Presentation Layer</strong>
      <ul>
        <li><strong>Goal</strong>: Display data and handle user interactions.</li>
        <li><strong>Technologies Used</strong>: 
          <ul>
            <li>ViewModel (MVVM) for managing data and lifecycle.</li>
            <li>LiveData/StateFlow for real-time UI updates.</li>
          </ul>
        </li>
      </ul>
    </li>
    <li>
      <strong>Domain Layer</strong>
      <ul>
        <li><strong>Goal</strong>: Handle business logic and use cases.</li>
        <li><strong>Technologies Used</strong>: 
          <ul>
            <li>Independent use cases for each feature.</li>
            <li>Interfaces for communication with the Data layer.</li>
          </ul>
        </li>
      </ul>
    </li>
    <li>
      <strong>Data Layer</strong>
      <ul>
        <li><strong>Goal</strong>: Manage local data storage using Room and SharedPreferences.</li>
        <li><strong>Technologies Used</strong>: 
          <ul>
            <li>Room for managing structured local database.</li>
            <li>SharedPreferences for lightweight key-value storage.</li>
          </ul>
        </li>
      </ul>
    </li>
  </ol>

  <h2>🛠️ Technologies & Libraries</h2>
  <ul>
    <li><strong>Language</strong>: Kotlin</li>
    <li><strong>Dagger Hilt</strong>: Dependency injection</li>
    <li><strong>Coroutines + Flow</strong>: Asynchronous operations</li>
    <li><strong>Room</strong>: Local database management</li>
    <li><strong>SharedPreferences</strong>: Lightweight data storage</li>
    <li><strong>Material Design</strong>: Modern user interface</li>
  </ul>

  <h2>📂 Folder Structure</h2>
  <pre>
├── presentation/
│   ├── ui/                     # User interface
│   ├── viewmodel/              # ViewModels handling UI logic
├── domain/
│   ├── model/                  # Define business entities
│   ├── usecase/                # Business logic processing
├── data/
│   ├── repository/             # Data repositories
│   ├── db/                     # Room database management
│   ├── preferences/            # SharedPreferences utilities
└── di/                         # Dependency Injection setup
  </pre>

  <h2>🌟 How to Use</h2>
  <ol>
    <li>Clone the repository:
      <pre>
git clone https://github.com/Arino130/TaskManage-Android
cd managetask
      </pre>
    </li>
    <li>Build and run the app in Android Studio.</li>
    <li>Launch the app, create groups, and start managing your tasks.</li>
  </ol>

  <h2>🚀 Future Development</h2>
  <ul>
    <li>Add task reminder notifications.</li>
    <li>Enable data synchronization across devices.</li>
    <li>Provide detailed analytics for group and individual performance.</li>
  </ul>

  <h2>📧 Contact</h2>
  <p>For questions or feedback, please contact us at <a href="mailto:phuongtm130@gmail.com">phuongtm130@gmail.com</a>.</p>

  <h2>📱 Download on Google Play</h2>
  <p>
    Get the app now on Google Play: 
    <a href="https://play.google.com/store/apps/details?id=com.ctp.zentasks" target="_blank">
      Zentasks: List It & Do It on Google Play
    </a>
  </p>

  <p><strong>Thank you for using ManageTask!</strong></p>
</body>
</html>
