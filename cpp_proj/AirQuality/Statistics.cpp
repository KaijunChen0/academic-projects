#include "Statistics.h"
#include <iostream>

using namespace std;

const int JAN = 1;
const int DEC = 12;
const int TARGET_YEAR1 = 2004;
const int TARGET_YEAR2 = 2005;
const int NOT_FOUND = -200;//-1 not proper as temp can be -1
const int MISSING_VALUE_FLAG = -200;

/**
 * Constructor for Statistics class
 * @param data as a vector of AirQualityType
 */
Statistics::Statistics(const vector<AirQualityType>& data) : airQualityData(data) {}

/**
 * Calculate the average temperature for a given month
 * @param month as an integer
 * @param year as an integer
 * @return as a double
 */
double Statistics::calculateAverageTemperatureForMonth(int month, int year) {
    if(!validateMonth(month) || !validateYear(year)){
        cerr << "Invalid month or year" << endl;
        return NOT_FOUND;
    }
    double sum = 0.0;
    int count = 0;
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && !isMissingValue(data.getTemperature()) &&
            data.getDate().GetYear() == year) {
            sum += data.getTemperature();
            count++;
        }
    }
    if (count == 0) {
        return 0.0; // Return 0 if no data available for the given month
    }
    return sum / count;
}

/**
 * Calculate the average relative humidity for a given month
 * @param month as an integer
 * @param year as an integer
 * @return as a double
 */
double Statistics::calculateAverageRelativeHumidityForMonth(int month, int year) {
    if(!validateMonth(month) || !validateYear(year)){
        cout << "Invalid month or year" << endl;
        return NOT_FOUND;
    }
    double sum = 0.0;
    int count = 0;
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && !isMissingValue(data.getRelativeHumidity()) &&
            data.getDate().GetYear() == year) {
            sum += data.getRelativeHumidity();
            count++;
        }
    }
    if (count == 0) {
        return 0.0; // Return 0 if no data available for the given month
    }
    return sum / count;
}

/**
 * Calculate the average absolute humidity for a given month
 * @param month as an integer
 * @param year as an integer
 * @return as a double
 */
double Statistics::calculateAverageAbsoluteHumidityForMonth(int month, int year) {
    if(!validateMonth(month) || !validateYear(year)){
        cout << "Invalid month or year" << endl;
        return NOT_FOUND;
    }
    double sum = 0.0;
    int count = 0;
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && !isMissingValue(data.getAbsoluteHumidity())
        && data.getDate().GetYear()== year) {
            sum += data.getAbsoluteHumidity();
            count++;
        }
    }
    if (count == 0) {
        return 0.0; // Return 0 if no data available for the given month
    }
    return sum / count;
}

/**
 * Get a air quality data for a given date and time
 * @param date as a Date object
 * @param time as a Time object
 * @return data as a AirQualityType object if found, otherwise return an empty AirQualityType object
 */
AirQualityType Statistics::getAirQualityDataAtDateTime(const Date& date, const Time& time) {
    for (const auto& data : airQualityData) {
        if (data.getDate().GetYear() == date.GetYear() && data.getDate().GetMonth() == date.GetMonth() &&
            data.getDate().GetDay() == date.GetDay() && data.getTime().GetHour() == time.GetHour() &&
            data.getTime().GetMin() == time.GetMin()) {
            return data;
        }
    }
    // Return an empty AirQualityType object if data is not found for the given date and time
    cout << "Not found a matched air quality history!" << endl;
    return AirQualityType(Date(), Time(), 0.0, 0.0, 0.0);
}

/**
 * Get the highest temperature for a given month
 * @param month as an integer
 * @param year as an integer
 * @return as a double
 */
double Statistics::getHighestTemperatureForMonth(int month, int year) {
    if(!validateMonth(month) || !validateYear(year)){
        cout << "Invalid month or year" << endl;
        return NOT_FOUND;
    }
    double highestTemp = -999.0; // Initialize with a very low value
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && data.getTemperature() > highestTemp && data.getDate().GetYear() == year) {
            highestTemp = data.getTemperature();
        }
    }
    return highestTemp;
}

/**
 * Get the highest relative humidity for a given month
 * @param month as an integer
 * @param year as an integer
 * @return as a double
 */
double Statistics::getHighestRelativeHumidityForMonth(int month, int year) {
    if(!validateMonth(month) || !validateYear(year)){
        cout << "Invalid month or year" << endl;
        return NOT_FOUND;
    }
    double highestRH = -999.0; // Initialize with a very low value
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && data.getRelativeHumidity() > highestRH && data.getDate().GetYear() == year) {
            highestRH = data.getRelativeHumidity();
        }
    }
    return highestRH;
}

/**
 * Get the highest absolute humidity for a given month
 * @param month as an integer
 * @param year as an integer
 * @return as a double
 */
double Statistics::getHighestAbsoluteHumidityForMonth(int month, int year) {
    if(!validateMonth(month) || !validateYear(year)){
        cout << "Invalid month or year" << endl;
        return NOT_FOUND;
    }
    double highestAH = -999.0; // Initialize with a very low value
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && data.getAbsoluteHumidity() > highestAH && data.getDate().GetYear() == year) {
            highestAH = data.getAbsoluteHumidity();
        }
    }
    return highestAH;
}

/**
 * Display the average temperature for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayAverageTemperatureForMonth(int month, int year) {
    double averageTemp = calculateAverageTemperatureForMonth(month, year);
    if(averageTemp == MISSING_VALUE_FLAG)
        return;
    cout << "Average temperature for " << month <<"/" << year<< "(M/Y): " << averageTemp << "°C" << endl;
}

/**
 * Display the average relative humidity for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayAverageRelativeHumidityForMonth(int month, int year){
    double averageRH = calculateAverageRelativeHumidityForMonth(month, year);
    if(averageRH == MISSING_VALUE_FLAG)
        return;
    cout << "Average relative humidity for " << month <<"/" << year<< "(M/Y): "<< averageRH << "%" << endl;
}

/**
 * Display the average absolute humidity for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayAverageAbsoluteHumidityForMonth(int month, int year) {
    double averageAH = calculateAverageAbsoluteHumidityForMonth(month, year);
    if(averageAH == MISSING_VALUE_FLAG)
        return;
    cout << "Average absolute humidity for " << month <<"/" << year<< "(M/Y): " << averageAH << endl;
}

/**
 * Display the air quality data for a given date and time
 * @param date as a Date object
 * @param time as a Time object
 */
void Statistics::displayAirQualityDataAtDateTime(const Date& date, const Time& time) {
    AirQualityType airQualityData = getAirQualityDataAtDateTime(date, time);
    cout << "Air quality data at " << date << " " << time << ' ' << endl;
    cout << "Temperature: " << airQualityData.getTemperature() << "°C" << endl;
    cout << "Relative Humidity: " << airQualityData.getRelativeHumidity() << "%" << endl;
    cout << "Absolute Humidity: " << airQualityData.getAbsoluteHumidity() << endl;
}

/**
 * Display the highest temperature for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayHighestTemperatureForMonth(int month, int year) {
    double highestTemp = getHighestTemperatureForMonth(month, year);
    cout << "Highest temperature for " << month <<"/" << year << "(M/Y): "<< highestTemp << "°C" << endl;
}

/**
 * Display the highest relative humidity for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayHighestRelativeHumidityForMonth(int month, int year) {
    double highestRH = getHighestRelativeHumidityForMonth(month, year);
    cout << "Highest relative humidity for " << month <<"/" << year<< "(M/Y): "<< highestRH << "%" << endl;
}

/**
 * Display the highest absolute humidity for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayHighestAbsoluteHumidityForMonth(int month, int year) {
    double highestAH = getHighestAbsoluteHumidityForMonth(month, year);
    cout << "Highest absolute humidity for " << month <<"/" << year<< "(M/Y): " << highestAH << endl;
}

/**
 * Display the dates with temperature above average for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayDatesWithTemperatureAboveAverageForMonth(int month, int year) {
    double averageTemp = calculateAverageTemperatureForMonth(month, year);
    cout << "Average Temperature of " << month <<"/" << year<< "(M/Y): "<< averageTemp << "°C" << endl;
    cout << "Dates with temperature above average for " << month <<"/" << year<< "(M/Y): " << endl;
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && data.getTemperature() > averageTemp && data.getDate().GetYear() == year) {
            cout << data.getDate() << " " << data.getTime() << " - " << data.getTemperature() << "°C" << endl;
        }
    }
}

/**
 * Display the dates with relative humidity above average for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayDatesWithRHAboveAverageForMonth(int month, int year) {
    double averageRH = calculateAverageRelativeHumidityForMonth(month, year);
    cout << "Average relative humidity of " << month <<"/" << year<< "(M/Y): "<< averageRH << "%" << endl;
    cout << "Dates with relative humidity above average for " <<month <<"/" << year<< "(M/Y): " << endl;
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && data.getRelativeHumidity() > averageRH && data.getDate().GetYear() == year) {
            cout << data.getDate() << " " << data.getTime() << " - " << data.getRelativeHumidity() << "%" << endl;
        }
    }
}

/**
 * Display the dates with absolute humidity above average for a given month
 * @param month as an integer
 * @param year as an integer
 */
void Statistics::displayDatesWithAHAboveAverageForMonth(int month, int year) {
    double averageAH = calculateAverageAbsoluteHumidityForMonth(month, year);
    cout << "Average absolute humidity of " << month <<"/" << year<< "(M/Y): "<< averageAH << endl;
    cout << "Dates with absolute humidity above average for " <<month <<"/" << year<< "(M/Y): " << endl;
    for (const auto& data : airQualityData) {
        if (data.getDate().GetMonth() == month && data.getAbsoluteHumidity() > averageAH && data.getDate().GetYear() == year) {
            cout << data.getDate() << " " << data.getTime() << " - " << data.getAbsoluteHumidity() << endl;
        }
    }
}

/**
 * Validate the month
 * @param month as an integer
 * @return true if the month is between Jan and Dem, false otherwise
 */
bool Statistics::validateMonth(int month) {
    return (month <= DEC && month >= JAN);
}

/**
 * Check if the value is missing
 * @param value as an integer
 * @return ture if the value is equal to -200 in csv file, which means missing value flag, false otherwise
 */
bool Statistics::isMissingValue(int value) {
    return value == MISSING_VALUE_FLAG;
}

/**
 * Check if the year is 2004 or 2005
 * @param year as int
 * @return true if year is 2004 or 2005, otherwise false
 */
bool Statistics::validateYear(int year) {
    return (year == TARGET_YEAR1 || year == TARGET_YEAR2);
}