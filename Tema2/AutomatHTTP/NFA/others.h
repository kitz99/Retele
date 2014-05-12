#ifndef OTHERS_H
#define OTHERS_H
#include "reach.h"
#include <fstream>
#define END '#'
#define NEWL '\n'
namespace foo
{
	void reader(Reach &a,std::ifstream &in);
	bool answer(Reach a,Reach b,int c);
}
#endif
