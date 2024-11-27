

### # **Registration CRUD Application**
This project is a simple Java-based CRUD application that allows the user to create, read, update, and delete registration records in a MySQL database. It includes input validation for fields such as email, date of birth, and phone number.
​
## **Features**
- Create: Adds a new registration record with user input.
- Read: Displays all registration records or a specific record by ID.
- Update: Updates an existing registration record by ID.
- Delete: Deletes a registration record by ID.
- Input Validation: Ensures the email, date of birth, and phone number meet specified criteria.
## **Technologies Used**
- Java
- MySQL Database
- JDBC (Java Database Connectivity)
## **Prerequisites**
Before running the project, ensure you have the following installed:
​
- Java Development Kit (JDK) version 17 or higher (Download it from Oracle)
- MySQL Database Server (Download it from MySQL website)
- IDE such as Eclipse or IntelliJ IDEA.
- JDK Version Compatibility
- Ensure you have JDK 17 or higher installed to avoid compatibility issues with the class file version.
​
## **MySQL Database Setup
Install MySQL on your local machine.**
​
1. Create a database for the project:
​
*CREATE DATABASE registration_db;*
Use the following SQL schema to create the necessary table in the database:
​
***CREATE TABLE Registration (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    DateOfBirth DATE NOT NULL,
    PhoneNumber VARCHAR(20),
# Registration CRUD Application
This project is a simple Java-based CRUD application that allows the user to create, read, update, and delete registration records in a MySQL database. It includes input validation for fields such as email, date of birth, and phone number.

Features
Create: Adds a new registration record with user input.
Read: Displays all registration records or a specific record by ID.
Update: Updates an existing registration record by ID.
Delete: Deletes a registration record by ID.
Input Validation: Ensures the email, date of birth, and phone number meet specified criteria.
Technologies Used
Java
MySQL Database
JDBC (Java Database Connectivity)

Prerequisites
Before running the project, ensure you have the following installed:

Java Development Kit (JDK) version 17 or higher (Download it from Oracle)

MySQL Database Server (Download it from MySQL website)
IDE such as Eclipse or IntelliJ IDEA.
JDK Version Compatibility
Ensure you have JDK 17 or higher installed to avoid compatibility issues with the class file version.
**MySQL Database Setup
Install MySQL on your local machine.**

Create a database for the project:
CREATE DATABASE registration_db;
Use the following SQL schema to create the necessary table in the database:

CREATE TABLE Registration (
ID INT AUTO_INCREMENT PRIMARY KEY,
Name VARCHAR(255) NOT NULL,
Email VARCHAR(255) NOT NULL UNIQUE,
DateOfBirth DATE NOT NULL,
PhoneNumber VARCHAR(20),
CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

Make sure to configure your database connection correctly (see DBConnection class).

Steps to Run the Project Locally
1. Clone the Project
Clone the repository to your local machine:

git clone

2. Import the Project into Your IDE
Open your IDE (e.g., Eclipse or IntelliJ).
Select File → Import (in Eclipse) and choose the project folder you cloned.
If using Eclipse, right-click the project and select Build Path → Configure Build Path → Libraries to include any necessary JAR files (like MySQL Connector/J).

3. Set Up MySQL Connection
In the DBConnection class, configure the database connection details:

URL: Set the URL to your local MySQL instance (e.g., jdbc:mysql://localhost:3306/registration_db).
Username and Password: Replace with your MySQL credentials.

For example:

public static Connection getConnection() throws SQLException {
String url = “jdbc:mysql://localhost:3306/registration_db”;
String username = “root”;
String password = “password”; // Replace with your MySQL password
return DriverManager.getConnection(url, username, password);
}

4. Add MySQL Connector JAR to Your Project
Download the MySQL JDBC driver (Connector/J) from here, and add it to your project.

In Eclipse:
Right-click the project → Build Path → Configure Build Path → Libraries → Add External JARs and select the mysql-connector-java-x.x.x.jar file.

5. Run the Application
In your IDE, locate the Main class (which contains the main method) and run it.
The program will ask you to choose an operation (e.g., Create, Read, Update, Delete).

Follow the instructions to interact with the registration data.

Sample Commands

Create: Add a new registration record.
Read: View a specific registration by ID or view all registrations.
Update: Modify a registration by ID.
Delete: Remove a registration by ID.
