import 'dart:io';

import 'package:flutter/material.dart';
import 'package:test1/pages7/Classa.dart';
import 'package:test1/pages7/Kara.dart';
import 'package:test1/pages7/Khabara.dart';
import 'package:test1/pages7/Tamrina.dart';
import '../pages/profile.dart';

class Sara extends StatefulWidget {
  String Id;

  Sara({super.key, required this.Id});

  @override
  _SaraState createState() => _SaraState();
}

class _SaraState extends State<Sara> {
  int bestScore = 0;
  int worstScore = 0;
  int numberOfAssignments = 0;
  String _error = '';

  @override
  void initState() {
    super.initState();
    SaraInfo();
  }

  Future<void> SaraInfo() async {
    try {
      Socket socket = await Socket.connect("192.168.1.112", 8080);

      // Sending request for SaraInfo
      socket.write('GET: SaraInfo,${widget.Id}\u0000');
      await socket.flush();

      // Listening for the response
      socket.listen((List<int> data) {
        String response = String.fromCharCodes(data);
        // format "bestScore,worstScore,numberOfAssignments"
        List<String> responseData = response.split(',');

        if (responseData.length == 3) {
          setState(() {
            bestScore = int.parse(responseData[0]);
            worstScore = int.parse(responseData[1]);
            numberOfAssignments = int.parse(responseData[2]);
          });
        }
        socket.close();
      });
    } catch (e) {
      setState(() {
        _error = 'Error: $e';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blueGrey[400],
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          "Sara",
          style: TextStyle(
            color: Colors.white,
            fontSize: 28,
            fontWeight: FontWeight.bold,
          ),
        ),
        actions: [
          IconButton(
            icon: Icon(Icons.person),
            onPressed: () {
              // Navigate to Student Info Page
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => StudentInfoPage(
                    name: "John Doe",
                    gpa: 3.75,
                    studentid: widget.Id,
                  ),
                ),
              );
            },
          ),
        ],
        centerTitle: true,
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              decoration: BoxDecoration(
                color: Colors.blue,
              ),
              child: Text(
                'Menu',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 24,
                ),
              ),
            ),
            ListTile(
              leading: Icon(Icons.home),
              title: Text('Sara'),
              onTap: () {
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: Icon(Icons.person),
              title: Text('Profile'),
              onTap: () {
                // Navigate to Profile Page
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => StudentInfoPage(
                      name: "John Doe",
                      gpa: 3.75,
                      studentid: widget.Id,
                    ),
                  ),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.calendar_month),
              title: Text('Kara'),
              onTap: () {
                // Navigate to Kara Page
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Kara()),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.hotel_class_sharp),
              title: Text('Classea'),
              onTap: () {
                // Navigate to Next Term Classes Page
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => NextTermClassesPage()),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.newspaper_rounded),
              title: Text('Khabara'),
              onTap: () {
                // Navigate to Contact Page
                Navigator.push(context, MaterialPageRoute(builder: (context) => Khabara()));
              },
            ),
            ListTile(
              leading: Icon(Icons.home_work),
              title: Text('Tamrina'),
              onTap: () {
                // Navigate to Contact Page
                Navigator.push(context, MaterialPageRoute(builder: (context) => Tamrina()));
              },
            ),
          ],
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(25.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            buildInfoCard("Best Score", bestScore.toString()),
            SizedBox(height: 20),
            buildInfoCard("Worst Score", worstScore.toString()),
            SizedBox(height: 20),
            buildInfoCard("Number of Assignments", numberOfAssignments.toString()),
            if (_error.isNotEmpty)
              Padding(
                padding: const EdgeInsets.only(top: 20),
                child: Text(
                  _error,
                  style: TextStyle(color: Colors.red, fontSize: 16),
                ),
              ),
          ],
        ),
      ),
    );
  }

  Widget buildInfoCard(String title, String content) {
    return Container(
      width: double.infinity,
      padding: EdgeInsets.all(15),
      decoration: BoxDecoration(
        color: Colors.blueGrey[500],
        borderRadius: BorderRadius.circular(15),
        boxShadow: [
          BoxShadow(
            color: Colors.black26,
            blurRadius: 10,
            offset: Offset(0, 5),
          ),
        ],
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            title,
            style: TextStyle(
              fontSize: 24,
              fontWeight: FontWeight.bold,
              color: Colors.white,
            ),
          ),
          SizedBox(height: 10),
          Text(
            "- $content",
            style: TextStyle(
              fontSize: 18,
              color: Colors.white,
            ),
          ),
        ],
      ),
    );
  }
}
