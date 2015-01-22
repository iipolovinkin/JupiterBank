Web application which perform the functions of a bank. The Bank named Jupiter Bank.
Web application should keep information about
  * clients(id, name, address, age),
  * accounts(id, owner, amount),
  * transactions(id, sender, reciever, amount, time) between accounts.

It provides the following functions:
1.1 Create new client
1.2 Add new empty account to client
1.3 show client information and his accounts
1.4 Show all clients

2.1 Show accounts
2.2 Transfer amount from one account to another
2.3 Refill account(increase amount)
2.4 Debit account(decrease amount)

3.1 Show all transactions
3.1 Filter transaction list
and so on

How to run it?
Requirments: maven 3, git, internet connection(download libraries).
clone, compile & run , open
- git clone https://github.com/iipolovinkin/JupiterBank.git
- mvn clean compile jetty:run
- url: http://127.0.0.1:8081/

Some used libraries.
java 1.7+
SpringMVC 3.2+
Hibernate 4.+
SLF4J 1.6+
JUnit 4.10+
Apache tiles 3.+
Bootstrap 3.+
Mockito 1.10.+
