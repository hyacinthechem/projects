#include "Ticket.h"
#include <sstream>
#include <iostream>
#include <string>

Ticket::Ticket(std::string name, double price, int seatNumber, int rowNumber)
    : name(name), price(price), seatNumber(seatNumber), rowNumber(rowNumber) {} // Constructor initialization list

// Getter methods with const keyword for const-correctness

std::string Ticket::getName() const {  // Adding const to the method definition
    return name;
}

double Ticket::getPrice() const {  // Adding const to the method definition
    return price;
}

int Ticket::getSeatNumber() const {  // Adding const to the method definition
    return seatNumber;
}

int Ticket::getRowNumber() const {  // Adding const to the method definition
    return rowNumber;
}

// toString method with const keyword for const-correctness

std::string Ticket::toString() const {  // Adding const to the method definition
    std::ostringstream sb;
    sb << name << "$" << price << " Seat: " << seatNumber << " Row: " << rowNumber;
    return sb.str();
}
