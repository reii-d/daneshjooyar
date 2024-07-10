import 'package:flutter/material.dart';
import 'package:test1/pages/login_page.dart';
import 'package:test1/pages/signup_page.dart';
import 'package:test1/pages/welcome_page.dart';
import 'package:test1/pages7/Classa.dart';
import 'package:test1/pages7/Sara.dart';

import 'pages7/Tamrina.dart';

void main() {
  runApp(const MyApp());
}
class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: welcome_page()
    );
  }
}
