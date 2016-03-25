/*
 * 
 * Autory :   Migaš Šimon
 * 			  Skirka ¼ubomír
 * 			  Sokol Kristián
 * 			  Stankoviè Tomáš
 * 
 */

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        for (int a: getaddress())
        {
            System.out.println(a);
        }
      //skuska z dec do bin
//		for(int a : fromDecToBin(2))
//		{
//			System.out.print(a);
//		}
		
		//skuska z bin do dec
//		int[] pole = {1,1,1,1,1,1,1,1};
//		System.out.println(fromBinToDec(pole));
		
		//skuska prefix do masky
//		for(int a : fromPrefixToMask(24))
//		{
//			System.out.print(a+".");
//		}
		
		//skuska maska do prefix
//		int[] maska = {255,192,0,0};
//		System.out.println(fromMaskToPrefix(maska));
		
		//skuska z prefixu na vildcard
		for(int a : fromPrefixToVildcard(24))
		{
			System.out.print(a + ".");
		}
		
		//skuska z masky na vildcard
//		int[] p = {255,255,255,0};
//		for(int b : fromMaskToVildcard(p))
//		{
//			System.out.print(b + ".");
//		

//skuska



    }
    //metod for get IPv4 address from user in console
	public static int[] getaddress()
    {
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
        String [] inout = kb.nextLine().split("\\.");
        try {
            int[] address = stringArrayToIntArray(inout);
            if(address.length == 4)
            {
                return address;
            }
            else
            {
            	System.out.println("Zadaj adresu so štyrmi oktetmi");
            	return getaddress();
            }
        }
        catch (Exception e)
        {
            System.out.println("Zadaj iba èísla");
            return getaddress();
        }

    }
    // metoda na pocitanie z desiatkovej do binarnej sústavy
    public static int[] fromDecToBin(int dec)
    {
        int bits = bits(dec);       // premena v ktorej je ulozeny pocet bitov daneho dec cisla
        int[] bin = new int[bits];  // pole ktoreho prvkz maju ulozene binarne cislo rovnajuce sa dec
        int prvok = bits - 1;
        int ex = dec;
        for(;prvok >= 0;prvok--)
        {
            bin[prvok] = ex %2;
            ex = ex / 2;
        }
        return bin;
    }
    // metoda na pocitanie z binárnej sústavy do desiatkovej
    public static int fromBinToDec(int[] bin)
    {
        int dec = 0;                                // Vysledne cislo v desiatkovej sustave
        int index = 0;
        for(int i = bin.length - 1; i >= 0; i--)
        {
            if(bin[i] == 1)
            {
                dec += mocnenie(2,index);
                index++;
            }
            else
                index++;
        }
        return dec;
    }
    // metoda na prevod z prefixu na masku
    public static int[] fromPrefixToMask(int prefix)
    {
        int[] mask = new int[4];  	// pole v ktorej je ulozena vysledna maska
        int full = prefix / 8;		// zistuje kolko octetov bude 255
        for(int i = 0; i < full;i++)
        {
            mask[i] = 255;
        }
        if(full < 4)
        {
            prefix = prefix - (full*8);		//od  celkoveho prefixu odpocita octety ktore uz su 255
            int[] activeOctet = new int[8];	// pole v ktorom su ulozene jednotky zvisneho prefixu
            for(int i = 0; i < prefix;i++)
            {
                activeOctet[i] = 1;
            }
            mask[full] = fromBinToDec(activeOctet);
        }

        return mask;
    }
    // metoda na prevod z masky na prefix
    public static int fromMaskToPrefix(int[] mask)
    {
        int prefix = 0;                     // prefix
        for(int i = 0; i < mask.length;i++)	//prechadza oktety masky
        {
            int[] pole = fromDecToBin(mask[i]);	// oktet masky sa konvertuje na bin cislo
            for(int a = 0; a < pole.length;a++)	// cyklus zistuje kolko jednotiek sa nachádza v danom oktete
            {
                if(pole[a] == 1)
                    prefix++;
                else
                    return prefix;
            }
        }
        return prefix;
    }
    // metoda ktora z prefixu uroby vildcard
    public static int[] fromPrefixToVildcard(int prefix)
    {
    	int[] maska = fromPrefixToMask(prefix);         // pole v ktorom je konvertovany prefix na masku
		int[] vildcard = fromMaskToVildcard(maska);     // pole v ktorom je ulozeny vildcard
		return vildcard;
    }
    // metoda ktora z masky uroby vildcard
    public static int[] fromMaskToVildcard(int[] maska)
    {
        int[] vildcard = new int[4];      // pole v ktorom je ulozeny vildcard
        int prvok = 0;
        for(int a : maska)
        {
            vildcard[prvok] = 255 - a;
            prvok++;
        }
        return vildcard;
    }
    // mocninátor
    public static int mocnenie(int zaklad,int index)
    {
        int out = 0;
        for(int i = 0; i <= index; i++)
        {
            if( i == 0)
                out = 1;
            else
                out = out * zaklad;
        }
        return out;
    }
    // metoda ktorá zisuje kolko bytov ma jedno èislo
    public static int bits(int dec)
    {
        if (dec == 0)
            return 1;
        for(int index = 0;;index++)
        {
            int f = mocnenie(2,index);   // f = vysledok mocnenia cisla 2 indexom
            if(dec / f == 0)
            {
                return index;
            }
        }
    }
    public static int[] stringArrayToIntArray(String[] array)
    {
        int[] arrayint = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            arrayint[i] = Integer.parseInt(array[i]);
        }
        return arrayint;
    }

}