//
// Created by Hyacinthe Chemasle on 13/12/2024.
//

#include "TicketSystem.h"
#include "Ticket.h"
#include <iostream>
#include <sstream>
#include <vector>
#include <string>

void TicketSystem::purchaseTicket(){
   Ticket newTicket("Hyacinthe Chemasle", 100, 34, 8);
   std::cout<<newTicket.toString()<<std::endl;
}

