DELETE FROM users;
DELETE FROM roles;
DELETE FROM cars;
DELETE FROM repairs;
DELETE FROM parts;
INSERT INTO `users` VALUES (1,'a@email.com','aaaaaa','2018-01-01 12:00:00','2018-01-01 12:00:00'),(2,'b@email.com','bbbbbb','2018-01-01 12:00:00','2018-01-01 12:00:00'),(3,'c@email.com','cccccc','2018-01-01 12:00:00','2018-01-01 12:00:00'),(4,'d@email.com','dddddd','2018-01-01 12:00:00','2018-01-01 12:00:00'),(5,'e@email.com','eeeeee','2018-01-01 12:00:00','2018-01-01 12:00:00'),(6,'f@email.com','ffffff','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `roles` VALUES (1,1,'a@email.com','Admin'),(2,2,'b@email.com','User'),(3,3,'c@email.com','User'),(4,4,'d@email.com','User'),(5,5,'e@email.com','User'),(6,6,'f@email.com','User');
INSERT INTO `cars` VALUES (1,2,2016,'Jeep','Wrangler','1C4BJWFG2GL133333','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `repairs` VALUES (1,1,'2018-01-01','123-456','Midas',300,45000,'1 year labor','Exhaust','Replaced muffler and tailpipe','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `repairs` VALUES (2,1,'2018-01-01','123-456','CarEx',400,55000,'5 years parts and labor','Brakes','Replaced rotors and pads','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `repairs` VALUES (3,1,'2018-01-01','123-456','Tires Plus',900,65000,'36000 mile and road side service','Tires','Replaced 4 tires and balance','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `repairs` VALUES (4,1,'2018-01-01','123-456','AutoPlus',200,75000,'5 years replacement prorated','Battery','Replaced battery','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `parts` VALUES (1,1,'Muffler','Flowmaster','123-444','Limited lifetime','Autozone',175.00,'Gold Premium','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `parts` VALUES (2,1,'Clamps pads','Flowmaster Clamps','123-445','Limited lifetime','Autozone',15.00,'Gold Premium','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `parts` VALUES (3,1,'Tailpipe','Flowmaster Tailpipe','123-446','Limited lifetime','Autozone',75.00,'Gold Premium','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `parts` VALUES (4,1,'Brakes pads','Raybestos','123-777','3 year replacement','Pads',100.00,'Gold Premium','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `parts` VALUES (5,2,'Brakes rotors','Raybestos','123-777','3 year replacement','Rotors',300.00,'Gold Premium','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `parts` VALUES (6,3,'Tires','Goodyear','123-111','5 year/50,000 miles','Wranglers',900.00,' ATX 225-70-16','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `parts` VALUES (7,3,'Valves and Stems','Goodyear','123-111','Lifetime','Longneck',9.00,'','2018-01-01 12:00:00','2018-01-01 12:00:00');
INSERT INTO `parts` VALUES (8,4,'Battery','Diehard','123-1111','5 years replacement prorated','Autoplus',200.00,'Long Life','2018-01-01 12:00:00','2018-01-01 12:00:00');




