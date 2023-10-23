#include<iostream>
#include<string>
#include "Time.h"
#include "sstream"

using namespace std;

Time::Time()
{
	hour=0;
	min=0;
}

Time::Time(const std::string &timeStr) {
    string hourStr, minStr;
    istringstream timeStream(timeStr);

    getline(timeStream, hourStr, '.');
    getline(timeStream, minStr, '.');

    this->hour = stoi(hourStr);
    this->min = stoi(minStr);
}

Time::Time(int hh, int mm)
{
	hour=hh;
	min=mm;
}
Time::~Time()
{
	//none
}
int Time::GetHour() const
{
	return hour;
}
int Time::GetMin() const
{
	return min;
}
void Time::SetHour(int hh)
{
	hour=hh;
}
void Time::SetMin(int mm)
{
	min=mm;
}
void Time::SetTime(int hh, int mm)
{
	hour=hh;
	min=mm;
}
void Time::printTime()
{
	cout<<"Time: "<<hour<<":"<<min;
}
istream & operator >>( istream & input, Time & T )
{
	string time,sHH,sMM;
	int position, hh, mm;

	getline(input,sHH,':');
	getline(input,sMM,'\n');

	hh=stoi(sHH);
	mm=stoi(sMM);

	T.SetHour(hh);
	T.SetMin(mm);
	return input;
}
ostream & operator <<( ostream & os, const Time & T )
{
	os<<"Time: "<< T.GetHour() << ":" << T.GetMin() << ' ';
	return os;
}
