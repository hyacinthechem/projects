cmake_minimum_required(VERSION 3.29)
project(implementation)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(implementation
    Role.cpp
    Set.cpp
    Set.h
        main.cpp)
