# 📚 Java Library Management System (CLI)

Hey there! Welcome to my Library Management System. 

I built this console-based application to take all the Java, Object-Oriented Programming (OOP), and software design concepts I've been learning and actually put them into practice in a real project. 

It’s a fully functional terminal app that simulates a real library—you can search for books, check them out, return them, and even pay off fake library fines using a mock payment gateway. 

## 💡 Why I Built This (And What I Learned)

Instead of just throwing all the code into one massive file, I really wanted to focus on writing clean, scalable software. Here's a peek under the hood at how I structured things:

* **Everything is Organized:** I broke the project down into distinct layers (`Models`, `Services`, `Databases`, and `Utilities`). It makes the code way easier to read and maintain.
* **SOLID Principles in Action:** I tried my best to stick to core software engineering rules. For example, my `InputHelper` is the *only* class that handles the `Scanner`, which saved me from a bunch of annoying input bugs.
* **Strategy Pattern:** I'm pretty proud of the payment system. By using an interface for the `PaymentGateway`, the app can seamlessly switch between `UPI` and `Wallet` payments on the fly without breaking the core logic. 
* **Data Handling:** Since I haven't hooked this up to a SQL database yet, I used Java Collections (like `HashMap`) to act as my in-memory database and handle the relationships between users and their borrowed books.

## ✨ What It Can Do

* **Colorful Terminal UI:** I added ANSI escape codes and some simulated loading delays so the terminal feels alive and interactive, not just like a boring wall of text.
* **Smart Book Searching:** You can look up books by their ID, Author, or Title.
* **Borrow & Return:** The app actually tracks inventory. If you check out a book, it marks it unavailable and ties it to your specific user ID. 
* **Settle Your Dues:** Got a fine? You can "pay" it off using the integrated mock payment gateways. 

## 🚀 How to Run It Locally

Want to try it out? You just need Java installed on your machine. 

1. **Clone the repo:**
   ```bash
   git clone https://github.com/tanujthakur634-cloud/library-management-system.git
   cd library-management-system
