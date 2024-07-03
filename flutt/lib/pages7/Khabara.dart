import 'package:flutter/material.dart';
import 'package:test1/pages7/Classa.dart';
import 'package:test1/pages7/Tamrina.dart';
import '../pages/profile.dart';
import 'Sara.dart';

class Khabara extends StatefulWidget {
  @override
  _KhabaraState createState() => _KhabaraState();
}

class _KhabaraState extends State<Khabara> {
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
                Navigator.push(
                    context, MaterialPageRoute(builder: (context) => Sara()));
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
                          username: "john_doe",
                          studentid: 12345678,
                        )
                    ));
              },
            ),
            ListTile(
              leading: Icon(Icons.calendar_month),
              title: Text('Kara'),
              onTap: () {
                // Navigate to Settings Page
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: Icon(Icons.hotel_class_sharp),
              title: Text('Classea'),
              onTap: () {
                // Navigate to Next Term Classes Page
                Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => NextTermClassesPage()),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.newspaper_rounded),
              title: Text('Khabara'),
              onTap: () {
                // Navigate to Contact Page
                Navigator.pop(context);
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
    );
  }
}
