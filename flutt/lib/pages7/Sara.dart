import 'dart:ffi';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:test1/pages7/Classa.dart';
import 'package:test1/pages7/Kara.dart';
import 'package:test1/pages7/Khabara.dart';
import 'package:test1/pages7/Tamrina.dart';
import '../pages/profile.dart';

class Sara extends StatefulWidget {
  final String Id;

  Sara({super.key, required this.Id});

  @override
  _SaraState createState() => _SaraState();
}

class _SaraState extends State<Sara> {
  double bestScore = 0.0;
  double worstScore = 0.0;
  double numberOfAssignments = 0.0;
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

      List<int> dataBuffer = [];
      // Listening for the response
      await socket.listen((List<int> data) {
        dataBuffer.addAll(data);
      }).asFuture();

      String response = String.fromCharCodes(dataBuffer).trim();
      print('Response received: $response'); // Debug print to verify received data

      // format "bestScore,worstScore,numberOfAssignments"
      List<String> responseData = response.split(',');

      if (responseData.length == 3) {
        setState(() {
          bestScore = double.parse(responseData[0]);
          worstScore = double.parse(responseData[1]);
          numberOfAssignments = double.parse(responseData[2]);
        });
      }

      socket.close();
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
                  builder: (context) => StudentInfoPage(studentid: widget.Id,),
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
            Container(
              height: 100,
              child: DrawerHeader(
                decoration: BoxDecoration(
                  color: Colors.blue,
                ),
                child: Padding(
                  padding: EdgeInsets.only(left: 16.0),
                  child: Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      'Menu',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 24,
                      ),
                    ),
                  ),
                ),
                margin: EdgeInsets.zero,
                padding: EdgeInsets.zero,
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
                    builder: (context) => StudentInfoPage(studentid: widget.Id,),
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
        padding: EdgeInsets.all(25.0),
        child: Column(
          //crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            ShowCard("Best Score", bestScore.toString()),
            SizedBox(height: 20),
            ShowCard("Worst Score", worstScore.toString()),
            SizedBox(height: 20),
            ShowCard("Number of Assignments", numberOfAssignments.toString()),
            if (_error.isNotEmpty)
              Padding(
                padding: EdgeInsets.only(top: 20),
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

  Widget ShowCard(String title, String content) {
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