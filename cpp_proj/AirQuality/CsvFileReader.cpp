#include "CsvFileReader.h"
#include "algorithm"
#include "iomanip"
#include <cmath>
using namespace std;

/**
 * Constructor of CsvFileReader and load data into reader
 * @param filePath as string
 */
CsvFileReader::CsvFileReader(const std::string filePath) {
   this->path = filePath;
   readCsvFile(); //load data into reader
}

/**
 * Get data from reader
 */
void CsvFileReader::readCsvFile() {
    ifstream airQualityCsv;
    bool isFirstLine = true;

    //open the file
    airQualityCsv.open(this->path);
    //check whether fail to open or not
    if(airQualityCsv.fail()){
        cerr << "Unable to open the file " << this->path << endl;
        return;
    }

    // read file to get data line by line, and store as AirQualityType in vector
    map<string, int> headersWithIndex; //to store headers and their index in col
    while(airQualityCsv.peek()!=EOF){ //executing read until reach the end of file
        string recordsOfOneCol;
        getline(airQualityCsv, recordsOfOneCol);

        if(isFirstLine){ //read the first line to get index of headers to analyze
            headersWithIndex = getHeadersWithIndex(recordsOfOneCol);
            isFirstLine = false;
        } else{ //read the remaining lines
            if(validateLine(recordsOfOneCol)){ //to check whether the line only consists of semicolon, if ture, skip it
                return;
            }
            vector<string> tokens = getTokensOfOneCol(recordsOfOneCol);
//            0: 04/04/2005 *
//            1: 14.00.00 *
//            ...
//            12: 28,5 *
//            13: 13,1 *
//            14: 0,5028 *
            AirQualityType aq = generateAirQualityForOneCol(tokens, headersWithIndex);
            this->data.push_back(aq); //add each AirQuality to vector data
        }
    }
    airQualityCsv.close();
}

/**
 * Get tokens of one line
 * @param line as string
 * @return tokens as vector<string>
 */
vector<string> CsvFileReader::getTokensOfOneCol(std::string line) {
    replace(line.begin(), line.end(),'\r',';'); //remove `\r` at the end of line

    vector<string> tokens;
    string token;
    stringstream tokenStream(line);

    while(getline(tokenStream, token,';')){
        if(token.size() > 0){ //skip the last two '' elements
            tokens.push_back(token);
        }
    }
    return tokens;
}

/**
 * Generate a map of headers with their index in col
 * @param firstLine as string
 * @return headersWithIndex as map<string, int>
 */
map<string, int> CsvFileReader::getHeadersWithIndex(std::string firstLine) {
    vector<string> tokens = getTokensOfOneCol(firstLine);
    map<string, int> headerWithIndex;
    for(int i = 0; i < tokens.size(); i++){
        if(tokens[i]=="Date" || tokens[i]=="Time" || tokens[i] == "T" || tokens[i] == "RH" || tokens[i] == "AH"){
            headerWithIndex.insert(pair<string, int>(tokens[i], i));
        }
    }return headerWithIndex;
}

/**
 * Generate AirQualityType for one col
 * @param tokens as vector<string>
 * @param headersWithIndex as map<string, int>
 * @return aq as AirQualityType
 */
AirQualityType CsvFileReader::generateAirQualityForOneCol(vector<std::string> tokens,
                                                          map<std::string, int> headersWithIndex) {
    string dateStr;
    string timeStr;
    string tempStr;
    string ahStr;
    string rhStr;

    for(map<string, int>::iterator it = headersWithIndex.begin(); it != headersWithIndex.end(); it++){
        if(it->first == "Date"){
            dateStr = tokens[it->second];
        }else if(it->first == "Time"){
            timeStr = tokens[it->second];
        }else if(it->first == "T"){
            tempStr = tokens[it->second];
        }else if(it->first == "RH"){
            rhStr = tokens[it->second];
        }else if(it->first == "AH"){
            ahStr = tokens[it->second];
        }else{
            cerr << "Mismatched in headers with index" << endl;
        }
    }

    //convert format
    Date dateOj = Date(dateStr); // string date-->Date
    Time timeOj = Time(timeStr); // string time-->Time

    double temperature = convertToDouble(tempStr, 1); // string temp-->double
    double ah = convertToDouble(ahStr, 4); // string ah-->double
    double rh = convertToDouble(rhStr, 1); // string rh-->double
    // generate a AirQuality object for one col
    AirQualityType aq(dateOj, timeOj, temperature, ah, rh);
    return aq;
}

/**
 * Convert string to double
 * @param str as string
 * @param precision as int
 * @return double
 */
double CsvFileReader::convertToDouble(std::string &str, int precision) { //convert string to double
    replace(str.begin(),str.end(),',','.');
    double result = stod(str);
    return round(result * pow(10, precision)) / pow(10, precision);
}

/**
 * Validate line
 * @param line as string
 * @return true if line starts with ';', otherwise false
 */
bool CsvFileReader::validateLine(std::string line) {
    return line[0] == ';';
}

/**
 * Get data from reader
 * @return data as vector<AirQualityType>
 */
vector<AirQualityType> CsvFileReader::getData(){
    return this->data;
}

/**
 * Overload operator <<
 * @param os as ostream
 * @param reader as CsvFileReader
 * @return os as ostream
 */
ostream &operator<<(ostream &os, const CsvFileReader &reader) { //print out data vector
    os << "path: " << reader.path << "\n"
        << "data: " << "\n";
    int count = 0;
    for(auto &item: reader.data){
        cout << item.getDate() << " "
            << item.getTime() << " "
            << item.getTemperature() << " "
            << item.getRelativeHumidity() << " "
            << item.getAbsoluteHumidity() << endl;
    }
    return os;
}
