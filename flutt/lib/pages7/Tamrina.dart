import 'package:flutter/material.dart';
import 'package:test1/pages/profile.dart';
import 'package:test1/pages7/Classa.dart';
import 'package:test1/pages7/Kara.dart';
import 'package:test1/pages7/Khabara.dart';
import 'package:test1/pages7/Sara.dart';

class Tamrina extends StatefulWidget {
  @override
  _TamrinaState createState() => _TamrinaState();
}

class _TamrinaState extends State<Tamrina> {
  List<Lesson> lessons = [
    Lesson(
      title: 'Mathematics',
      assignments: [
        Assignment(
          title: 'Algebra Homework',
          deadline: DateTime.now().add(Duration(hours: 4)),
          details: 'Complete all exercises on page 32.',
        ),
        Assignment(
          title: 'Geometry Assignment',
          deadline: DateTime.now().add(Duration(days: 1)),
          details: 'Draw and label all the geometric shapes.',
        ),
      ],
    ),
    Lesson(
      title: 'History',
      assignments: [
        Assignment(
          title: 'World War II Essay',
          deadline: DateTime.now().add(Duration(hours: 6)),
          details: 'Write a 2-page essay about World War II.',
        ),
      ],
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blueGrey[400],
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          "Tamrina",
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
                  padding:  EdgeInsets.only(left: 16.0),
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
                Navigator.push(context, MaterialPageRoute(builder: (context) => Sara(Id: "40243108",)));;
              },
            ),
            ListTile(
              leading: Icon(Icons.person),
              title: Text('Profile'),
              onTap: () {
                // Navigate to Profile Page
                Navigator.push(context, MaterialPageRoute(builder: (context) => StudentInfoPage(studentid: "john_doe",)
                ));
              },
            ),
            ListTile(
              leading: Icon(Icons.calendar_month),
              title: Text('Kara'),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) => Kara()));
              },
            ),
            ListTile(
              leading: Icon(Icons.hotel_class_sharp),
              title: Text('Classea'),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) => Classa(id: "40")));
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
                Navigator.pop(context);
              },
            ),
          ],
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: ListView.builder(
          itemCount: lessons.length,
          itemBuilder: (context, index) {
            final lesson = lessons[index];
            return LessonCard(
              lesson: lesson,
              onDeadlineChanged: (assignment, newDeadline) {
                setState(() {
                  assignment.deadline = newDeadline;
                });
              },
              onTapAssignment: (assignment) {
                showDialog(
                  context: context,
                  builder: (context) => AlertDialog(
                    title: Text(assignment.title),
                    content: Text(assignment.details),
                    actions: [
                      TextButton(
                        onPressed: () => Navigator.pop(context),
                        child: Text('OK'),
                      ),
                    ],
                  ),
                );
              },
            );
          },
        ),
      ),
    );
  }
}

class LessonCard extends StatelessWidget {
  final Lesson lesson;
  final Function(Assignment, DateTime) onDeadlineChanged;
  final Function(Assignment) onTapAssignment;

  const LessonCard({
    required this.lesson,
    required this.onDeadlineChanged,
    required this.onTapAssignment,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.blueGrey[500],
      margin: const EdgeInsets.symmetric(vertical: 8.0),
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              lesson.title,
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
                color: Colors.black,
              ),
            ),
            ...lesson.assignments.map((assignment) {
              final remainingTime = assignment.deadline.difference(DateTime.now());
              return ListTile(
                title: Text(
                  assignment.title,
                  style: TextStyle(color: Colors.black),
                ),
                subtitle: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Remaining Time: ${remainingTime.inHours} hours and ${remainingTime.inMinutes % 60} minutes',
                      style: TextStyle(color: Colors.white),
                    ),
                    TextButton(
                      onPressed: () async {
                        final newDeadline = await showDatePicker(
                          context: context,
                          initialDate: assignment.deadline,
                          firstDate: DateTime.now(),
                          lastDate: DateTime(2100),
                        );
                        if (newDeadline != null) {
                          onDeadlineChanged(assignment, newDeadline);
                        }
                      },
                      child: Text(
                        'Change Deadline',
                        style: TextStyle(color: Colors.green),
                      ),
                    ),
                  ],
                ),
                onTap: () => onTapAssignment(assignment),
              );
            }).toList(),
          ],
        ),
      ),
    );
  }
  // Future<void> TamrinaInfo() async {
  //   Socket socket = await Socket.connect("192.168.1.112", 8080);
  //
  //   // Sending request for SaraInfo
  //   socket.write('GET: SaraInfo,${widget.Id}\u0000');
  //   socket.flush();
  //
  // }
}

class Lesson {
  String title;
  List<Assignment> assignments;

  Lesson({required this.title, required this.assignments});
}

class Assignment {
  String title;
  DateTime deadline;
  String details;

  Assignment({
    required this.title,
    required this.deadline,
    required this.details,
  });
}




