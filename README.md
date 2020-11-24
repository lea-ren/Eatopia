# Eatopia
A place to view and share healthy &amp; quick recipe

### Project Abstract
An increasing number of urban young people adopted an unhealthy lifestyle. In detail, they eat unhealthy food, stay up late, and undertake overmuch work. Based on thorough research on target users, the developing team find out the pursuit of convenience contribute to their eating habits. Young people are reluctant to shop, wash dishes, search for menus and need encouragement. This project aimed to provide customized weekly menus. Users are encouraged to do more dishes by themselves to share pictures and flag dishes they have done in the APP. Developers also spent efforts to protect privacy by building a comprehensive login system, ensuring the password is invisible for developers. The APP is based on Java and developed through Android Studio with coherent logic and an online database.

### Introduction
Now many Chinese young people live alone and have an unhealthy dietary habit based on our user research. They always order takeaway food and eat at the restaurant because of lack for one-person menu, inconvenience to cook and laziness to decide what to eat. There are various online menu APPs such as Go to the Kitchen (下厨房) focus on older people and lack of one-person menus. Besides, people take too much time on selecting dishes. Therefore, we use plan-driven processes to develop the EATOPIA APP mainly for solving 18-30-year-old young people cook pain points. The APP is aimed at encouraging people to cook instead of ordering takeaway food, which exerts the health burden on young citizens. It can give a personalized recommendation on one-week three-meal menus based on the personal information. Besides, it has social functions that users can share their notes and view other notes about cooking and eating. It is suitable for young people who are lazy to consider what to eat and how to do dishes for only one person. By using this app, people could gradually develop a healthy habit in eating results from the convenience and social mechanism.

### System Description
The APP combines one-person one-week three-meal menus recommendation and social contact as two main functions. In the menu recommendation part, it provides personalized menus based on personal information. Users can edit their personal information containing profile picture, username and preference (spicy food, weight loss and cook level). The dish menu details including ingredients, kitchenware, procedure and cook level. And users can flag the dish as finished. In the social contact part, it provides notes share and view functions. Users can also view their previous notes and the number of finished dishes.

### System Design
In the system design, we do architecture design, prototype design, interface design and database design. For architecture design, we use an activity diagram to identify the overall structure of the system, their relationships and how they are distributed. For prototype design, we draw prototypes to help with requirements elicitation and validation. For interface design, we define the user interface style. For database design, we design the system data structures and representation in a database.

### Architecture Design
The app consists of four main modules, which are menu, share, moments and me modules. Below is the activity diagram of the app.

### Prototype Design
There are six interfaces apart from login and register interfaces. Users could switch between different module pages at the bottom of the app. In the menu module, users could view the menu plan suggested by system, specific menu details and mark the dish as finished. In the share module, users could share their notes about cooking and other related topics. In the moments' module, users can view the notes of all users in chronological order. In the personal center module, the page gives an overview of diet preference, spicy preference, finished dishes amount and user notes. And users can edit personal information. Below are the prototypes.

### Interface Design
The app UI style has two characteristics on account of target users and good usability. One is that interface is simple, elegant and clearly organized. The other is that design elements emphasize minimalism and remove redundant decoration to highlight content such as text and pictures of the APP.
For text, we apply two types of font size in convenience for different parts. Most text are 20sp in font size, including recipe title, username, page header, and so on. The text for the detailed recipe is in a smaller font size of 15sp, because the text is dense. The text of ingredients, kitchenware, cooking steps here are all applied 15sp of font size.
For color, the main color is designed to be warm and vibrant orange. Since this kind of color is warmer and more appetizing. The color we used is shown below.
For icon, we use symbolized icons to represent four modules at the navigation bar at the bottom of the APP. And we use orange color and navy color to represent different states.

### Database Design
There are 4 tables in the database. _User is for users’ information. Testmenu is for menu information. CookedDishes is for recording the dishes user cooked. Notes are for recording the notes users posted. The design of the database schema is shown below.



