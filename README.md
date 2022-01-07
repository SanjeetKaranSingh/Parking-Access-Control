# Parking-Access-Control

• An Access Control system model for a company parking, where every user must tap their access card on RFID card, embedded in Raspberry pi, and python running on raspberry pi using pyobdc connects to MS SQL server (running locally), and check if user is authorized and open gate for him<br />
• It also counts number of vehicles, and check it with parking limit provided by administrator, and if it exceeds will not open gate, unless someone exits.<br />
•	Developed another J2EE website, that display number of available parking spaces to users.<br />
•	J2EE <a href="https://github.com/SanjeetKaranSingh/Parking-Access-Control/tree/main/Eclipse%20WebSite%20Source%20Code/TPJ_FinalProject">webpage</a> provide Administrator settings to set parking limit, view parking entry logs as well as turning on night lights and Emergency mode.<br />
•	Website is using jspx pages and servlets and filters to process backend, talking with DB through JDBC.<br />
•	Website is taking to raspberry pi through <a href="https://github.com/SanjeetKaranSingh/Parking-Access-Control/tree/main/Rest%20API%20code%20that%20was%20in%20public_html">Rest API</a>, that is written in python and configured on apache2 server.<br />
Other Contributor: Arshdeep Singh Cheema
