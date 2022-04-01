##  TO-DO LIST CREATOR


A simple web application that allows you to create to-do items and folders to group them. 


##  REQUERIMENTS

** Technologies used **

* Front-end: React 17.0.2
* Back-end: MySQL 8.0.16, Spring Boot 2.5.3

**Ports available**

* 4200 : front-end server
* 8080: back-end server
* 3306: mysql server


## SETUP

**Get the repository / Clone**

	```bash
	git clone https://github.com/tachi123/to-do_list_creator.git
	```

**Initialize backend**

	```bash
	cd creator-backend
	mvn spring-boot:run
	```

	The server will start on port 8080


**Initialize frontend**


	```bash
	cd creator-frontend
	npm install && npm start
	```

	The server will start on port 4200
	

## CONFIGURATION

**LOGIN - Current credentials**
   
   current user: dev
   current password: dummy  
	
**MySQL - Current credentials**


**MySQL - Change credentials**


	Inside `src/main/resources/application.yml` change `username` and `password` properties		  

