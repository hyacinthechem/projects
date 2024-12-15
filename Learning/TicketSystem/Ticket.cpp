//
// Created by Hyacinthe Chemasle on 15/12/2024.
//

#include "Ticket.h"
#include <sstream>
#include <iostream>
#include <string>

Ticket::Ticket(std::string name, double price, int seatNumber, int rowNumber) //Constructor for Ticket Class
 : name(name), price(price), seatNumber(seatNumber), rowNumber(rowNumber){} //this is specific C++ syntax

/*These are the methods which are all Getters. The syntax is almost the same to Java*/

std::string Ticket::getName(){
  return name;
}

double Ticket::getPrice(){
  return price;
}

int Ticket::getSeatNumber(){
  return seatNumber;
}

int Ticket::getRowNumber(){
  return rowNumber;
}

/*toString method equivalent in C++*/

std::string Ticket::toString(){
  std::ostringstream sb; //Creates StringBuilder oss = new StringBuilder();
  sb << name << "$" << price << " Seat: " << seatNumber << " Row: " << rowNumber; // << equivalent to + but in the context of StringBuilder it refers to oss.append(name);
  return sb.str(); //equivalent to oss.toString(); sb.toString();
}


