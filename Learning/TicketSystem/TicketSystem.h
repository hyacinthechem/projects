//
// Created by Hyacinthe Chemasle on 13/12/2024.
//

#ifndef TICKETSYSTEM_H
#define TICKETSYSTEM_H

#include "Ticket.h"
#include <string>
#include <vector>

class TicketSystem {

private:
    std::vector<Ticket> allTickets;

  public:
    void exampleTicket();
    void purchaseTicket();
    void viewTicket();
    void showTicket(Ticket ticket);
    void showAllTickets();
    void showMostExpensiveTickets();
    bool inSystem(std::string name);
};



#endif //TICKETSYSTEM_H
