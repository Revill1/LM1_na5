package com.company;

import java.util.*;

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
    private static HashSet<String> STANY_KONCZACE = new HashSet<String>(
            Arrays.asList(WART_B, WART_B2H, WART_B2HR1, WART_B2HR2, WART_B2HR3, WART_B2HR4, WART_BR1,
                    WART_BR2, WART_BS, WART_BS2H, WART_BSR1, WART_BSR2, WART_BSR3, WART_BS2H, ZWROT_MONET,BASEN_2H));

    public static void main(String[] args)
    {
        System.out.println("Witaj! Wrzuć monety (1,2,5) aby kupić bilet. Dostępne bilety:9zł - 1h pływalnia, 12zł - 1h pływalnia z sauną, 15zł - 2h pływalnia, 20zł - 2h pływalnia z sauną");
        System.out.println("Aby otrzymać zwrot monet, wpisz Z. Aby kupić bilet wpisz K, Aby wybrać 2h bilet na basen wpisz B2");
        Scanner input = new Scanner(System.in);
        String wartosc = "";
        mapaStanow = inicjujDane();

        do
        {
            if( AKTUALNY_STAN.equals("q9") || AKTUALNY_STAN.equals("q10") || AKTUALNY_STAN.equals("q11") )
            {
                System.out.println("Wciśnij 'K' aby kupić bilet 1h na basen");
                System.out.println("Wciśnij 'ZM' aby otrzymać zwrot monet. Wrzuc monetę jesli chcesz kontynuować: ");
            }
            else if (AKTUALNY_STAN.equals("q12") || AKTUALNY_STAN.equals("q13") || AKTUALNY_STAN.equals("q14"))
            {
                System.out.println("Wciśnij 'K' aby kupić bilet 1h na basen i saunę");
                System.out.println("Wciśnij 'ZM' aby otrzymać zwrot monet. Wrzuc monetę jesli chcesz kontynuować: ");
            }
            else if(AKTUALNY_STAN.equals("q15"))
            {
                System.out.println("Wciśnij 'K' aby kupić bilet 1h na basen + saunę i otrzymać resztę");
                System.out.println("Wciśnij " + WART_B2H + " aby kupić bilet 2h na basen ");
                System.out.println("Wciśnij 'ZM' aby otrzymać zwrot monet. Wrzuc monetę jesli chcesz kontynuować: ");
            }
            else if(AKTUALNY_STAN.equals("q16") || AKTUALNY_STAN.equals("q17") || AKTUALNY_STAN.equals("q18") || AKTUALNY_STAN.equals("q19") || AKTUALNY_STAN.equals("q20"))
            {
                System.out.println("Wciśnij 'K' aby kupić bilet 2h na basen i otrzymać resztę");
                System.out.println("Wciśnij 'ZM' aby otrzymać zwrot monet. Wrzuc monetę jesli chcesz kontynuować: ");
            }
            else if (!AKTUALNY_STAN.equals("q0"))
            {
                System.out.println("Wciśnij 'ZM' aby otrzymać zwrot monet. Wrzuc monetę: ");
            }
            else
            {
                System.out.println("Wrzuc monetę: ");
            }

            wartosc = input.next();
            if(wartosc != null)
            {
                wartosc = wartosc.toUpperCase();
            }
            przejdzDoStanu(wartosc, AKTUALNY_STAN);
        }
        while (!STANY_KONCZACE.contains(AKTUALNY_STAN));

        System.out.println("Odwiedzone stany:" + LISTA_ODWIEDZONYCH_STANOW);
        System.out.println("Stan koncowy:" + LISTA_ODWIEDZONYCH_STANOW.get(LISTA_ODWIEDZONYCH_STANOW.size() - 1));
        System.out.println("Wrzucone monety:" + ILOSC_MONET);
    }

    private static void przejdzDoStanu(String wartosc, String stan)
    {

        Stan aktualnyStan = mapaStanow.get(stan);
        if (aktualnyStan != null)
        {
            String nowyStan = aktualnyStan.getMapaPrzejsc().get(wartosc);
            if (nowyStan != null)
            {

                if (WART_ZM.equals(wartosc))
                {
                    ILOSC_MONET = 0;
                } else if ("1".equals(wartosc) || "2".equals(wartosc) || "5".equals(wartosc))
                {
                    ILOSC_MONET += Integer.parseInt(wartosc);
                }

                LISTA_ODWIEDZONYCH_STANOW.add(nowyStan);
                AKTUALNY_STAN = nowyStan;
                System.out.println("aktualny stan: " + nowyStan);
                System.out.println("Wrzucono: " + ILOSC_MONET);
            }
        }
    }

    private static HashMap<String, Stan> inicjujDane()
    {
        HashMap<String, Stan> mapaNowychStanow = new HashMap<String, Stan>();

        mapaNowychStanow = ustawWstepnePrzejscia(mapaNowychStanow);

        mapaNowychStanow.get("q0").mapaPrzejsc.put(WART_ZM, "q0");
        mapaNowychStanow.get("q0").mapaPrzejsc.put(KONIEC, "q0");
        mapaNowychStanow.get("q0").mapaPrzejsc.put(WART_B2H, "q0");

        for (int i = 1; i < 21; i++)
        {
            mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_ZM, ZWROT_MONET);
            mapaNowychStanow.get("q" + i).mapaPrzejsc.put(WART_B2H, "q" + i);
            if (i >= 9)
            {
                mapaNowychStanow = przejsciaDlaWartosciKonczacych(mapaNowychStanow, i);
            } else
            {
                mapaNowychStanow.get("q" + i).mapaPrzejsc.put(KONIEC, "q" + i);
            }
        }

        mapaNowychStanow = przejsciaDlaStanowKonczacych(mapaNowychStanow);
        return mapaNowychStanow;
    }

    private static HashMap<String, Stan> przejsciaDlaStanowKonczacych(HashMap<String, Stan> mapaNowychStanow)
    {
        for (String stan : STANY_KONCZACE)
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
        return mapaNowychStanow;
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
        for (int i = 0; i < 21; i++)
        {
            String nazwaStanu = "q" + i;
            HashMap<String, String> mapaPrzejsc = new HashMap<String, String>();
            int indeksAdd1 = i + 1;
            int indeksAdd2 = i + 2;
            int indeksAdd5 = i + 5;

            mapaPrzejsc.put("1", "q" + indeksAdd1);
            if (i == 20)
            {
                mapaPrzejsc.put("1", nazwaStanu);
            }

            if (i < 18)
            {
                mapaPrzejsc.put("2", "q" + indeksAdd2);
            } else
            {
                mapaPrzejsc.put("2", nazwaStanu);
            }

            if (i < 16)
            {
                mapaPrzejsc.put("5", "q" + indeksAdd5);
            } else
            {
                mapaPrzejsc.put("5", nazwaStanu);
            }

            mapaStanow.put(nazwaStanu, new Stan(nazwaStanu, mapaPrzejsc));
        }

        return mapaStanow;
    }
}
