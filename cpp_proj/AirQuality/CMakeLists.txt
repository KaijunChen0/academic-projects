cmake_minimum_required(VERSION 3.25)
project(finalProject)

set(CMAKE_CXX_STANDARD 17)

add_executable(finalProject main.cpp AirQualityType.cpp Date.cpp Time.cpp CsvFileReader.h CsvFileReader.cpp Statistics.h Statistics.cpp)
