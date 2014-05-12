#include "reach.h"
bool Reach::check(int x)
{
	return mySt[x];
}
Reach Reach::operator+(const Reach &y)
{
	Reach aux;
	for(int i(0); i < static_cast<int>(mySt.size()); ++i)
		aux.mySt.push_back(mySt[i] || y.mySt[i]);
	return aux;
}
Reach& Reach::operator=(const Reach &y)
{
	if(this == &y) return *this;
	mySt = y.mySt;
}
Reach::Reach(const Reach &y)
{
	mySt = y.mySt;
}
Reach::Reach(int x)
{
	mySt.assign(x,false);
}
void Reach::set_b(int x)
{
	if(x < 0 || x > static_cast<int>(mySt.size())) return;
	mySt[x] = true;
}
void Reach::printer(void)
{
	std::cout << std::endl;
	for(int i (0); i < static_cast<int>(mySt.size()); ++i)
		std::cout << std::boolalpha << mySt[i] << ' ';
	std::cout << std::noboolalpha;
}
bool Reach::operator==(const Reach &y)
{
	for(int i(0); i < static_cast<int>(mySt.size()); i++)
		if(mySt[i] != y.mySt[i]) return false;
	return true;
}
