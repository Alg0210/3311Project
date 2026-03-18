src/
├── main/
│   └── java/
│       └── org/
│           └── example/
│               
│                   ├── Main.java
│                   ├── users/
│                   │   ├── User.java (abstract)
│                   │   ├── Student.java
│                   │   ├── Faculty.java
│                   │   ├── Researcher.java
│                   │   ├── Guest.java
│                   │   ├── LabManager.java
│                   │   ├── HeadCoordinator.java
│                   │   ├── UserFactory.java
│                   │   └── UserDecorator.java
│                   ├── auth/
│                   │   ├── AuthService.java
│                   │   └── PasswordValidator.java
│                   ├── equipment/
│                   │   ├── Equipment.java
│                   │   ├── EquipmentStatus.java (enum)
│                   │   ├── EquipmentManager.java
│                   │   ├── EquipmentCommand.java (interface)
│                   │   └── EquipmentAction.java
│                   ├── sensors/
│                   │   ├── SensorObservable.java (interface)
│                   │   ├── SensorObserver.java (interface)
│                   │   └── EquipmentSensor.java
│                   ├── reservation/
│                   │   ├── Reservation.java
│                   │   ├── ReservationStatus.java (enum)
│                   │   ├── ReservationManager.java
│                   │   ├── ReservationCommand.java (interface)
│                   │   └── ReservationAction.java
│                   ├── payment/
│                   │   ├── Payment.java
│                   │   ├── PricingStrategy.java (interface)
│                   │   ├── UserPricingStrategy.java
│                   │   └── PaymentDecorator.java
│                   ├── data/
│                   │   ├── CSVHandler.java (Singleton)
│                   │   └── CSVRepository.java
│                   └── gui/
│                       ├── MainApp.java
│                       ├── MainController.java
│                       └── panels/
│                           ├── LoginPanel.java
│                           ├── RegisterPanel.java
│                           ├── DashboardPanel.java
│                           ├── EquipmentPanel.java
│                           ├── ReservationPanel.java
│                           └── ManagerPanel.java
└── main/
    └── resources/
        ├── styles/
        │   └── main.css
        └── data/
            ├── users.csv
            ├── equipment.csv
            ├── reservations.csv
            └── payments.csv


Design Pattern Coverage

Factory: UserFactory.java
Strategy: PricingStrategy.java, UserPricingStrategy.java
Command: EquipmentCommand.java, EquipmentAction.java, ReservationCommand.java, ReservationAction.java
Singleton: CSVHandler.java
Observer: SensorObservable.java, SensorObserver.java, EquipmentSensor.java
Decorator: UserDecorator.java, PaymentDecorator.java

            



            
