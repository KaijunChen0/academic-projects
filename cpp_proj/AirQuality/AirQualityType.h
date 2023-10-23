#ifndef CONTACTMANAGEMENT_AIRQUALITYTYPE_H
#define CONTACTMANAGEMENT_AIRQUALITYTYPE_H
#include "Date.h"
#include "Time.h"

class AirQualityType {
private:
    Date date;
    Time time;
    double temperature;
    double absoluteHumidity;
    double relativeHumidity;

public:
    AirQualityType(const Date &date, const Time &time, double temperature, double absoluteHumidity,
                   double relativeHumidity); //constructor that takes all the attributes as input
    //getters and setters
    const Date &getDate() const;
    const Time &getTime() const;
    double getTemperature() const;
    double getAbsoluteHumidity() const;
    double getRelativeHumidity() const;

    void setDate(const Date &date);
    void setTime(const Time &time);
    void setTemperature(double temperature);
    void setAbsoluteHumidity(double absoluteHumidity);
    void setRelativeHumidity(double relativeHumidity);
};

#endif //CONTACTMANAGEMENT_AIRQUALITYTYPE_H
