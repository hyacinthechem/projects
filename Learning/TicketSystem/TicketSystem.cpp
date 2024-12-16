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
    std::cout<<"Would you like to purchase a Ticket?";
    std::cin>>answer;
    if(answer == "yes") {
     std::string name;
        int seatNumber;
        int rowNumber;
        double price;
        std::cout<<"Please enter your name: ";
        std::cin>>name;
        std::cout<<"Please enter your seat number: ";
        std::cin>>seatNumber;
        std::cout<<"Please Enter your row number";
        std::cin>>rowNumber;
        if(rowNumber<5) {
             price = 75;
        }else {
             price = 100;
        }
        Ticket newTicket(name, seatNumber, rowNumber, price);
        std::cout<<"Succesfully Purchased Ticket";
        TicketSystem::allTickets.push_back(newTicket);
    }
}

void TicketSystem::viewTicket() {
    std::string name;
    std::cout<<"Please enter your name: ";
    std::cin>>name;
    inSystem(name);
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

