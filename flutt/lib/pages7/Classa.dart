import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:test1/pages/profile.dart';
import 'package:test1/pages7/Kara.dart';
import 'package:test1/pages7/Khabara.dart';
import 'package:test1/pages7/Sara.dart';
import 'package:test1/pages7/Tamrina.dart';

class Classa extends StatefulWidget {
  final String id;

  Classa({Key? key, required this.id}) : super(key: key);

  @override
  _ClassaState createState() => _ClassaState();
}

class _ClassaState extends State<Classa> {
  final TextEditingController classIdController = TextEditingController();
  List<Map<String, String>> classes = [];
  String _error = '';

  @override
  void initState() {
    super.initState();
    classa();
  }

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
                    builder: (context) => Sara(id: widget.id),
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
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: Icon(Icons.newspaper_rounded),
              title: Text('Khabara'),
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => Khabara(id: widget.id)));
              },
            ),
            ListTile(
              leading: Icon(Icons.home_work),
              title: Text('Tamrina'),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => Tamrina(id: widget.id,)));
              },
            ),
          ],
        ),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: [
            Expanded(
              child: ListView.builder(
                itemCount: classes.length,
                itemBuilder: (context, index) {
                  final classInfo = classes[index];
                  return buildClassCard(
                    classInfo['className']!,
                    classInfo['classId']!,
                    classInfo['teacherName']!,
                  );
                },
              ),
            ),
            SizedBox(height: 20),
            TextField(
              controller: classIdController,
              decoration: InputDecoration(
                labelText: 'Class ID',
                border: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.white),
                ),
              ),
            ),
            SizedBox(height: 10),
            ElevatedButton(
              onPressed: () async {
                final newClassId = classIdController.text;
                await addClass(newClassId);
              },
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.white, // Button color
                foregroundColor: Colors.black, // Text color
              ),
              child: Text('Add Class'),
            ),
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

  Widget buildClassCard(String className, String classId, String teacherName) {
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
            className,
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
                'Class ID: $classId',
                style: TextStyle(color: Colors.white),
              ),
              Text(
                'Teacher: $teacherName',
                style: TextStyle(color: Colors.white),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Future<void> classa() async {
    try {
      Socket socket = await Socket.connect("192.168.1.112", 8080);

      // Sending request for classes
      socket.write('GET: Classes,${widget.id}\u0000');
      await socket.flush();

      List<int> dataBuffer = [];
      // Listening for the response
      await socket.listen((List<int> data) {
        dataBuffer.addAll(data);
      }).asFuture();

      String response = utf8.decode(dataBuffer).trim();
      print('Response received: $response');

      // Split the response into chunks of 3 parts each
      List<String> parts = response.split(',');
      List<Map<String, String>> classList = [];

      for (int i = 0; i < parts.length; i += 3) {
        if (i + 2 < parts.length) {
          classList.add({
            'className': parts[i],
            'classId': parts[i + 1],
            'teacherName': parts[i + 2],
          });
        }
      }

      setState(() {
        classes = classList;
        _error = '';
      });

      socket.close();
    } catch (e) {
      setState(() {
        _error = 'Error: $e';
      });
    }
  }

  Future<void> addClass(String newClassId) async {
    try {
      Socket socket = await Socket.connect("192.168.1.112", 8080);

      // Sending request to add class
      socket.write('GET: AddClassa,${widget.id},$newClassId\u0000');
      await socket.flush();

      Navigator.push(context,
          MaterialPageRoute(builder: (context) => Classa(id: widget.id)));
    }catch (e){
      setState(() {
        _error = 'Error: $e';
      });
  }
}

}
