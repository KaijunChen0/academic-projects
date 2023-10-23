#include "AirQualityType.h"

/**
 * Default constructor
 * @param date as Date object
 * @param time as Time object
 * @param temperature as double
 * @param absoluteHumidity as double
 * @param relativeHumidity as double
 */
AirQualityType::AirQualityType(const Date &date, const Time &time, double temperature, double absoluteHumidity,
                               double relativeHumidity) : date(date), time(time), temperature(temperature),
                                                          absoluteHumidity(absoluteHumidity),
                                                          relativeHumidity(relativeHumidity) {}
/**
 * Getter for date
 * @return date as Date object
 */
const Date &AirQualityType::getDate() const {
    return date;
}

/**
 * Getter for time
 * @return time as Time object
 */
const Time &AirQualityType::getTime() const {
    return time;
}

/**
 * Getter for temperature
 * @return temperature as double
 */
double AirQualityType::getTemperature() const {
    return temperature;
}

/**
 * Getter for absolute humidity
 * @return absolute humidity as double
 */
double AirQualityType::getAbsoluteHumidity() const {
    return absoluteHumidity;
}

/**
 * Getter for relative humidity
 * @return relative humidity as double
 */
double AirQualityType::getRelativeHumidity() const {
    return relativeHumidity;
}

/**
 * Setter for date
 * @param date as Date object
 */
void AirQualityType::setDate(const Date &date) {
    AirQualityType::date = date;
}

/**
 * Setter for time
 * @param time as Time object
 */
void AirQualityType::setTime(const Time &time) {
    AirQualityType::time = time;
}

/**
 * Setter for temperature
 * @param temperature as double
 */
void AirQualityType::setTemperature(double temperature) {
    AirQualityType::temperature = temperature;
}

/**
 * Setter for absolute humidity
 * @param absoluteHumidity as double
 */
void AirQualityType::setAbsoluteHumidity(double absoluteHumidity) {
    AirQualityType::absoluteHumidity = absoluteHumidity;
}

/**
 * Setter for relative humidity
 * @param relativeHumidity as double
 */
void AirQualityType::setRelativeHumidity(double relativeHumidity) {
    AirQualityType::relativeHumidity = relativeHumidity;
}
