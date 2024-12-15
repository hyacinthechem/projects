//
// Created by Hyacinthe Chemasle on 14/12/2024.
//

#ifndef TICKET_H
#define TICKET_H

#include <sstream> // used for string-based input/output, equivalent to Java's StringBuilder
#include <iostream> // equivalent to importing java.io (Used for System.in and System.out)
#include <string> // equivalent to Java.String class import

class Ticket {
    // Private field declarations for the Ticket class
private:
    int seatNumber;
    int rowNumber;
    std::string name;
    double price;

    // Public area which contains constructors and methods for the class
public:
    Ticket(std::string name, double price, int seatNumber, int rowNumber); // Constructor for Ticket Class

    // Getter methods
    std::string getName() const;
    double getPrice() const;
    int getSeatNumber() const;
    int getRowNumber() const;
    std::string toString() const;
};

#endif // TICKET_H
