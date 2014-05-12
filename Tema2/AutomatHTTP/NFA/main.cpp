#include "others.h"
#include <queue>
#include <vector>
#include <string>

int main()
{
    const std :: vector <std::string> STARI = {"NEED CONNECTION", "NEED REQUEST", "SENT REQUEST", "NEED ACCES AUTENTH",
        "NEED BODY", "GOT DATA", "NO DATA", "REDIRECTION", "ERROR/ FAILURE"
        };
	std::ifstream in("in.txt");
	std::vector<Reach> myV;
	std::string alp;
	getline(in,alp);
	std::cout <<"TRANZITIILE SUNT: "<< alp <<"\n";
	std::cout <<"OK    ---> a" << "\n";
	std::cout <<"Error ---> b" << "\n";
	std::cout <<"301   ---> c" << "\n";
	std::cout <<"401   ---> d" << "\n";
	std::cout <<"200   ---> e" << "\n";
	std::cout <<"204   ---> f" << "\n";
	int nSp,nSt,nA(static_cast<int>(alp.size()));
	in >> nSt;
	in >> nSp;
	Reach final(nSt);
	foo::reader(final,in);
	for(int i(0); i < nSt*nA; i++)
	{
		myV.push_back(Reach(nSt));
		foo::reader(myV[i],in);
	}
	in.close();
	while(true)
	{
		std::cout << std::endl;
		std::cout << "Introduceti scenariul: ";
		if(std::cin.peek() == END) break;
		Reach start(nSt),SINK(nSt);
		start.set_b(nSp);
		std::string cuv;
		getline(std::cin,cuv);
		std::queue<int> myQ;
		bool okay = false;
		for(int i(0); i < static_cast<int>(cuv.size()); i++)
		{
			int aux(alp.find(cuv[i]));
			if(aux == std::string::npos)
			{
				std::cout << "Scenariu invalid";
				myQ = std::queue<int> ();
				okay = true;
				break;
			}
			myQ.push(aux);
		}
		if (okay) continue;
		while(!myQ.empty())
		{
			Reach x(nSt);
			for(int i(0);i < nSt; i++)
			{
				if(!start.check(i)) continue;
				x = x + myV[i*nA + myQ.front()];
			}
			myQ.pop();
			start = x;
			if(start == SINK) break;
		}
		if(start == SINK)
		{
			std::cout << "Nu exista cale de iesire";
			continue;
		}
        int stare;
        for(int i = 0; i <=9; i++){
            if(start.check(i) == true)
                stare = i;
        }
        if(foo::answer(start,final,nSt) == true){
            std :: cout << "Am ajuns in starea " << STARI[stare] << ".\nStarea este finala."<< "\n";
        }
        else{
            std :: cout << "Am ajuns in starea " << STARI[stare] << ".\nStarea nu este finala."<< "\n";
        }

	}
	return 0;
}
