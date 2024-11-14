//
// Created by Hyacinthe Chemasle on 12/11/2024.
//

#include "Playground.h"


int main(){
  Playground pl;
  pl.printMessage();
  pl.getUserInput();
  int result = pl.getNumberOfCharacters();
  std::cout<<"Number of characters:"<<result<<std::endl;
}
