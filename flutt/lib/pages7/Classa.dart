import 'package:flutter/material.dart';
import 'package:test1/pages/profile.dart';
import 'package:test1/pages7/Kara.dart';
import 'package:test1/pages7/Khabara.dart';
import 'package:test1/pages7/Sara.dart';
import 'package:test1/pages7/Tamrina.dart';

class NextTermClassesPage extends StatelessWidget {
  NextTermClassesPage({super.key});

  final List<Map<String, String>> classes = [
    {
      'className': 'Mathematics',
      'classId': 'MATH101',
      'teacherName': 'Mr. Smith',
    },
    {
      'className': 'History',
      'classId': 'HIST201',
      'teacherName': 'Ms. Johnson',
    },
    {
      'className': 'Physics',
      'classId': 'PHYS301',
      'teacherName': 'Dr. Brown',
    },
  ];

  final TextEditingController classIdController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blueGrey[400],
      appBar: AppBar(
          backgroundColor: Colors.blue,
        title: Text(
          "Classa",
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
                      username: "john_doe",
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
                Navigator.pop(context);
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
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Expanded(
              child: ListView.builder(
                itemCount: classes.length,
                itemBuilder: (context, index) {
                  final classInfo = classes[index];
                  return Card(
                    margin: const EdgeInsets.symmetric(vertical: 8.0),
                    color: Colors.blueGrey[500],
                    child: Container(
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
                      child: ListTile(
                        title: Text(
                          classInfo['className']!,
                          style: TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                            color: Colors.black,
                          ),
                        ),
                        subtitle: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              'Class ID: ${classInfo['classId']}',
                              style: TextStyle(color: Colors.white),
                            ),
                            Text(
                              'Teacher: ${classInfo['teacherName']}',
                              style: TextStyle(color: Colors.white),
                            ),
                          ],
                        ),
                      ),
                    ),
                  );
                },
              ),
            ),
            SizedBox(height: 20),
            TextField(
              controller: classIdController,
              decoration: InputDecoration(
                labelText: 'Class ID',
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 10),
            ElevatedButton(
              onPressed: () {
                // Implement class addition logic here
                final newClassId = classIdController.text;
                // Call backend to add the class using newClassId
                // Update the classes list with new class details
              },
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.green, // Button color
                foregroundColor: Colors.black, // Text color
              ),
              child: Text('Add Class'),
            ),
          ],
        ),
      ),
    );
  }
}
