//
// Created by Hyacinthe Chemasle on 30/12/2024.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include "Set.h"

using namespace std;

  Set::Set(const std::string& nameOfSet) {
    this->nameOfSet = nameOfSet;
  }
  void Set::add(const string& name) {
    if(find(allStrings.begin(),allStrings.end(),name) == allStrings.end()){
      allStrings.push_back(name);
    }
  }

void Set::remove(const string& name) {
    allStrings.erase(find(allStrings.begin(), allStrings.end(), name));
  }

int Set::size() const {
    return allStrings.size();
  }

  void Set::clear() {
    allStrings.clear();
  }
