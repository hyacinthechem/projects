//
// Created by Hyacinthe Chemasle on 13/12/2024.
//

#include "TicketSystem.h"
#include "Ticket.h"
#include <iostream>
#include <sstream>
#include <fstream>
#include <vector>
#include <string>


void TicketSystem::loadTickets() {
    std::ifstream file("data/TicketData.txt");
   // file.open("TicketData.txt");
    if(!file.is_open()) { //checks if the ifstream file is open. if not then throw error
        std::cout<<"Error opening file"<<std::endl;
        return;
    }

    std::string line; //assign line variable to process file line by line
    while(std::getline(file,line)) { //while there are lines in file process each line and print it. while(scanner.hasNextLine());
       std::stringstream ss(line); //string[] parts and ignore comma.
        //initialise the variables that we want to read line by line
        std::string name;
        double price;
        int rowNumber;
        int seatNumber;
        std::getline(ss, name, ',');
        ss>>price; ss.ignore(); //ignores the comma following price
        ss>>rowNumber; ss.ignore(); //ignores the coma following rowNumber
        ss>>seatNumber; ss.ignore();
        Ticket newTicket = Ticket(name,price,rowNumber,seatNumber);
        TicketSystem::allTickets.push_back(newTicket);
    }



}
#include <iostream>
#include <unistd.h> // For getcwd()



void TicketSystem::exampleTicket(){
   Ticket newTicket("Hyacinthe Chemasle", 100, 34, 8);
   std::cout<<newTicket.toString()<<std::endl;
   TicketSystem::allTickets.push_back(newTicket);
}

void TicketSystem::purchaseTicket() {
    std::string answer;
    std::cout << "Would you like to purchase a Ticket? ";
    std::cin >> answer;

    if (answer == "yes") {
        std::string name;
        int seatNumber;
        int rowNumber;
        double price;

        // Clear the input buffer to handle any leftover newline characters
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

        std::cout << "Please enter your name: ";
        std::getline(std::cin, name);  // Use getline to capture full name with spaces

        std::cout << "Please enter your seat number: ";
        std::cin >> seatNumber;
        std::cout << "Please enter your row number: ";
        std::cin >> rowNumber;


        if (rowNumber < 5) {
            price = 75;
        } else {
            price = 100;
        }

        Ticket newTicket(name, price, seatNumber, rowNumber);
        std::cout << "Successfully Purchased Ticket" << newTicket.toString()<<std::endl;
        TicketSystem::allTickets.push_back(newTicket);
    }
}


void TicketSystem::viewTicket() {
    std::string name;
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    std::cout<<"Please enter your name: ";
    std::getline(std::cin, name);
    bool found = inSystem(name);
    if(!found) {
        std::cout<<"Name not in System"<<std::endl;
    }else {
        std::cout<<"Name: "<<name<<std::endl;
    }
}

bool TicketSystem::inSystem(std::string name) {

    for(auto ticket : TicketSystem::allTickets) {
        if(ticket.getName() == name) {
            return true;
        }
    }
    return false;
}

void TicketSystem::showMostExpensiveTickets() {

     int mostExpensiveTicket = 0; //intialise counter to keep track of most expensive tickets
     Ticket* final_ticket = nullptr; //* declaration in cpp represents a pointer. right now we intialise to a null pointer.
    for(auto ticket : TicketSystem::allTickets) {
        if(ticket.getPrice() > mostExpensiveTicket) {
            mostExpensiveTicket = ticket.getPrice();
            final_ticket = &ticket;
        }
    }

    std::cout<<"The Most Expensive Ticket belongs to "<<final_ticket->toString()<<std::endl; // -> syntax which means that it is dereferncing the pointer and becomes final state
}

void TicketSystem::showAllTickets() {
  std::cout<<TicketSystem::allTickets.size()<<std::endl;
    for(auto ticket : TicketSystem::allTickets) { //auto is the c++ compiler that automatically infers the type object that its dealing with
        std::cout<<ticket.toString()<<std::endl;
    }
}

