//
// Created by Hyacinthe Chemasle on 13/12/2024.
//

#include "TicketSystem.h"
#include "Ticket.h"
#include <iostream>
#include <sstream>
#include <vector>
#include <string>

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

void TicketSystem::showAllTickets() {
  std::cout<<TicketSystem::allTickets.size()<<std::endl;
    for(auto ticket : TicketSystem::allTickets) { //auto is the c++ compiler that automatically infers the type object that its dealing with
        std::cout<<ticket.toString()<<std::endl;
    }
}

