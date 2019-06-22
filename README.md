# Consumer

Consumer is a Java application for processing data coming from Simplaex Producer.
The service listens on port 9000 and reads CSV-stream line by line. 
If the next line is not available, it waits for 1000 millis and closes the connection.
A new connection will be opened once Simplaex Producer is ready.
Consumer creates a file with the analysis of those 1000 messages. The files are stored in '/results' directory. They are named '1.txt', '2.txt' etc.

Port, timeout, target folder name, number of lines to analise and number of files to create can be configured
in ConsumerApplication.java. NumberOfFilesToCreate is -1 by default. In means the service runs until it is interrupted 
(e.g. by issuing Ctrl+C or killing it). If you set up another value (e.g. 5) Consumer creates max 5 files with data analyses for safe testing.


## Installation
Create jar file: ./gradlew build
Run it: java -jar consumer-1.0-SNAPSHOT.jar --tcp
