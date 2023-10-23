#ifndef FINALPROJECT_STATISTICS_H
#define FINALPROJECT_STATISTICS_H
#include "AirQualityType.h"
#include <vector>

using namespace std;

class Statistics {
public:
    Statistics(const std::vector<AirQualityType>& data);

    void displayAverageTemperatureForMonth(int month, int year);
    void displayAverageRelativeHumidityForMonth(int month, int year);
    void displayAverageAbsoluteHumidityForMonth(int month, int year);
    void displayAirQualityDataAtDateTime(const Date& date, const Time& time);
    void displayHighestTemperatureForMonth(int month, int year);
    void displayHighestRelativeHumidityForMonth(int month, int year);
    void displayHighestAbsoluteHumidityForMonth(int month, int year);
    void displayDatesWithTemperatureAboveAverageForMonth(int month, int year);
    void displayDatesWithRHAboveAverageForMonth(int month, int year);
    void displayDatesWithAHAboveAverageForMonth(int month, int year);

private:
    vector<AirQualityType> airQualityData;

    double calculateAverageTemperatureForMonth(int month, int year);
    double calculateAverageRelativeHumidityForMonth(int month, int year);
    double calculateAverageAbsoluteHumidityForMonth(int month, int year);
    AirQualityType getAirQualityDataAtDateTime(const Date& date, const Time& time);
    double getHighestTemperatureForMonth(int month, int year);
    double getHighestRelativeHumidityForMonth(int month, int year);
    double getHighestAbsoluteHumidityForMonth(int month, int year);
    bool validateMonth(int month);
    bool isMissingValue(int value);
    bool validateYear(int year);
};


#endif //FINALPROJECT_STATISTICS_H
