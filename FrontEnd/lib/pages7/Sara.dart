import 'package:flutter/material.dart';
import '../pages/profile.dart';

class HomePage extends StatelessWidget {
  HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    // Dummy data
    final bestScore = 95;
    final worstScore = 60;
    final unfinishedHomework = ["Math Assignment", "Science Project"];//az backend miyad
    final futureWorks = ["History Essay", "Programming Assignment"];//az backend miyad

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
                  builder: (context) => StudentInfoPage(
                    name: "John Doe",
                    gpa: 3.75,
                    username: "john_doe",
                      studentid: 12345678,
                  ),
                ),
              );
            },
          ),
        ],
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(25.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            buildInfoCard("Best Score", bestScore.toString()),
            SizedBox(height: 20),
            buildInfoCard("Worst Score", worstScore.toString()),
            SizedBox(height: 20),
            buildInfoCard("Unfinished Homework", unfinishedHomework.join('\n- ')),
            SizedBox(height: 20),
            buildInfoCard("Future Works", futureWorks.join('\n- ')),
          ],
        ),
      ),
    );
  }

  Widget buildInfoCard(String title, String content) {
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

