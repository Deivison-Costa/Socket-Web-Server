# Socket Web Server for File Sharing
- This is a simple Java application that implements a Socket Web Server for file sharing. The server allows multiple clients to connect and request files from a shared folder. 
- The application utilizes concepts of Threads, Sockets, and Serialization.

# Prerequisites
## To run this application, you need the following:
- Java Development Kit (JDK) installed on your system.
- An IDE like Eclipse or any other Java IDE of your choice.

## Setup
- Clone or download this repository to your local machine.
- Open your Java IDE (Eclipse, for example).

## Import the project into the IDE:
- In Eclipse: File -> Import -> Existing Projects into Workspace -> Select the project folder -> Finish.

# Usage
## Starting the Socket Web Server
- Open the SocketWebServer.java file.
- Modify the SHARED_FOLDER_PATH constant to point to the folder you want to share with clients. 
- Replace "/path/to/shared/folder" with the actual path.
- Compile and run the SocketWebServer.java class.
- The server will start running and listen for incoming client connections on port 8080.

## Connecting and Requesting Files (ClientTest/FileClient.java)
### Modify the SERVER_IP, SERVER_PORT, and FILE_NAME constants as needed:
- **SERVER_IP**: Set this to the IP address of the machine running the Socket Web Server. If the server is running on the same machine, you can use "127.0.0.1".
- **SERVER_PORT**: Set this to the same port number that the Socket Web Server is listening on (default is 8080).
- **FILE_NAME**: Replace this with the name of the file you want to request from the shared folder.
## Compile and run the FileClient.java class: 
    javac FileClient.java
    java FileClient)

- The client will connect to the server and request the specified file from the shared folder. If the file is found, it will be saved in the current directory with the same name. If the file does not exist, the server will respond with a "File not found" message.
