import 'package:flutter/material.dart';
import 'package:test1/pages/admin_login.dart';
import 'package:test1/pages/login_page.dart';
import 'package:test1/pages/signup_page.dart';
import 'package:test1/pages/teacher_login.dart';

class login_choice extends StatelessWidget {
  login_choice({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blueGrey[400],
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          "Choose your role",
          style: TextStyle(
            color: Colors.white,
            fontSize: 28,
            fontWeight: FontWeight.bold,
          ),
        ),
        centerTitle: true,
      ),
      body: Center(
        child: Container(
          height: 700,
          width: 380,
          decoration: BoxDecoration(
            color: Colors.blueGrey[500],
            borderRadius: BorderRadius.circular(50),
            boxShadow: [
              BoxShadow(
                color: Colors.black.withOpacity(0.5),
                spreadRadius: 5,
                blurRadius: 7,
                offset: Offset(0, 3), // changes position of shadow
              ),
            ],
          ),
          child: Center(
            child: Container(
              height: 400,
              width: 300,
              decoration: BoxDecoration(
                color: Colors.blueGrey[600],
                borderRadius: BorderRadius.circular(50),
                boxShadow: [
                  BoxShadow(
                    color: Colors.black.withOpacity(0.4),
                    spreadRadius: 5,
                    blurRadius: 7,
                    offset: Offset(0, 3), // changes position of shadow
                  ),
                ],
              ),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Text(
                    "As a :",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                  SizedBox(height: 20),
                  SizedBox(
                    width: 200,
                    height: 50,
                    child: ElevatedButton(
                      child: Text(
                        "Student",
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 25,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => LoginPage(),
                          ),
                        );
                      },
                    ),
                  ),
                  SizedBox(height: 20),
                  SizedBox(
                    width: 200,
                    height: 50,
                    child: ElevatedButton(
                      child: Text(
                        "Teacher",
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 27,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => teacher_login(),
                          ),
                        );
                      },
                    ),
                  ),
                  SizedBox(height: 20),
                  SizedBox(
                    width: 200,
                    height: 50,
                    child: ElevatedButton(
                      child: Text(
                        "Admin",
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 28,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => admin_login(),
                          ),
                        );
                      },
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
