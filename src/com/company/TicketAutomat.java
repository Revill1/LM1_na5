package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TicketAutomat
{
    private static HashMap<String, Stan> mapaStanow = new HashMap<String, Stan>();
    private static final String WART_ZM = "ZM";
    private static final String WART_B = "B";
    private static final String WART_BR1 = "BR1";
    private static final String WART_BR2 = "BR2";
    private static final String WART_BS = "BS";
    private static final String WART_BSR1 = "BSR1";
    private static final String WART_BSR2 = "BSR2";
    private static final String WART_BSR3 = "BSR3";
    private static final String WART_B2H = "B2H";
    private static final String WART_B2HR1 = "B2HR1";
    private static final String WART_B2HR2 = "B2HR2";
    private static final String WART_B2HR3 = "B2HR3";
    private static final String WART_B2HR4 = "B2HR4";
    private static final String WART_BS2H = "BS2H";
    private static final String KONIEC = "K";
    private static final String BASEN_2H = "B2h";
    private static final String ZWROT_MONET = "Z";
    private static ArrayList<String> LISTA_ODWIEDZONYCH_STANOW = new ArrayList<String>();
    private static String AKTUALNY_STAN = "q0";
    private static int ILOSC_MONET = 0;

    public static void main(String[] args)
    {
        System.out.println("Witaj! Wrzuć monety (1,2,5) aby kupić bilet. Dostępne bilety:9zł - 1h pływalnia, 12zł - 1h pływalnia z sauną, 15zł - 2h pływalnia, 20zł - 2h pływalnia z sauną");
        System.out.println("Aby otrzymać zwrot monet, wpisz Z. Aby kupić bilet wpisz K, Aby wybrać 2h bilet na basen wpisz B2");
        Scanner input = new Scanner(System.in);
        String wartosc = "";
        mapaStanow = inicjujDane();

        do
        {
            System.out.println("Wrzuc monetę: ");
            wartosc = input.next();
            przejdzDoStanu(wartosc, AKTUALNY_STAN);

        }
        while (!wartosc.equals("K"));
        System.out.println("Odwiedzone stany:" + LISTA_ODWIEDZONYCH_STANOW);
        System.out.println("Stan koncowy:" + LISTA_ODWIEDZONYCH_STANOW.get(LISTA_ODWIEDZONYCH_STANOW.size() - 1));
        System.out.println("Wrzucone monety:" + ILOSC_MONET);


    }

    private static void przejdzDoStanu(String wartosc, String stan)
    {

        Stan aktualnyStan = mapaStanow.get(stan);
        System.out.println("mapaStanow stan: " + mapaStanow);

        if (aktualnyStan != null)
        {
            String nowyStan = aktualnyStan.getMapaPrzejsc().get(wartosc);
            if(WART_ZM.equals(wartosc))
            {
                ILOSC_MONET = 0;
            }
            else if ("1".equals( wartosc ) || "2".equals( wartosc ) || "5".equals(wartosc))
            {
                ILOSC_MONET += Integer.parseInt(wartosc);
            }

            LISTA_ODWIEDZONYCH_STANOW.add(nowyStan);
            AKTUALNY_STAN = nowyStan;
            System.out.println("aktualny stan: " + nowyStan);
            System.out.println("Wrzucono: " + ILOSC_MONET);


        }
    }

    private static HashMap<String, Stan> inicjujDane()
    {
        HashMap<String, Stan> mapaNowychStanow = new HashMap<String, Stan>();

        mapaNowychStanow = ustawWstepnePrzejscia(mapaNowychStanow);

        mapaNowychStanow.get("q0").mapaPrzejsc.put(WART_ZM, "q0");
        mapaNowychStanow.get("q0").mapaPrzejsc.put(KONIEC, "q0");
        mapaNowychStanow.get("q0").mapaPrzejsc.put(WART_B2H, "q0");

        for (int i = 1; i < 20; i++)
        {
            mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, "q" + i);
            if (i >= 9)
            {
                mapaNowychStanow =  przejsciaDlaWartosciKonczacych(mapaNowychStanow, i);
            } else
            {
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, "q" + i);
            }
            mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
        }

        mapaNowychStanow = przejsciaDlaStanowKonczacych(mapaNowychStanow);

        System.out.println(mapaNowychStanow);

        return mapaNowychStanow;
    }

    private static HashMap<String, Stan> przejsciaDlaStanowKonczacych(HashMap<String, Stan> mapaNowychStanow)
    {
        ArrayList<String> tablicaStanowKonczacych = new ArrayList<String>();
        tablicaStanowKonczacych.add(WART_ZM);
        tablicaStanowKonczacych.add(WART_B);
        tablicaStanowKonczacych.add(WART_B2H);
        tablicaStanowKonczacych.add(WART_B2HR1);
        tablicaStanowKonczacych.add(WART_B2HR2);
        tablicaStanowKonczacych.add(WART_B2HR3);
        tablicaStanowKonczacych.add(WART_B2HR4);
        tablicaStanowKonczacych.add(WART_BR1);
        tablicaStanowKonczacych.add(WART_BR2);
        tablicaStanowKonczacych.add(WART_BS);
        tablicaStanowKonczacych.add(WART_BS2H);
        tablicaStanowKonczacych.add(WART_BSR1);
        tablicaStanowKonczacych.add(WART_BSR2);
        tablicaStanowKonczacych.add(WART_BSR3);

        for (String stan : tablicaStanowKonczacych)
        {
            HashMap<String, String> mapka = new HashMap<String, String>();
            mapka.put("1", stan);
            mapka.put("2", stan);
            mapka.put("5", stan);
            mapka.put(KONIEC, stan);
            mapka.put(ZWROT_MONET, stan);
            mapka.put(BASEN_2H, stan);
            mapaNowychStanow.put(stan, new Stan(stan, mapka));
        }
        return  mapaNowychStanow;
    }

    private static HashMap<String, Stan> przejsciaDlaWartosciKonczacych(HashMap<String, Stan> mapaNowychStanow, int i)
    {
        switch (i)
        {
            case 9:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_B);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 10:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_BR1);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 11:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_BR2);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 12:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_BS);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 13:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_BSR1);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 14:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_BSR2);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 15:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_BSR3);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, BASEN_2H);
                break;
            case 16:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_B2HR1);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 17:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_B2HR2);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 18:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_B2HR3);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 19:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_B2HR4);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
            case 20:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, WART_BS2H);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
                break;
        }

        return mapaNowychStanow;
    }

    private static HashMap<String, Stan> ustawWstepnePrzejscia(HashMap<String, Stan> mapaStanow)
    {
        for (int i = 0; i < 20; i++)
        {
            String nazwaStanu = "q" + i;
            HashMap<String, String> mapaPrzejsc = new HashMap<String, String>();
            mapaPrzejsc.put("1", "q" + 1);
            if (i < 18)
            {
                mapaPrzejsc.put("2", "q" + 2);
            } else
            {
                mapaPrzejsc.put("2", nazwaStanu);
            }

            if (i < 15)
            {
                mapaPrzejsc.put("5", "q" + 5);
            } else
            {
                mapaPrzejsc.put("5", nazwaStanu);
            }
            mapaStanow.put(nazwaStanu, new Stan(nazwaStanu, mapaPrzejsc));
        }

        return mapaStanow;
    }
}
