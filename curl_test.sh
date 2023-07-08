#!/bin/bash

rm nanosoftmedicaldb.mv.db

curl -X POST -H "Content-Type: application/json;" -d '{"name":"Psyke","email":"psdady@msn.com","pskH":"toor","user":"USER"}' http://localhost:8080/nanosoftmedical/register 

curl -X POST -H "Content-Type: application/json;" -d '{"name":"Dottore","email":"test@testdoctor.com","pskH":"toor","user":"DOCTOR","doctorType":"ANAESTHETICS"}' http://localhost:8080/nanosoftmedical/register

curl -X GET -u "psdady@msn.com:toor" http://localhost:8080/nanosoftmedical/login

curl -X GET -u "test@testdoctor.com:toor" http://localhost:8080/nanosoftmedical/login

curl -X GET -u "psdady@msn.com:toor" http://localhost:8080/nanosoftmedical/doctor/list

curl -X GET -u "psdady@msn.com:toor" http://localhost:8080/nanosoftmedical/doctor/search/anaesthetics

curl -X GET -u "test@testdoctor.com:toor" http://localhost:8080/nanosoftmedical/login

curl -X POST -H "Content-Type:application/json;" -u "psdady@msn.com:toor" -d '{"d":{"email":"test@testdoctor.com", "specialty":"ANAESTHETICS"},"l":"2023-12-21T09:00"}' http://localhost:8080/nanosoftmedical/user/newappointment

curl -X GET -u "psdady@msn.com:toor" http://localhost:8080/nanosoftmedical/user/myappointments

curl -X GET -u "test@testdoctor.com:toor" http://localhost:8080/nanosoftmedical/user/myappointments
