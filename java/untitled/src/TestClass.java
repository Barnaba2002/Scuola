/**
 * @author Alberto Basaglia , Barnaba Rallo , Leonardo Campagnol
 * @date 30/01/2018
 * @version 1.0
 * Il programma , composto da 2 classi , permette un confronto visivo delle risorse utilizzate dai vari ordinamenti.
 * */
import java.awt.*;
import java.lang.Math.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import zuclib.*;
import static zuclib.GraficaSemplice.*;
class Contatore
{
    public long confronti;
    public long assegnamenti;
    /**
     * costruttore della classe , imposta a 0 i contatori
     * */
    public Contatore()
    {
        confronti = 0;
        assegnamenti = 0;
    }
    /**
     * metodo che azzera i contatori
     * */
    public void reset()
    {
        confronti = 0;
        assegnamenti = 0;
    }
    /**
     * Il metodo permette di ritornare una stringa che contiene gli assegnamenti e i confronti
     * @return stringa da ritornare
     * */
    @Override
    public String toString()
    {
        return "Confronti : "+confronti+" Assegnamenti: "+assegnamenti;
    }
}
public class TestClass
{
    /**
     * Il metodo ordina un array utilizzando l'algoritmo bubble sort
     * @param array è il vettore da ordinare
     * @param c riferimento all'oggetto contatore che permette di salvare gli assegnamenti e i confronti che sono stati fatti
     * */
    public static void bubble(int[] array,Contatore c)
    {
        c.reset();
        for(int i = 0; i < array.length; i++) {
            boolean flag = false;
            for(int j = 0; j < array.length-1; j++) {
                c.confronti++;
                if(array[j]>array[j+1]) {
                    c.assegnamenti+=3;
                    int k = array[j];
                    array[j] = array[j+1];
                    array[j+1] = k;
                    flag=true;
                }
            }
            if(!flag) break;
        }
    }
    /**
     * Il metodo ordina un array utilizzando l'algoritmo selection sort
     * @param array è il vettore da ordinare
     * @param c riferimento all'oggetto contatore che permette di salvare gli assegnamenti e i confronti che sono stati fatti
     * */
    public static void selection(int[] array,Contatore c)
    {
        c.reset();
        int posmin = 0;
        for(int i=0;i<array.length;i++)
        {
            int minimo = array[i];
            c.assegnamenti++;
            for(int k = i+1;k<array.length;k++)
            {
                c.confronti++;
                if(array[k]<minimo)
                {
                    c.assegnamenti+=2;
                    minimo = array[k];
                    posmin = k;
                }
            }
            c.assegnamenti+=3;
            int scambio = array[i];
            array[i] = minimo;
            array[posmin] = scambio;
        }
    }
    /**
     * Il metodo permette di caricare un array casualmente fornendo il suo seed
     * @param n elementi da caricare nel vettore
     * @param seed seed da utilizzare per la generazione casuale, se viene inserito 0 il seed non sarà inizializzato
     * @return il vettore caricato
     * */
    public static int[] caricaRand(int n,long seed)
    {
        int[] newarray = new int[n];
        Random r = new Random();
        if(seed!=0)
        {
            r.setSeed(seed);
        }
        for(int i=0;i<n;i++)
        {
            newarray[i] = r.nextInt(1001);
        }
        return newarray;
    }
    /**
     * Il metodo permette di caricare un array sequenzialmente
     * @param n elementi da caricare nel vettore
     * @param reverse se TRUE il vettore sarà caricato al contrario
     * @return il vettore caricato
     * */
    public static int[] caricaSeq(int n,boolean reverse)
    {
        int[] newarray = new int[n];
        if(reverse)
        {
            for(int i=0;i<n;i++)
            {
                newarray[i] = n-i-1;
            }
        }else{
            for(int i=0;i<n;i++)
            {
                newarray[i] = i;
            }
        }

        return newarray;
    }
    /**
     * La funzione scrive a schermo il vettore
     * @param array il vettore da mostrare
     * */
    public static void mostra(int[] array)
    {
        for(int i = 0;i<array.length;i++)
        {
            System.out.println(array[i]);
        }
    }
    public static double size = 600;
    /**
     * Il metodo disegna gli assi necessari per il grafico
     * @param t riferimento alla tartaruga che verrà utilizzata per i disegni
     * */
    public static void faiAssi(Tartaruga t)
    {
        t.pennaSu();
        t.gotoXY(0.15,0);
        t.pennaGiu();
        t.gotoXY(0.15,1);
        t.pennaSu();
        t.gotoXY(0,0.15);
        t.pennaGiu();
        t.gotoXY(1,0.15);
    }
    /**
     * Il metodo ordina un array utilizzando l'algoritmo quick ricorsivo
     * @param vec vettore da ordinare
     * @param inf limite inferiore
     * @param sup limite superiore
     * @param c riferimento all'oggetto contatore che permette di salvare gli assegnamenti e i confronti che sono stati fatti
     * */
    public static void quickSort(int vec[],int inf,int sup,Contatore c)
    {

        int i = inf;
        int s = sup;
        int elem;
        elem = vec[(i+s)/2];
        c.assegnamenti+=3;
        do
        {
            while(vec[i]<elem)
            {
                i++;
                c.confronti++;
            }
            while(elem<vec[s])
            {
                s--;
                c.confronti++;
            }
            c.confronti++;
            if(i<s)
            {
                int buff = vec[i];
                vec[i] = vec[s];
                vec[s] = buff;
                i++;
                s--;
                c.assegnamenti+=3;
            }
            else if(i==s)
            {
                i++;
                s--;
            }
            c.confronti++;
        }while(i<=s);
        c.confronti++;
        if(inf<s) quickSort(vec,inf,s,c);
        c.confronti++;
        if(i< sup) quickSort(vec,i,sup,c);
    }
    /**
     * Metodo per semplificare l'accesso all'algoritmo ricorsivo di quicksort
     * @param vec vettore da ordinare
     * @param c riferimento all'oggetto contatore che permette di salvare gli assegnamenti e i confronti che sono stati fatti
     * */
    public static void quickSort(int[] vec,Contatore c)
    {
        c.reset();
        quickSort(vec,0,vec.length-1,c);
    }
    public static void main(String[] args)
    {
        Scanner key = new Scanner(System.in); //oggetto Scanner per leggere da tastiera
        int metodoConfronti=0;
        do {
            System.out.print("Fase 1)\nScelta del metodo di confronto\n1)Confronti\n2)Assegnamenti\nInserisci Scelta: ");
            metodoConfronti=key.nextInt();
        }while(metodoConfronti!=1&&metodoConfronti!=2);
        int metodoOrdinamento=0;
        do {
            System.out.print("Fase 2)\nScelta del metodo di ordinamento\n1)Casuale\n2)Già ordinato\n3)Ordinato al contrario\nInserisci Scelta: ");
            metodoOrdinamento=key.nextInt();
        }while(metodoOrdinamento!=1&&metodoOrdinamento!=2&&metodoOrdinamento!=3);
        setFinestra(900,900,"Comparazione Confronti e Assegnamenti di vari ordinamenti.");
        Tartaruga t = new Tartaruga();
        faiAssi(t);
        int[] vettore;
        Contatore c = new Contatore();
        t.pennaSu();
        t.gotoXY(0.15,0.15);
        t.pennaGiu();


        //questo pezzo di codice serve per fornire una descrizione a schermo su quello che si sta facendo
        String daScrivere = "";
        if(metodoConfronti==1){
            daScrivere+="Comparazione dei Confronti effettuati dai diversi ordinamenti utilizzando vettori ";
        }else{
            daScrivere+="Comparazione degli Assegnamenti effettuati dai diversi ordinamenti utilizzando vettori ";
        }
        if(metodoOrdinamento==1){
            daScrivere+="caricati casualmente.";
        }else if(metodoOrdinamento==2){
            daScrivere+="caricati già ordinati.";
        }else{
            daScrivere+="caricati ordinati al contrario.";
        }
        testo(0.5,0.92,daScrivere,0,NERO);

        //inizializzate variabili per disegnare il grafico
        double xprecedente;
        double yprecedente;
        double x;
        double y;
        xprecedente = 0.15;
        yprecedente = 0.15;

        //aggiunte informazioni a schermo
        testo(0.575,0.05,"Elementi nel vettore",0,NERO);
        if(metodoConfronti==1)
            testo(0.03,0.575,"Confronti",-90,NERO);
        else
            testo(0.03,0.575,"Assegnamenti",-90,NERO);

        //inizio del codice per calcolare il selection sort
        setColore(BLU);
        testo(0.07,0.3,"Selection Sort",0,BLU);
        for(int i=1;i<=40;i++)
        {
            //a seconda dei tipi di confronto viene caricato un diverso vettore
            if(metodoOrdinamento==1) {
                vettore = caricaRand(500 * i, 2000);
            }else if(metodoOrdinamento==2){
                vettore = caricaSeq(500*i,false);
            }else{
                vettore = caricaSeq(500*i,true);
            }
            long paragone;
            double scala=0.0000000015;
            selection(vettore,c);
            //a seconda dei tipi di confronto viene impostata una diversa scala
            if(metodoConfronti==1){
                paragone = c.confronti;
            }else{
                paragone = c.assegnamenti;
                if(metodoOrdinamento==2){
                    scala = 0.000005;
                }else {
                    scala = 0.000000015;
                }
            }
            //viene tracciata a schermo la linea
            x = 0.15+(0.02*i);
            y = 0.15+paragone*scala;
            linea(xprecedente,yprecedente,x,y);
            xprecedente = x;
            yprecedente = y;
            setColore(NERO);
            if(i%5==0)
            {
                testo(x,0.1,""+500*i,-90,NERO);
                linea(x,0.14,x,1);
                cerchio(x,y,0.005);
                testo(x+0.1,y,""+paragone,0,BLU);
            }
            setColore(BLU);
        }

        //inizio del codice per calcolare il bubble sort
        xprecedente = 0.15;
        yprecedente = 0.15;
        setColore(ROSSO);
        testo(0.07,0.25,"Bubble Sort",0,ROSSO);
        for(int i=1;i<=40;i++)
        {
            if(metodoOrdinamento==1) {
                vettore = caricaRand(500 * i, 2000);
            }else if(metodoOrdinamento==2){
                vettore = caricaSeq(500*i,false);
            }else{
                vettore = caricaSeq(500*i,true);
            }
            long paragone;
            double scala=0.0000000015;
            bubble(vettore,c);
            if(metodoConfronti==1){
                paragone = c.confronti;
            }else{
                paragone = c.assegnamenti;
                if(metodoOrdinamento==2){
                    scala = 0.000005;
                }else {
                    scala = 0.000000015;
                }
            }
            x = 0.15+(0.02*i);
            y = 0.15+paragone*scala;
            linea(xprecedente,yprecedente,x,y);
            xprecedente = x;
            yprecedente = y;
            if(i%5==0){
                testo(x-0.05,y,""+paragone,0,ROSSO);
                cerchio(x,y,0.005);
            }
        }

        //inizio del codice per calcolare il quick
        xprecedente = 0.15;
        yprecedente = 0.15;
        setColore(VERDE);
        testo(0.07,0.2,"Quick Sort",0,VERDE);
        for(int i=1;i<=40;i++)
        {
            if(metodoOrdinamento==1) {
                vettore = caricaRand(500 * i, 2000);
            }else if(metodoOrdinamento==2){
                vettore = caricaSeq(500*i,false);
            }else{
                vettore = caricaSeq(500*i,true);
            }
            long paragone;
            double scala=0.0000000015;
            quickSort(vettore,c);
            if(metodoConfronti==1){
                paragone = c.confronti;
            }else{
                paragone = c.assegnamenti;
                if(metodoOrdinamento==2){
                    scala = 0.000005;
                }else {
                    scala = 0.000000015;
                }
            }
            x = 0.15+(0.02*i);
            y = 0.15+paragone*scala;
            linea(xprecedente,yprecedente,x,y);
            xprecedente = x;
            yprecedente = y;
            if(i%5==0) cerchio(x,y,0.005);
            if(i%20==0){
                testo(x,y+0.015,""+paragone,0,VERDE);
            }
        }
    }
}