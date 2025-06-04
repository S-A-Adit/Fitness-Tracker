# Fitness-Tracker
The Fitness Tracker is a Java-based application designed to help users manage and analyze gym member data. It supports CRUD operations (Create, Read, Update, Delete) and includes querying and statistics functionality.

There are two versions of the app:

Console-Based Version: A command-line interface using Scanner for input/output.

GUI Version: A graphical interface using Java Swing (JFrame, JPanel, JButton, etc.) for a user-friendly experience.
# Console Version
Ensure Java is installed (javac -version, java -version).
javac *.java
java Fitness_TrackerDemo
Features
Automatically creates the FITNESSTRACKERDATASET table if it does not exist.

Menu-driven operations:

Add Rows: Insert new gym member data.

Run Query: Run age range and average count queries.

Update Row: Modify existing entries.

Delete Row: Remove a record.

Drop Table: Deletes the entire table and exits.

Prompts user to repeat or exit after each operation.
# GUI Version
javac *.java
java FitnessTrackerGUI
Features
User-friendly Swing interface with buttons for each operation:

Add Member

View Table

Run Query

Update Member

Delete Member

Drop Table

Text fields and dialogs to input or update member data.

Displays results using JTable or JTextArea.

DBMS: Apache Derby (Java DB)

Table Name: FITNESSTRACKERDATASET

Schema Example:

sql
Copy
Edit

Notes
Please do not repeat any action more than once in the same run (applies mostly to the console version).

GUI version provides more flexibility and usability.

Best suited for small-scale fitness/gym data management projects.
