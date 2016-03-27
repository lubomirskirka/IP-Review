/*
 * 
 * Autory :   Miga� �imon
 * 			  Skirka �ubom�r
 * 			  Sokol Kristi�n
 * 			  Stankovi� Tom�
 * 
 */

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Vitajte v aplikácii Počty");
        menu();

    }
    public static void menu()
    {
        Scanner kb = new Scanner(System.in);

        System.out.println("Funkcie:");
        System.out.println("    1 Prevod medzi sústavami");
        System.out.println("    2 Maska/Prefix");
        System.out.println("    3 IP Review");
        System.out.print("Vyberte funkciu: ");
        int y = kb.nextInt();

        switch (y)
        {
            case 1:
                convertUI();
                break;
            case 2:
                System.out.println("    2 Maska/Prefix");
                break;
            case 3:
                System.out.println("    3 IP Review");
                break;
            default:
                System.out.println("Zadaj číslo fukcie");
                menu();
                break;

        }
    }
    public static void convertUI()
    {
        Scanner kb2 = new Scanner(System.in);
        System.out.println("Z ktorej sústavy chceš prevádzať:");
        System.out.println("    1 Binarna");
        System.out.println("    2 Decimálna");
        int y = kb2.nextInt();
        System.out.println("Do ktorej sústavy chceš prevádzať:");
        System.out.println("    1 Binarna");
        System.out.println("    2 Decimálna");
        int u = kb2.nextInt();
        switch (y)
        {
            case 1:
                System.out.print("Zadaj binarne číslo: ");
                break;
            case 2:
                System.out.print("Zadaj decimalne číslo: ");
                break;
            default:
                System.out.println("Zlý vstup");
                convertUI();
        }
    }
    //metod for get IPv4 address from user in console
	public static int[] getAddress()
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
            	System.out.println("Zadaj adresu so �tyrmi oktetmi");
            	return getAddress();
            }
        }
        catch (Exception e)
        {
            System.out.println("Zadaj iba ��sla");
            return getAddress();
        }

    }
    // metoda na pocitanie z desiatkovej do binarnej s�stavy
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
    // specialna metoda na pocitanie z desiatkovej do binarnej (8 bitov) s�stavy
    public static int[] fromDecToBin8(int dec)
    {
    	int[] bin = fromDecToBin(dec);
    	int[] out = new int[8];
    	int read = 0;
    	for(int i = 8 - bin.length; i < out.length; i++)
    	{
    		out[i] = bin[read];
    		read++;
    	}
    	return out;
    }
    // metoda na pocitanie z bin�rnej s�stavy do desiatkovej
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
            for(int a = 0; a < pole.length;a++)	// cyklus zistuje kolko jednotiek sa nach�dza v danom oktete
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
    // metoda na ziskanie sietovej alebo broadcastovej adresy
    public static int[] getNetOrBroAddress(int[] address,int prefix,boolean net)
    {
    	if(prefix == 32)
    		return address;
    	int[] octet1 = fromDecToBin8(address[0]);
    	int[] octet2 = fromDecToBin8(address[1]);
    	int[] octet3 = fromDecToBin8(address[2]);
    	int[] octet4 = fromDecToBin8(address[3]);
    	int fromThisPrefix = (prefix == 32) ? 4 : prefix / 8 + 1; 	// vypocita v ktorom oktete sa za�inaju meni� 0/1 
    	int border = prefix - (prefix / 8)*8;						// hranica zmeny 0/1 v prvom upravujucom sa oktete
    	
    	changeOctet(octet1, fromThisPrefix, 1, border, net);
    	changeOctet(octet2, fromThisPrefix, 2, border, net);
    	changeOctet(octet3, fromThisPrefix, 3, border, net);
    	changeOctet(octet4, fromThisPrefix, 4, border, net);
    	
    	int[] outAddress = new int[4];
    	outAddress[0] = fromBinToDec(octet1);
    	outAddress[1] = fromBinToDec(octet2);
    	outAddress[2] = fromBinToDec(octet3);
    	outAddress[3] = fromBinToDec(octet4);
    	return outAddress;
    }
    // metoda na opravu octetu pre getNetOrBroAddress
    public static void changeOctet(int[] octet,int fromThisPrefix,int numOctet,int border,boolean net)
    {
    	if(fromThisPrefix <= numOctet)			// numOctet - cislo prave upravujuceho sa oktetu
    	{
    		border = (fromThisPrefix == numOctet) ? border : 0;
    		for(int i = border;i < 8;i++)
    		{
    			octet[i] = (net) ? 0 : 1;		// menia sa 1/0
    		}
    	}
    }
    // metoda na zis�ovanie po�tu adries
    public static int getSpaceAddress(int prefix)
    {
    	int spaceAddress;
    	spaceAddress = mocnenie(2,32 - prefix);
    	return spaceAddress;	
    }
    // metoda na zistenie prvej a poslednej pou�ite�nej adresy
    public static int[] getFirstOrLastAddress(int[] address,int prefix,boolean or)
    {
    	int[] net = getNetOrBroAddress(address,prefix,true);     // v tomto poli je ulozena
    	int[] bro = getNetOrBroAddress(address,prefix,false);
    	
    	net[3]++;
    	bro[3]--;
    	
    	return (or) ? net : bro;
    }
    // mocnin�tor
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
    // metoda ktor� zis�uje kolko bytov ma jedno �islo
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
    // metoda ktora uroby zo String pola int pole
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