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
        
    }

}

void TicketSystem::showAllTickets() {
  std::cout<<TicketSystem::allTickets.size()<<std::endl;
    for(auto ticket : TicketSystem::allTickets) { //auto is the c++ compiler that automatically infers the type object that its dealing with
        std::cout<<ticket.toString()<<std::endl;
    }
}

