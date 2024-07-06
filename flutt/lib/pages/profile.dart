import 'dart:io';
import 'package:flutter/material.dart';
import 'package:test1/pages/welcome_page.dart';

class StudentInfoPage extends StatelessWidget {
  final String name;
  final double gpa;
  final String studentid;
  final String profilePictureUrl = "https://via.placeholder.com/150";

  StudentInfoPage({
    Key? key,
    required this.name,
    required this.gpa,
    required this.studentid,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blueGrey[400],
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          "User Info",
          style: TextStyle(
            color: Colors.white,
            fontSize: 28,
            fontWeight: FontWeight.bold,
          ),
        ),
        centerTitle: true,
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(25.0),
          child: Container(
            width: 400,
            height: 500,
            decoration: BoxDecoration(
              color: Colors.blueGrey[500],
              borderRadius: BorderRadius.circular(50),
              boxShadow: [
                BoxShadow(
                  color: Colors.black26,
                  blurRadius: 10,
                  offset: Offset(0, 5),
                ),
              ],
            ),
            child: Padding(
              padding: EdgeInsets.all(20.0),
              child: Column(
                children: [
                  CircleAvatar(
                    radius: 50,
                    backgroundImage: NetworkImage(profilePictureUrl),
                  ),
                  SizedBox(height: 20), // Space between the picture and text
                  Text(
                    "Name: $name",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                  Divider(color: Colors.white), // Divider
                  Text(
                    "StudentId: $studentid",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                  Divider(color: Colors.white), // Divider
                  Text(
                    "GPA: $gpa",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                  Spacer(), // Push the button//////////// ckeck//////////
                  ElevatedButton(
                    onPressed: () {
                      DeleteAccount(context);
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.red,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(30),
                      ),
                      padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                    ),
                    child: Text(
                      "Delete Account!",
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  Future<void> DeleteAccount(BuildContext context) async {
    try {
      Socket socket = await Socket.connect("192.168.1.112", 8080);

      // Sending delete account data
      socket.write('GET: DeleteAccount,${this.name},${this.studentid}\u0000');
      socket.flush();
      socket.close();

      // Navigate to WelcomePage
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => welcome_page()),
      );
    } catch (e) {
      print("Failed to delete account: $e");
      // Handle error
    }
  }
}
