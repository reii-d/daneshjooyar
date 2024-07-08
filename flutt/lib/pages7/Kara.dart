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
  TextEditingController taskController = TextEditingController();

  @override
  void initState() {
    super.initState();
    fetchTasks();
  }

  Future<void> fetchTasks() async {
    // Fetch tasks from backend using the widget.id
    // For example:
    // final response = await http.get('your-backend-api/tasks/${widget.id}');
    // Parse the response and setState to update the dailyTasks list
  }

  Future<void> addTask(String taskName) async {
    Task newTask = Task(
      name: taskName,
      deadline: DateTime.now().add(Duration(hours: 24)),
    );
    setState(() {
      dailyTasks.add(newTask);
    });
    // Send task details to backend
    // For example:
    // await http.post('your-backend-api/tasks/add', body: {'id': widget.id, 'task': taskName});
  }

  Future<void> deleteTask(Task task) async {
    setState(() {
      dailyTasks.remove(task);
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
            Center(
              child: Text(
                'Daily Tasks',
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold, color: Colors.white),
              ),
            ),
            Expanded(
              child: ListView(
                children: dailyTasks.map((task) => buildTaskItem(task)).toList(),
              ),
            ),
            TextField(
              controller: taskController,
              decoration: InputDecoration(
                labelText: 'New Task',
                labelStyle: TextStyle(color: Colors.white),
                enabledBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.white),
                ),
                focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.white),
                ),
              ),
              style: TextStyle(color: Colors.white),
            ),
            SizedBox(height: 10),
            ElevatedButton(
              onPressed: () async {
                if (taskController.text.isNotEmpty) {
                  await addTask(taskController.text);
                  taskController.clear();
                }
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
