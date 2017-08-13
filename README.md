RoundRobinMailer
================

RoundRobinMailer will mail one at a time giving opportunity to everyone at a time to Collaborate.

Configuration
=============
In RoundRobinMailer.java, replace the following values,
1. XXXXXX with User Name of SMTP User
2. YYYYYY with Password of SMTP User
3. ZZZZZZ with SMTP Host Name
4. XXXXXXEMAIL with email address of Login

How to Run?
===========
javac -cp lib/*; com/naren/mailer/RoundRobinMailer.java
java -cp lib/*; com/naren/mailer/RoundRobinMailer

jar cvfe RoundRobinMailer.jar com/naren/mailer/RoundRobinMailer com/naren/mailer/RoundRobinMailer.class
