import 'package:flutter/material.dart';
import 'package:test1/pages7/Classa.dart';
import 'package:test1/pages7/Khabara.dart';
import 'package:test1/pages7/Tamrina.dart';
import '../pages/profile.dart';
import 'Sara.dart';

class Kara extends StatefulWidget {
  final String id;

  Kara({required this.id});

  @override
  _KaraState createState() => _KaraState();
}

class _KaraState extends State<Kara> {
  List<Task> dailyTasks = [];
  List<Task> futureTasks = [];

  @override
  void initState() {
    super.initState();
    fetchTasks();
  }

  Future<void> fetchTasks() async {
    // Fetch tasks from backend using the widget.id
    // For example:
    // final response = await http.get('your-backend-api/tasks/${widget.id}');
    // Parse the response and setState to update the dailyTasks and futureTasks lists
  }

  Future<void> addTask(Task task) async {
    setState(() {
      dailyTasks.add(task);
    });
    // Send task details to backend
    // For example:
    // await http.post('your-backend-api/tasks/add', body: {'id': widget.id, 'task': task.name});
  }

  Future<void> deleteTask(Task task) async {
    setState(() {
      if (dailyTasks.contains(task)) {
        dailyTasks.remove(task);
      } else {
        futureTasks.remove(task);
      }
    });
    // Send task details to backend
    // For example:
    // await http.post('your-backend-api/tasks/delete', body: {'id': widget.id, 'task': task.name});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blueGrey[400],
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          "Kara",
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
                  MaterialPageRoute(
                    builder: (context) => Sara(Id: "402243108"),
                  ),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.person),
              title: Text('Profile'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => StudentInfoPage(studentid: "john_doe"),
                  ),
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
                  MaterialPageRoute(builder: (context) => Classa(id: "40")),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.newspaper_rounded),
              title: Text('Khabara'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Khabara()),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.home_work),
              title: Text('Tamrina'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Tamrina(id: "40")),
                );
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
            Text(
              'Daily Tasks',
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold, color: Colors.white),
            ),
            Expanded(
              child: ListView(
                children: dailyTasks.map((task) => buildTaskItem(task)).toList(),
              ),
            ),
            Text(
              'Future Tasks',
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold, color: Colors.white),
            ),
            Expanded(
              child: ListView(
                children: futureTasks.map((task) => buildTaskItem(task)).toList(),
              ),
            ),
            ElevatedButton(
              onPressed: () async {
                // Add a new task (for example purposes)
                Task newTask = Task(
                  name: 'New Task',
                  deadline: DateTime.now().add(Duration(hours: 24)),
                );
                await addTask(newTask);
              },
              child: Text('Add Task'),
            ),
          ],
        ),
      ),
    );
  }

  Widget buildTaskItem(Task task) {
    return Card(
      color: Colors.blueGrey[500],
      child: ListTile(
        title: Text(
          task.name,
          style: TextStyle(
            color: Colors.white,
            decoration: task.isCompleted ? TextDecoration.lineThrough : TextDecoration.none,
          ),
        ),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            IconButton(
              icon: Icon(Icons.check, color: Colors.green),
              onPressed: () {
                setState(() {
                  task.isCompleted = true;
                });
              },
            ),
            IconButton(
              icon: Icon(Icons.delete, color: Colors.red),
              onPressed: () async {
                await deleteTask(task);
              },
            ),
          ],
        ),
        onTap: () {
          showDialog(
            context: context,
            builder: (context) => AlertDialog(
              title: Text('Remaining Time'),
              content: Text(
                'Time remaining: ${task.deadline.difference(DateTime.now()).inHours} hours and ${task.deadline.difference(DateTime.now()).inMinutes % 60} minutes',
              ),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: Text('OK'),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}

class Task {
  String name;
  DateTime deadline;
  bool isCompleted;

  Task({required this.name, required this.deadline, this.isCompleted = false});
}
