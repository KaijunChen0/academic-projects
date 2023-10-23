#ifndef FINALPROJECT_CSVFILEREADER_H
#define FINALPROJECT_CSVFILEREADER_H
#include "string"
#include "fstream"
#include "iostream"
#include "vector"
#include "map"
#include "sstream"
#include "AirQualityType.h"
using namespace std;

class CsvFileReader {
private:
    string path;
    vector<AirQualityType> data; //vector to store the data from the csv file

public:
    CsvFileReader(const string filePath); //constructor that takes a file path as input
    void readCsvFile(); //read csv file and store data into vector data
    vector<AirQualityType> getData(); //getter for data

    double convertToDouble(string& str); //convert string to double
    double convertToDouble(string& str, int precision); //convert string to double with precision

    vector<string> getTokensOfOneCol(string line); //get tokens of one line
    map<string, int> getHeadersWithIndex(string line); //get headers with their index in col
    AirQualityType generateAirQualityForOneCol(vector<string> tokens, map<string, int> headersWithIndex); //generate AirQualityType for one line
    bool validateLine(string line); //check whether the line starts with semicolon

    friend ostream &operator<<(ostream &os, const CsvFileReader &reader);
};


#endif //FINALPROJECT_CSVFILEREADER_H
