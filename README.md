# AYUNTAMIENTO DE CIEMPOZUELOS

Project developed as an assignment for Web Application Development subject, Software Engineering degree. Rey Juan Carlos University 2020/2021

Development off a web application for the city council of ciempozuelos.

##  Team members

| NAME  | EMAIL | GITHUB MEMBER |
| ----- | ----- | ------------- |
| Yeray Cornejo | y.cornejo.2018@alumnos.urjc.es  | Yeray-Cornejo |
| Israel Pozuelo  | i.pozuelo.2018@alumnos.urjc.es  | Israelpzl |
| Alvaro Cano | a.canog.2018@alumnos.urjc.es  | Alvaro-Cano |
| Adrian Muñoz  | a.munozm.2018@alumnos.urjc.es | adri-m173 |
| Antonio Cuadrado  | a.cuadrado.2018@alumnos.urjc.es | cuadantonio |

##  Complementary tools

Link to Trello: https://trello.com/b/ECkQfNyt/daw-g-14

##  Important aspects of the web application

### Entities

Now we are going to explain some points of the entities:

1.  **User** : Type of user with their personal data, permissions and other interactions.
2.  **Local events** :  Different events that the town has, with their schedules and information about it.
3.  **Locals reviews** : Reviews and scores about locals of the city, written by the habitants.
4.  **Local entertainment** : Premises, information and opinions on these.


### User permissions

1.  **Administrator** : Add events and news, modify aspects of the website and user management
2.  **Habitant** : Being able to add comments and change them, manage profiles and join events.
3.  **Stranger** : Browse the website

### Images

1.  **Locals photos** 
2.  **Event photos**
3.  **User Avatar**
4.  **Government team photos**


### Graphics

1.  **Event audience charts** : Number of participants in different events
2.  **Registered users graph** : Number of registered users compared to visits

### Complementary technology

1.  **Maps**

### Algorithm or advanced query

1.  **Recommendation of related events that you have previously signed up for**

## PHASE 1: Website structure by HTML and CSS

### Main Page 
Presentation of the website:
 ![Main page](/images/index.png)


### Goberment Page 
Show all the people of the government:
 ![Goberment_Page](images/gob.jpg)


### Local Page 
Shows the different local in Ciempozuelos:
![Local](images/locales.jpg)

### Local Specification page
More information of the local:
![Local_Specification](/images/CapturaChotisPage.jpg)

### Events page 
Shows the different events in Ciempozuelos:
![Events_page](images/eventos.jpg)

### Event Specification page 
More information of the event:
![Events_Specification](images/mercadillo.jpg)

### Login page
![Login](images/inicio%20sesion.jpg)

### Profile page
Shows user information:
![Profile](images/navegacion%20perfil.jpg)

### Profile edit page 
Permit user to edit him personal information:
![Profile_edit](images/editar%20perfil.jpg)

### Navigation Diagram:
![Navigation](images/navigation.jpg)

## PHASE 2: Server web technologies

### Development direction

-  **Repository: Github/webapp14** 
-  **Development tools: Intellij, Spring Tool Suite4.**
-  **Dependencies: MySQL Workbench**
-  **To be able to execute the files included in this repository yo should follow the next steps:**

1. Open the project in Spring Tool Suite4.
2. Start a service MySQL in the local host.
3. Start a connection with de database from the ip: 127.0.0.1 in the port: 3306 with the user "root"and without password. We recomend using MySQL Workbench.
4. (RECOMENDED) In Spring, right click over the root project folder > Maven > Update Project
5. Start running the applicattion from Spring by Spring Boot App.
6. Enter from a browser to: https://localhost:8443


### Diagrams

### Navigation Diagram for navegations:
 ![Navigation](images/DiagramaDeNavegacionFase2.jpg)

### Data Base Diagram:
 ![DataBase Diagram](images/ERDiagram.png)

### Class-Template Diagram
 ![Template Diagram](images/Class_and_templates_diagram.png)
 
### Members participation

**Israel**

- Completed tasks:

- 5 most significant commits:

1. 
2. 
3.
4. 
5. 

- 5 files with participation:

1. 
2. 
3.
4. 
5.

**Álvaro**

- Completed tasks:

- 5 most significant commits:

1. 
2. 
3.
4. 
5.

- 5 files with participation:

1. 
2. 
3.
4. 
5.

**Yeray**

- Completed tasks:

- 5 most significant commits:

1. 
2. 
3.
4. 
5.

- 5 files with participation:

1. 
2. 
3.
4. 
5.

**Cuadrado**

- Completed tasks: My work in this phase was mainly to create the AJAX system on the Events and Locals page. I also worked on a badge system that updates when you either comment on an event or an store and when you subscribe to an event, and another really important part of my job was to create a history system that puts on your profule page where you have commented or to which events you have subscribed. Also, I have worked with all my teammates on different tasks which everyday someone different commited it.

- 5 most significant commits:

1. [AJAX created](https://github.com/CodeURJC-DAW-2020-21/webapp14/commit/f3d8083b2a198238f01abad557a10bf6cd8217c6)
2. [Infinite loop fixed](https://github.com/CodeURJC-DAW-2020-21/webapp14/commit/4d81eef6f8ee3d812748c0ba12ce3945835e3782)
3. [History made](https://github.com/CodeURJC-DAW-2020-21/webapp14/commit/79e438a6f98611bcae923e474b285bb06f3685a5)
4. [Badge system created](https://github.com/CodeURJC-DAW-2020-21/webapp14/commit/5c654f383c98fa6ea29e494172194245406152b9)
5. [Register made](https://github.com/CodeURJC-DAW-2020-21/webapp14/commit/0e3e61ddd94bc91ad8ca46713a17c984803d11bf)

- 5 files with participation:

1. AppController.java
2. ajaxevents.js
3. DataBaseInitialization.java
4. User.java
5. events.html

**Adri**

- Completed tasks:

- 5 most significant commits:

1. 
2. 
3.
4. 
5.

- 5 files with participation:

1. 
2. 
3.
4. 
5.


