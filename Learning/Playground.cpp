//
// Created by Hyacinthe Chemasle on 12/11/202

#include "Playground.h"
#include <string>

#include "Playground.h"

void Playground::printMessage() {
    std::cout << "Hello World!" << std::endl; // std::cout is equivalent to System.out.println
    // std::endl is equivalent to new line
}

void Playground::getUserInput() {
    int number; //Scanner scan = new Scanner(System.in);
    int secondNumber;
    std::cout << "Please enter a number: "; //UI.askInt(double num)
    std::cin>> number; //scans for the user input from UI.askInt(double num)
    std::cout<< "Enter another number: "; //UI.askInt(double num )
    std::cin>>secondNumber; //scans for the user input from UI.askInt(double num)
    addNumber(number,secondNumber);
}

void Playground::addNumber(int firstNum,int secondNum) {
    int ans = firstNum + secondNum;
    std::cout<<"Answer: "<<ans<<std::endl;
}

int  Playground::getNumberOfCharacters() {
     std::cout<<"Please enter a password: "<<std::endl;
     char input[100];
     std::cin>>input;
     int length = 0;
     for(int i=0; input[i]!='\0'; i++) { //input[i]!='\0' is the equivalent to input.length or input.size()
         length++;
     }
     return length;
}


//main function must be outside the class for C++. IN java we put main method inside class but CPP is different.

