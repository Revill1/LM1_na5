package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TicketAutomat
{
    private static HashMap<String, Stan> mapaStanow = new HashMap<String, Stan>();

    public static void main(String[] args)
    {
        System.out.println("Witaj! Wrzuć monety (1,2,5) aby kupić bilet. Dostępne bilety:9zł - 1h pływalnia, 12zł - 1h pływalnia z sauną, 15zł - 2h pływalnia, 20zł - 2h pływalnia z sauną");
        System.out.println("Aby otrzymać zwrot monet, wpisz Z. Aby kupić bilet wpisz K, Aby wybrać 2h bilet na basen wpisz B2");
        Scanner input = new Scanner(System.in);
        String value = "";
        do
        {
            System.out.println("Wrzuc monetę: ");
            value = input.next();

        }
        while (!value.equals("K"));

    }

    private static HashMap<String, Stan> inicjujDane()
    {
        String wartZM = "ZM";
        String wartB = "B";
        String wartBR1 = "BR1";
        String wartBR2 = "BR2";
        String wartBS = "BS";
        String wartBSR1 = "BSR1";
        String wartBSR2 = "BSR2";
        String wartBSR3 = "BSR3";
        String wartB2H = "B2H";
        String wartB2HR1 = "B2HR1";
        String wartB2HR2 = "B2HR2";
        String wartB2HR3 = "B2HR3";
        String wartB2HR4 = "B2HR4";
        String wartBS2H = "BS2H";
        String koniec = "K";
        String basen2h = "B2h";
        String zwrotMonet = "Z";
        HashMap<String, Stan> mapaNowychStanow = new HashMap<String, Stan>();

        ustawWstepnePrzejscia(mapaNowychStanow);

        mapaNowychStanow.get("q0").mapaPrzejsc.put(wartZM, "q0");
        mapaNowychStanow.get("q0").mapaPrzejsc.put(koniec, "q0");
        mapaNowychStanow.get("q0").mapaPrzejsc.put(wartB2H, "q0");

        for (int i = 1; i < 20; i++)
        {
            mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, "q" + i);
            if (i >= 9)
            {
                przejsciaDlaWartosciKonczacych(mapaNowychStanow, i);
            } else
            {
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, "q" + i);
            }
            mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
        }

        przejsciaDlaStanowKonczacych(mapaNowychStanow);

        System.out.println(mapaNowychStanow);

        return mapaNowychStanow;
    }

    private static void przejsciaDlaStanowKonczacych(HashMap<String, Stan> mapaNowychStanow)
    {
        String wartZM = "ZM";
        String wartB = "B";
        String wartBR1 = "BR1";
        String wartBR2 = "BR2";
        String wartBS = "BS";
        String wartBSR1 = "BSR1";
        String wartBSR2 = "BSR2";
        String wartBSR3 = "BSR3";
        String wartB2H = "B2H";
        String wartB2HR1 = "B2HR1";
        String wartB2HR2 = "B2HR2";
        String wartB2HR3 = "B2HR3";
        String wartB2HR4 = "B2HR4";
        String wartBS2H = "BS2H";
        String koniec = "K";
        String basen2h = "B2h";
        String zwrotMonet = "Z";
        ArrayList<String> tablicaStanowKonczacych = new ArrayList<String>();
        tablicaStanowKonczacych.add(wartZM);
        tablicaStanowKonczacych.add(wartB);
        tablicaStanowKonczacych.add(wartB2H);
        tablicaStanowKonczacych.add(wartB2HR1);
        tablicaStanowKonczacych.add(wartB2HR2);
        tablicaStanowKonczacych.add(wartB2HR3);
        tablicaStanowKonczacych.add(wartB2HR4);
        tablicaStanowKonczacych.add(wartBR1);
        tablicaStanowKonczacych.add(wartBR2);
        tablicaStanowKonczacych.add(wartBS);
        tablicaStanowKonczacych.add(wartBS2H);
        tablicaStanowKonczacych.add(wartBSR1);
        tablicaStanowKonczacych.add(wartBSR2);
        tablicaStanowKonczacych.add(wartBSR3);

        for (String stan : tablicaStanowKonczacych)
        {
            HashMap<String, String> mapka = new HashMap<String, String>();
            mapka.put("1", stan);
            mapka.put("2", stan);
            mapka.put("5", stan);
            mapka.put(koniec, stan);
            mapka.put(zwrotMonet, stan);
            mapka.put(basen2h, stan);
            mapaNowychStanow.put(stan, new Stan(stan, mapka));
        }
    }

    private static void przejsciaDlaWartosciKonczacych(HashMap<String, Stan> mapaNowychStanow, int i)
    {
        String wartZM = "ZM";
        String wartB = "B";
        String wartBR1 = "BR1";
        String wartBR2 = "BR2";
        String wartBS = "BS";
        String wartBSR1 = "BSR1";
        String wartBSR2 = "BSR2";
        String wartBSR3 = "BSR3";
        String wartB2H = "B2H";
        String wartB2HR1 = "B2HR1";
        String wartB2HR2 = "B2HR2";
        String wartB2HR3 = "B2HR3";
        String wartB2HR4 = "B2HR4";
        String wartBS2H = "BS2H";
        String koniec = "K";
        String basen2h = "B2h";
        String zwrotMonet = "Z";

        switch (i)
        {
            case 9:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartB);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 10:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartBR1);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 11:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartBR2);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 12:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartBS);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 13:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartBSR1);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 14:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartBSR2);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 15:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartBSR3);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, basen2h);
                break;
            case 16:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartB2HR1);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 17:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartB2HR2);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 18:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartB2HR3);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 19:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartB2HR4);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
            case 20:
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(koniec, wartBS2H);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartZM, zwrotMonet);
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(wartB2H, "q" + i);
                break;
        }
    }

    private static void ustawWstepnePrzejscia(HashMap<String, Stan> mapaStanow)
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
    }
}
