## README
Read this file in its entirety before beginning to code.

### Chat Room Requirements
You must write server and client programs to let users exchange messages. Each user will run the ChatClient program and connect to a shared ChatServer.  The server will be responsible for forwarding incoming messages to all clients that are connected.

Typing a message and hitting send should transmit the desired message to the server, which will in turn forward the message to any other client that is connected. The server should also print something to its screen such as: "Joe: Hi I just joined" indicating that a message was sent by the user Joe.

**Optional Extra credit functionality:** add functionality to support multiple chatrooms and allow users to join. 

### Starter Code
You have been provided starter code that not only sets up the basic GUI, but also has the initial communication primitives. The code uses Inner Classes to modularize the communication functions (Hint: this will be particularly helpful on the server side).

The current code allows a single client to connect to the server. The client can then send messages to the server, which will display them to the screen.  However, the server does not send messages back to the client, nor is the server capable of simultaneously handling more than one client.

You should read through all of the provided Java files carefully and be sure you understand how the classes are related and how inner classes are being used. Your code should be based on these starter files, but you are free to modify/extend them however you please.  However, you may not completely scrap them to build a new architecture (if you do, it will be a red flag to the grader that you copy/pasted the code from somewhere you shouldn't have).

### Make a Plan
Before writing code, you should make a plan for how you will solve this project. What features will you work on and in what order? In what ways does the current code need to change?  How will you use threading?  What will the messages you send back and forth look like?

I strongly encourage you to build the system in an incremental way where you first get sending/receiving fully working with a single client, and then you move on to support multiple clients.  Be purposeful in the order you write your code, and go back and clean up old code if you find you need something different.  Try to make your code clean and elegant, not repetitive or full of unused/commented out functions.

### Important Note
For this project you may use the following resources (preferably in this order):
  - The lecture material and sample code
  - The textbook, particularly chapters 15, 12, and 13
  - The instructor, TAs, and peers on Piazza

If all of those fail to help you, then you can look at:
  - General online resources about Java sockets and GUIs

You **may NOT** look up resources specifically for building "chat servers" in java. If you are unsure how to proceed, use Piazza to ask questions.  As always, you **may NOT** copy/paste code from any online resources or other students. All work must be your own. We will be checking this carefully.
