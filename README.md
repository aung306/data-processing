# CIS4930 Data Processing & Storage
## Setup Instructions
If main.class is not present, create the file by running 
javac main.java 
Once main.class is created, start data processing by running 
java main
## Suggestions
1. It is repetitive to list the functions and then define the functions later on in the instructions. It would be better if it was like:
a. int get(String key); will return the value associated with the key or null if the key doesn’t exist. Can be called anytime even when a transaction is not in progress.
b. void put(String key, int val); will create a new key with the provided value if a key doesn’t exist. Otherwise it will update the value of an existing key. Is called when a transaction is not in progress throw an exception.
c. void begin_transaction(); starts a new transaction. Only a single transaction can exist at a time. Within a transaction you can make as many changes to as many keys as you like. However, they should not be “visible” to get(), until the transaction is committed. A transaction ends when either commit() or rollback() is called.
d. commit(); applies changes made within the transaction to the main state. Allowing any future gets() to “see” the changes made within the transaction.
e. rollback(); should abort all the changes made within the transaction and everything should go back to the way it was before.
2. For ease on both the student and grading sides, it would be effective to create a test suite in popular languages with instructions on how to link the program to the library.