# JavaEpam2021-LowCost
  Сourse project EPAM Java basic course online
  
## Task
LowCost-Airline system. The client orders and pays for a ticket for a flight, taking into account the presence / absence of baggage and the right of priority check-in and boarding (the ticket price is lower than the cost of baggage). As the flight date approaches or the aircraft is full, the ticket price should increase

## Functional:
### User part:
- the list of available flights is displayed on the main page
- the client can select the flights he needs and add to the cart
- to place an order, the client must be registered and pledged
- registration is confirmed by a code that is sent to email
- at the time of ordering, the client can choose the number of tickets and baggage and the priority of registration (this affects the cost)
- paid and booked tickets are visible in the user panel

### Admin part:
 - adding, changing, deleting Airports
 - adding, changing, deleting Cities
 - adding, changing, deleting Planes
 - adding, changing, deleting Flights
 - veiw, changing status Users
 
### Business logic:
-there may be several airports in a city
- the airport has characteristics (taxes) that affect the ticket price
- the aircraft has characteristics (number of seats, flight cost) that affect the ticket price
- when creating a flight, specify: date and time of start and finish, start and finish airports, distance, baggage price and priority price
- user can book tickets
- every certain period of time, the price of free tickets is reset
   


## Technologies:
  - Maven
  - Java 8+
  - Servlet
  - JSP
  - JDBC
  - PostgreSQL
  - log4j
  - Mail
  - Junit tests
  - Tomcat
  - Google recaptcha
  
  
  


