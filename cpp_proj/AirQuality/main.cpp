#include <iostream>
#include "string"
#include "algorithm"
#include "CsvFileReader.h"
#include "Statistics.h"
using namespace std;

bool canConvertToInt(const string& value);//check whether input is a number or not
void promptError();//prompt error message
void showMenu(Statistics statistics);

int main() {
    CsvFileReader csvReader("/Users/kc/Downloads/finalProject_v2/resources/AirQualityUCI.csv");//initialize csvReader
//    CsvFileReader csvReader("/Users/kehanxiao/Documents/NEU/5008/finalProject/resources/AirQualityUCI.csv");//initialize csvReader

//    cout << csvReader.getData().size() << endl; //9357
    vector<AirQualityType> airQualityData = csvReader.getData();//get data from csvReader
    Statistics statistics(airQualityData);//initialize statistics

    showMenu(statistics);

    return 0;
}

/**
 * Show menu for user guide
 * @param statistics as Statistics
 */
void showMenu(Statistics statistics){
    int choice;
    while (true) { //show menu
        cout << "********** Air Quality Statistics **********" << endl;
        cout << "1) Display average temperature for a month" << endl;
        cout << "2) Display average relative humidity for a month" << endl;
        cout << "3) Display average absolute humidity for a month" << endl;
        cout << "4) Display air quality data at a specific date and time" << endl;
        cout << "5) Display highest temperature for a month" << endl;
        cout << "6) Display highest relative humidity for a month" << endl;
        cout << "7) Display highest absolute humidity for a month" << endl;
        cout << "8) Display dates with temperature above average for a month" << endl;
        cout << "9) Display dates with relative humidity above average for a month" << endl;
        cout << "10) Display dates with absolute humidity above average for a month" << endl;
        cout << "0) Exit" << endl;

        cout << "Enter your choice: ";
        cin >> choice;
        if (choice == 1) {
            string month1, year1;
            cout << "Enter the month(1-12) and year(2004 or 2005): ";
            cin >> month1 >> year1;
            if(canConvertToInt(month1) && canConvertToInt(year1)){
                statistics.displayAverageTemperatureForMonth(stoi(month1), stoi(year1));
            }else promptError();
        } else if (choice == 2) {
            string month2, year2;
            cout << "Enter the month (1-12) and year(2004 or 2005): ";
            cin >> month2 >> year2;
            if(canConvertToInt(month2) && canConvertToInt(year2)){
                statistics.displayAverageRelativeHumidityForMonth(stoi(month2), stoi(year2));
            }else promptError();
        } else if (choice == 3) {
            string month3, year3;
            cout << "Enter the month (1-12) and year(2004 or 2005): ";
            cin >> month3 >> year3;
            if(canConvertToInt(month3) && canConvertToInt(year3)){
                statistics.displayAverageAbsoluteHumidityForMonth(stoi(month3), stoi(year3));
            }else promptError();
        } else if (choice == 4) {
            string day, month4, year4, hour;
            cout << "Enter a date between March 2004 to April 2005(day month year): ";
            cin >> day >> month4 >> year4;
            cout << "Enter a time between 0 and 23(hour): ";
            cin >> hour;
            if(canConvertToInt(day) && canConvertToInt(month4) &&
               canConvertToInt(year4) && canConvertToInt(hour)){
                Date dateObj(stoi(day), stoi(month4), stoi(year4));
                Time timeObj(stoi(hour), 0);
                statistics.displayAirQualityDataAtDateTime(dateObj, timeObj);
            }else promptError();
        } else if (choice == 5) {
            string month5, year5;
            cout << "Enter the month (1-12) and year(2004 or 2005): ";
            cin >> month5 >> year5;
            if(canConvertToInt(month5) && canConvertToInt(year5)){
                statistics.displayHighestTemperatureForMonth(stoi(month5), stoi(year5));
            }else promptError();
        } else if (choice == 6) {
            string month6, year6;
            cout << "Enter the month (1-12) and year(2004 or 2005): ";
            cin >> month6 >> year6;
            if(canConvertToInt(month6) && canConvertToInt(year6)){
                statistics.displayHighestRelativeHumidityForMonth(stoi(month6), stoi(year6));
            }else promptError();
        } else if (choice == 7) {
            string month7, year7;
            cout << "Enter the month (1-12) and year(2004 or 2005): ";
            cin >> month7 >> year7;
            if(canConvertToInt(month7) && canConvertToInt(year7)){
                statistics.displayHighestAbsoluteHumidityForMonth(stoi(month7), stoi(year7));
            }else promptError();
        } else if (choice == 8) {
            string month8, year8;
            cout << "Enter the month (1-12) and year(2004 or 2005): ";
            cin >> month8 >> year8;
            if(canConvertToInt(month8) && canConvertToInt(year8)){
                statistics.displayDatesWithTemperatureAboveAverageForMonth(stoi(month8), stoi(year8));
            }else promptError();
        } else if (choice == 9) {
            string month9, year9;
            cout << "Enter the month (1-12) and year(2004 or 2005): ";
            cin >> month9 >> year9;
            if(canConvertToInt(month9) && canConvertToInt(year9)){
                statistics.displayDatesWithRHAboveAverageForMonth(stoi(month9), stoi(year9));
            }else promptError();
        } else if (choice == 10) {
            string month10, year10;
            cout << "Enter the month (1-12) and year(2004 or 2005): ";
            cin >> month10 >> year10;
            if(canConvertToInt(month10) && canConvertToInt(year10)){
                statistics.displayDatesWithAHAboveAverageForMonth(stoi(month10), stoi(year10));
            }else promptError();
        } else if (choice == 0) {
            cout << "Exiting..." << endl;
            break;
        } else {
            promptError();
        }
    }
}

/**
 * Validate input
 * @param value as string
 * @return ture if value can be converted to int, otherwise false
 */
bool canConvertToInt(const string& value){
    try {
        stoi(value);
        return true;
    }catch (const invalid_argument& e){//stoi() will throw invalid_argument if no conversion could be performed
        return false;
    }catch (const out_of_range& e){//stoi() will throw out_of_range if the converted value is out of the range of representable values by an int
        return false;
    }
}

/**
 * Prompt error for user
 */
void promptError(){
    cout << "Invalid input. Please try again." << endl;
}

