#include "others.h"
void foo::reader(Reach &a,std::ifstream &in)
{
	in.get();
	while(in.peek() != END && in.peek() != NEWL)
	{
		int aux;
		in >> aux;
		a.set_b(aux);
	}
}
bool foo::answer(Reach a,Reach b,int c)
{
	for(int i(0); i < c; i++)
	{
		if(a.check(i) && b.check(i)){
            return true;
		}
	}
	return false;
}
