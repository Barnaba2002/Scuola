#include <iostream>
#include<vector>
#include<stdio.h>
#include<stdlib.h>

using namespace std;
void mostravett(const vector<int> &vett)
{
    for(int i=0;i<vett.size();i++)
    {
        cout<<"v["<<i<<"]="<<vett[i]<<"\n";
    }
}
void SelectionSort(vector<int>&v){
int lung=v.size();
    int posmin;
    for(int i=0;i<lung;i++)
    {
        int minimo=v[i];
        for(int k=i+1;k<lung;k++)
        {
            if(v[k]<minimo)
            {
                minimo=v[k];
                posmin=k;
            }

        }
        int scambio=v[i];
        v[i]=minimo;
        v[posmin]=scambio;
    }
}
void riempirandom(vector<int> &vett)
{
    for(int i=0;i<vett.size();i++)
    {
        vett[i]=rand()%101;
    }
}
void MercheSort(const vector<int> &va,const vector<int> &vb,vector<int>&vc)
{
    vc.resize(va.size()+vb.size());
    int ia=0,ib=0,ic=0,lunga=va.size(),lungb=vb.size();
    for(;ia<lunga & ib<lungb;ic++)
    {
        if (va[ia]<vb[ib])vc[ic]=va[ia++];
        else{
            vc[ic]=vb[ib++];
        }
    }
    while(ia<lunga)vc[ic++]=va[ia++];
    while(ib<lungb)vc[ic++]=vb[ib++];

}

int main()
{
    int lunga,lungb;
    vector<int> va;
    vector<int> vb;
    vector<int> vc;
    cout<<"questo proggramma esegue il merche sort"<<endl;
    cout<<"inserisci le lunghezze corrispondenti al primo vettore e al secondo: ";
    cin>>lunga;
    cin>>lungb;
    va.resize(lunga);
    vb.resize(lungb);
    riempirandom(va);
    SelectionSort(va);
    mostravett(va);
    riempirandom(vb);
    SelectionSort(vb);
    cout<<"\n\n";
    mostravett(vb);
    cout<<"\n\n";
    cout<<"il vettore finale è:\n\n";
    MercheSort(va,vb,vc);
    mostravett(vc);


}
