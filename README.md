# Birthday Tracker
(last modified: 01.12.2021 on version 0.1.0)
## Introduction
The Birthday Tracker is a program which lets you save the date of birth of
persons (including year). It then calculates their age and shows their
birthdays.
## Users Guide
TODO
## Collaboraters Guide
### 1. Exception Handling
We use gugorrex.util.ExceptionHandler to log exceptions instead of plain
printStackTrace(). Log4J2 is integrated into the ExceptionHandler.
Therefore, calling public methods from ExceptionHandler will automatically
log the exception in the correct format, specified in log4j2.xml.

We also use gugorrex.util.MainUncaughtExceptionHandler to handle all
exceptions which we missed. The Logs will also tell that an exception
is logged by MainUncaughtExceptionHandler and was therefore missed.

MainUncaughtExceptionHandler uses the ExceptionHandler to ensure uniform
logging of exceptions. Different xxxUncaughtExceptionHandler can be created
in the same way for other threads.

### 2. Logging
We use Log4J2 for logging, and it should be the only form to use.
An Log4J2 logger must be created for each class.

### 3. Frontend (GUI)
We use JavaFX for the frontend because this is only a small project so that
a better library is not necessary.

Together with JavaFX we use an MVVM-like pattern:
 - View defines our GUI (view.fxml)
 - ViewModel defines what the GUI elements do
 - (Model) defines the data structure