import 'dart:async';
import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:test1/pages/profile.dart';
import 'package:test1/pages7/Classa.dart';
import 'package:test1/pages7/Kara.dart';
import 'package:test1/pages7/Khabara.dart';
import 'package:test1/pages7/Sara.dart';

class Tamrina extends StatefulWidget {
  String id;

  Tamrina({required this.id});

  @override
  _TamrinaState createState() => _TamrinaState();
}

class _TamrinaState extends State<Tamrina> {
  List<Map<String, dynamic>> lessons = [];

  @override
  void initState() {
    super.initState();
    TamrinaInfo();
  }

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
                Navigator.push(context, MaterialPageRoute(builder: (context) => Sara(Id: widget.id)));
              },
            ),
            ListTile(
              leading: Icon(Icons.person),
              title: Text('Profile'),
              onTap: () {
                Navigator.push(
                    context, MaterialPageRoute(builder: (context) => StudentInfoPage(studentid: widget.id)));
              },
            ),
            ListTile(
              leading: Icon(Icons.calendar_month),
              title: Text('Kara'),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) => Kara(id:widget.id)));
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
                Navigator.push(context, MaterialPageRoute(builder: (context) => Khabara()));
              },
            ),
            ListTile(
              leading: Icon(Icons.home_work),
              title: Text('Tamrina'),
              onTap: () {
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
              onTapAssignment: (assignment) {
                showDialog(
                  context: context,
                  builder: (context) => AlertDialog(
                    title: Text(assignment['title']),
                    content: Text('Due in ${assignment['daysLeft']} days'),
                    actions: [
                      TextButton(
                        onPressed: () => Navigator.pop(context),
                        child: Text('OK'),
                      ),
                      TextButton(
                        onPressed: () async {
                          DateTime? newDueDate = await showDatePicker(
                            context: context,
                            initialDate: DateTime.now().add(Duration(days: assignment['daysLeft'])),
                            firstDate: DateTime.now(),
                            lastDate: DateTime.now().add(Duration(days: 365)),
                          );
                          if (newDueDate != null) {
                            setState(() {
                              Duration difference = newDueDate.difference(DateTime.now());
                              assignment['daysLeft'] = difference.inDays;
                            });
                          }
                          Navigator.pop(context);
                        },
                        child: Text('Change Due Date'),
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

  Future<void> TamrinaInfo() async {
    try {
      Socket socket = await Socket.connect("192.168.1.112", 8080);

      // Sending request for TamrinaInfo
      socket.write('GET: TamrinaInfo,${widget.id}\u0000');
      await socket.flush();

      List<int> dataBuffer = [];
      await socket.listen((List<int> data) {
        dataBuffer.addAll(data);
      }).asFuture();

      String response = utf8.decode(dataBuffer).trim();
      print('Response received: $response');

      List<Map<String, dynamic>> lessonList = [];
      List<String> lessonsData = response.split(';');
      for (String lessonData in lessonsData) {
        List<String> parts = lessonData.split(',');
        String lessonTitle = parts[0];
        List<Map<String, dynamic>> assignments = [];
        for (int i = 1; i < parts.length; i += 2) {
          if (i + 1 < parts.length) {
            String assignmentTitle = parts[i];
            int daysLeft = int.parse(parts[i + 1]);
            assignments.add({
              'title': assignmentTitle,
              'daysLeft': daysLeft,
              'dueDate': DateTime.now().add(Duration(days: daysLeft)),
            });
          }
        }
        lessonList.add({
          'title': lessonTitle,
          'assignments': assignments,
        });
      }

      setState(() {
        lessons = lessonList;
      });

      socket.close();
    } catch (e) {
      print('Error: $e');
    }
  }
}

class LessonCard extends StatelessWidget {
  final Map<String, dynamic> lesson;
  final Function(Map<String, dynamic>) onTapAssignment;

  const LessonCard({
    required this.lesson,
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
              lesson['title'],
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
                color: Colors.black,
              ),
            ),
            ...lesson['assignments'].map<Widget>((assignment) {
              return AssignmentTile(
                assignment: assignment,
                onTap: () => onTapAssignment(assignment),
              );
            }).toList(),
          ],
        ),
      ),
    );
  }
}

class AssignmentTile extends StatefulWidget {
  final Map<String, dynamic> assignment;
  final VoidCallback onTap;

  const AssignmentTile({
    required this.assignment,
    required this.onTap,
  });

  @override
  _AssignmentTileState createState() => _AssignmentTileState();
}

class _AssignmentTileState extends State<AssignmentTile> {
  late DateTime dueDate;
  late Duration timeLeft;
  Timer? timer;

  @override
  void initState() {
    super.initState();
    dueDate = widget.assignment['dueDate'];
    updateTimeLeft();
    timer = Timer.periodic(Duration(seconds: 1), (timer) {
      updateTimeLeft();
    });
  }

  void updateTimeLeft() {
    setState(() {
      timeLeft = dueDate.difference(DateTime.now());
    });
  }

  @override
  void dispose() {
    timer?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(
        widget.assignment['title'],
        style: TextStyle(color: Colors.black),
      ),
      subtitle: Text(
        'Time Left: ${timeLeft.inDays} days, ${timeLeft.inHours % 24} hours, ${timeLeft.inMinutes % 60} minutes',
        style: TextStyle(color: Colors.green),
      ),
      onTap: widget.onTap,
    );
  }
}
