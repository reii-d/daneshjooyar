import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:test1/pages7/Kara.dart';
import 'package:test1/pages7/Khabara.dart';
import 'package:test1/pages7/Tamrina.dart';
import '../pages/profile.dart';
import 'Classa.dart';

class Sara extends StatefulWidget {
  final String id;

  Sara({Key? key, required this.id}) : super(key: key);

  @override
  _SaraState createState() => _SaraState();
}

class _SaraState extends State<Sara> {
  double bestScore = 0.0;
  double worstScore = 0.0;
  int numberOfAssignments = 0;

  String _error = '';

  @override
  void initState() {
    super.initState();
    SaraInfo();
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
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => StudentInfoPage(id: widget.id),
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
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => StudentInfoPage(id: widget.id),
                  ),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.calendar_month),
              title: Text('Kara'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Kara(id:widget.id)),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.hotel_class_sharp),
              title: Text('Classea'),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) => Classa(id: widget.id)));
              },
            ),
            ListTile(
              leading: Icon(Icons.newspaper_rounded),
              title: Text('Khabara'),
              onTap: () {
                // Navigate to Contact Page
                Navigator.push(context, MaterialPageRoute(builder: (context) => Khabara(id: widget.id)));
              },
            ),
            ListTile(
              leading: Icon(Icons.home_work),
              title: Text('Tamrina'),
              onTap: () {
                // Navigate to Contact Page
                Navigator.push(context, MaterialPageRoute(builder: (context) => Tamrina(id: widget.id)));
              },
            ),
            // Add other list tiles for navigation
          ],
        ),
      ),
      body: Padding(
        padding: EdgeInsets.all(25.0),
        child: Column(
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
            content,
            style: TextStyle(
              fontSize: 18,
              color: Colors.white,
            ),
          ),
        ],
      ),
    );
  }

  Future<void> SaraInfo() async {
    try {
      Socket socket = await Socket.connect("172.20.116.103", 8080);

      // Sending request for SaraInfo
      socket.write('GET: SaraInfo,${widget.id}\u0000');
      socket.flush();

      List<int> dataBuffer = [];
      await socket.listen((List<int> data) {
        dataBuffer.addAll(data);
      }).asFuture();

      String response = utf8.decode(dataBuffer).trim();
      print('Response received: $response');

      List<String> responseData = response.split(',');
      if (responseData.length >= 3) {
        setState(() {
          bestScore = double.parse(responseData[0]);
          worstScore = double.parse(responseData[1]);
          double responseDataDouble = double.parse(responseData[2]);
          numberOfAssignments = responseDataDouble.toInt();
        });
      }

      socket.close();
    } catch (e) {
      setState(() {
        _error = 'Error: $e';
      });
    }
  }
}
