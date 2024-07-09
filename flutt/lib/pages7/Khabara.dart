import 'dart:io';
import 'package:flutter/material.dart';
import 'package:test1/pages7/Classa.dart';
import 'package:test1/pages7/Tamrina.dart';
import '../pages/profile.dart';
import 'Sara.dart';

class Khabara extends StatefulWidget {
  final String id;

  Khabara({required this.id});

  @override
  _KhabaraState createState() => _KhabaraState();
}

class _KhabaraState extends State<Khabara> {
  String response = '';
  List<Map<String, String>> newsList = [];

  @override
  void initState() {
    super.initState();
    Khabara();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blueGrey[400],
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          "Khabara",
          style: TextStyle(
            color: Colors.white,
            fontSize: 28,
            fontWeight: FontWeight.bold,
          ),
        ),
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
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Sara(id: widget.id)),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.person),
              title: Text('Profile'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => StudentInfoPage(id: widget.id)),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.calendar_month),
              title: Text('Kara'),
              onTap: () {
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: Icon(Icons.hotel_class_sharp),
              title: Text('Classea'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Classa(id: widget.id)),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.newspaper_rounded),
              title: Text('Khabara'),
              onTap: () {
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: Icon(Icons.home_work),
              title: Text('Tamrina'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Tamrina(id: widget.id)),
                );
              },
            ),
          ],
        ),
      ),
      body: ListView.builder(
        itemCount: newsList.length,
        itemBuilder: (context, index) {
          return Card(
            color: Colors.blueGrey[500],
            margin: EdgeInsets.all(10),
            elevation: 5,
            shadowColor: Colors.black,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10),
            ),
            child: Padding(
              padding: EdgeInsets.all(10),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Center(
                    child: Text(
                      newsList[index]['title'] ?? '',
                      style: TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  SizedBox(height: 10),
                  Text(
                    newsList[index]['news'] ?? '',
                    style: TextStyle(
                      fontSize: 16,
                    ),
                  ),
                ],
              ),
            ),
          );
        },
      ),
    );
  }

  Future<void> Khabara() async {
    try {
      Socket socket = await Socket.connect("192.168.1.112", 8080);
      socket.write('GET: KhabaraInfo\u0000');
      socket.flush();

      socket.listen((socketResponse) {
        setState(() {
          response = String.fromCharCodes(socketResponse);
          _parseNews(response);
        });
      });
    } catch (e) {
      print('Error connecting to socket: $e');
    }
  }

  void _parseNews(String response) {
    List<String> newsItems = response.split('.~');
    newsList = newsItems.map((item) {
      List<String> parts = item.split(':');
      if (parts.length == 2) {
        return {'title': parts[0], 'news': parts[1]};
      } else {
        return {'title': '', 'news': ''};
      }
    }).toList();
  }
}
