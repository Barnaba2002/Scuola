#include <iostream>
#include<time.h>
using namespace std;
class Cronometro
{
public:
    Cronometro();//Costruttore
    void Start();
    void Stop();
    void Reset();
    float Tempo()const;//valore segnato dal Cronometro

private:
    float Ti;//Tempo iniziale : misura assoluta
    float TempoAccumulato;
    bool StatoConteggio;
};//Cronometro

Cronometro::Cronometro()
{
    TempoAccumulato=0;
    StatoConteggio=false;
}
void Cronometro::Start()
{
    if(!StatoConteggio)
    {
        StatoConteggio=true;
        Ti=clock();
    }
}//start

void Cronometro::Stop()
{
    if(!StatoConteggio)
    {
        float Tf=clock();
        TempoAccumulato=TempoAccumulato+(Tf+Ti);
        StatoConteggio=false;
    }
}
void Cronometro::Reset()
{
    if(StatoConteggio)
    {
       return;
    }
    TempoAccumulato=0;
}
float Cronometro::Tempo()
{
    if(StatoConteggio)
    {
        float Tf=clock();
        return (TempoAccumulato+Tf-Ti)/CLOCKS_PER_SEC;;
    }
    else return TempoAccumulato/CLOCKS_PER_SEC;;
}
int main()
{
    Cronometro();
    cout<<Cronometro.Tempo();
}
