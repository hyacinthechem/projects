//
// Created by Hyacinthe Chemasle on 14/12/2024.
//

#ifndef TICKET_H
#define TICKET_H

#include <sstream> //used for String based input/output equivalent to import java.lang.StringBuilder
#include <iostream> //equivalent to importing java.io ( Used for System.in and System.our (std::cout, std::cin)
#include <string> //equivalent to Java.String class import

#endif //TICKET_H

class Ticket{
/* Private field declarations to Ticket Class*/
    private:
    int seatNumber;
    int rowNumber;
    std::string stand;
    std::string name;
    double price;

/* Public area which contains constructors and methods for that class*/
public:
    Ticket(std::string name, double price, int seatNumber, int rowNumber); //Constructor for Ticket Class

    /*These are the methods which are all Getters. The syntax is almost the same to Java*/
    std::string getName();
    double getPrice();
    int getSeatNumber();
    int getRowNumber();
    std::string toString();

};