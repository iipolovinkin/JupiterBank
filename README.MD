Web application which performs the functions of a bank. The Bank named Jupiter Bank.
Web application should keep information about
  * clients(id, name, address, age),
  * accounts(id, owner, amount),
  * transactions(id, sender, receiver, amount, time) between accounts.

It provides the following functions:

1. Create new client
1. Add new empty account to client
1. show client information and his accounts
1. Show all client


2. Show accounts
2. Transfer amount from one account to another
2. Refill account(increase amount)
2. Debit account(decrease amount)


3. Show all transactions
3. Filter transaction list
and so on

How to run it?
Requirements: maven 3, git, internet connection(download libraries).
clone, compile & run , open

- git clone https://github.com/iipolovinkin/JupiterBank.git
- mvn clean compile jetty:run
- url: http://127.0.0.1:8081/

Actual login\password pairs described in the file security.xml(admin\pass)

Some of used technologies:

    java 1.7+, SpringMVC 3.2+, Hibernate 4.+, SLF4J 1.6+, JUnit 4.10+, Apache tiles 3.+, Bootstrap 3.+, Mockito 1.10.+, 
    jquery, etc.

Command to run findbugs check:

mvn clean findbugs:check