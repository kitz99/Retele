#ifndef REACH_H
#define REACH_H
#include <iostream>
#include <string>
#include <vector>
class Reach
{
	std::vector<bool> mySt;
public:
	Reach(void)
	{}
	Reach(int x);
	Reach(const Reach &y);
	Reach& operator=(const Reach &y);
	bool operator==(const Reach &y);
	~Reach(void)
	{
		mySt.erase(mySt.begin(),mySt.end());
	}
	Reach operator+(const Reach &y);
	void set_b(int x);
	void printer(void);
	bool check(int x);
};
#endif
