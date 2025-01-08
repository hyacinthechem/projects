// Created by Hyacinthe Chemasle on 30/12/2024.

#pragma once  // Modern alternative to include guards

#include <string>
#include <vector>

class Set {
private:
    int sizeOfSet;
    std::string name;
    std::string nameOfSet;
    std::vector<std::string> allStrings;

public:
    Set(const std::string& nameOfSet);  // Use const reference to avoid unnecessary copying
    void add(const std::string& name);  // const ref for the same reason
    void remove(const std::string& name);
    void clear();
    int size() const;  // Mark as const since it doesn't modify the object
};

