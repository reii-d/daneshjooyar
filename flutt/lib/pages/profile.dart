import 'dart:io';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:test1/pages/welcome_page.dart';

class StudentInfoPage extends StatefulWidget {
  final String id;

  StudentInfoPage({
    Key? key,
    required this.id,
  }) : super(key: key);

  @override
  _StudentInfoPageState createState() => _StudentInfoPageState();
}

class _StudentInfoPageState extends State<StudentInfoPage> {
  String name = '';
  double gpa = 0.0;
  final String profilePictureUrl = "https://via.placeholder.com/150";

  @override
  void initState() {
    super.initState();
    buildProfile();
  }

  Future<void> deleteAccount(BuildContext context) async {
    try {
      Socket socket = await Socket.connect("172.20.116.103", 8080);

      // Sending delete account data
      socket.write('GET: DeleteAccount,$name,${widget.id}\u0000');
      await socket.flush();
      socket.close();

      // Navigate to WelcomePage after the account deletion
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => welcome_page()),
      );
    } catch (e) {
      print("Failed to delete account: $e");
    }
  }

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
                  SizedBox(height: 20),
                  Text(
                    "Name: $name",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                  Divider(color: Colors.white),
                  Text(
                    "StudentId: ${widget.id}",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                  Divider(color: Colors.white),
                  Text(
                    "GPA: $gpa",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                  Spacer(),
                  ElevatedButton(
                    onPressed: () {
                      deleteAccount(context);
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.red,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(30),
                      ),
                      padding:
                      EdgeInsets.symmetric(horizontal: 30, vertical: 15),
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

  Future<void> buildProfile() async {
    try {
      Socket socket = await Socket.connect("172.20.116.103", 8080);

      // Sending profile info request with studentid
      socket.write('GET: ProfileInfo,${widget.id}\u0000');
      await socket.flush();

      socket.listen((socketResponse) {
        String response = utf8.decode(socketResponse);
        List<String> dataParts = response.split(',');
        if (dataParts.length >= 2) {
          setState(() {
            gpa = double.tryParse(dataParts[0]) ?? 0.0;
            name = dataParts[1];
          });
        }
        if(dataParts.length < 2){
          setState(() {
            gpa =  0.0;
            name = dataParts[0];
          });
        }
      });

      //socket.close();
    } catch (e) {
      print("Failed to fetch profile information: $e");
    }
  }
}
