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
        System.out.println("Pocty v0.5");
        menu();

    }
    // Applications menu
    public static void menu()
    {
        while (true)
        {
            Scanner kb = new Scanner(System.in);

            System.out.println("Functions:");
            System.out.println("    1 Convert between numeral system");
            System.out.println("    2 Mask/Prefix");
            System.out.println("    3 IP Review");
            System.out.print("Choose function: ");
            int y;
            try {
                y = kb.nextInt();    // select an option
            }
            catch (Exception e)
            {
                System.out.println("Wrong input");
                blank(2);
                continue;
            }
            blank(1);
            switch (y)
            {
                case 1:
                    convertUI();
                    break;
                case 2:
                    prefixUI();
                    break;
                case 3:
                    ipReviewUI();
                    break;
                default:
                    System.out.println("Wrong function");
                    blank(2);
                    continue;
            }
        }
    }
    // method which do 1.prevod medzy sústavami
    public static void convertUI()		
    {
        boolean que = true;		// true-from beginning,false-Previous transfer
        int y = 0;				// preset option
        int u = 0;				// preset option
        while(true)
        {
            Scanner kb2 = new Scanner(System.in);
            if (que)
            {
                System.out.println("Z ktorej sústavy chceš prevádzať:");
                System.out.println("    1 Binarna");
                System.out.println("    2 Decimálna");
                y = kb2.nextInt();					// select an option
                System.out.println("Do ktorej sústavy chceš prevádzať:");	// display will offer no possibility that was already selected
                u = 0;
                if(y == 2)	
                {
                    System.out.println("    1 Binarna");
                    u = kb2.nextInt();				// select an option
                    u = 1;
                }
                if(y == 1)
                {
                    System.out.println("    1 Decimálna");
                    u = kb2.nextInt();				// select an option
                    u = 2;
                }
                System.out.println();
            }

            switch (y)
            {
                case 1:
                    if(u == 2)
                    {
                        System.out.print("Zadaj binarne číslo: ");
                        int[] in = getBinNum();
                        System.out.println("Zadané číslo v desiatkovej sústave je: " + fromBinToDec(in));
                    }
                    break;
                case 2:
                    if(u == 1)
                    {
                        System.out.print("Zadaj decimalne číslo: ");
                        int in = kb2.nextInt();
                        System.out.print("Zadané číslo v binárnej sústave je: ");
                        for (int x : fromDecToBin(in))
                        {
                            System.out.print(x);
                        }
                    }

                    break;
                default:
                    System.out.println("Zlý vstup");
                    continue;
            }
            kb2.nextLine();
            kb2.nextLine();

            System.out.println();
            System.out.println();

            System.out.println("Čo chceš robiť? Pokračovať s nastaveným prevodom = Enter  Nový prevod = 0  Menu = 1");
            String back = kb2.nextLine();



            try {
                int in = Integer.parseInt(back);
                if(in == 0)
                {
                	que = true;
                    y = 0;
                    u = 0;
                    continue;		// method are initiated from the beginning
                }
                if (in == 1)
                {
                    break;						// end method/transfer, and the program returns to the menu
                }
            }
            catch (Exception e)
            {
                que = false;
                continue;			// Previous transfer
            }
            System.out.println();
            System.out.println();
        }
    }
    // method which do 2.maska/prefix
    public static void prefixUI()	 
    {
        boolean request = true;		// true-from the beginning,false-Previous transfer
        int option1 = 0;				// preset option
        int option2 = 0;				// preset option
        while(true)
        {
            Scanner kb2 = new Scanner(System.in);
            if (request)
            {
                System.out.println("Konvertovať z:");
                System.out.println("    1 Prefix");
                System.out.println("    2 Maska");
                System.out.println("    3 Wildcard");
                option1 = kb2.nextInt();                      // select an option
                System.out.println("Konvertovať do:");  // display will offer no possibility that was already selected
                option2 = 0;
                if(option1 == 1)
                {
                    System.out.println("    1 Maska");
                    System.out.println("    2 Wildcard");
                    option2 = kb2.nextInt();
                    if (option2 < 0 && option2 > 3)
                    {
                        System.out.println("Zlý vstup");
                        continue;
                    }
                }
                if(option1 == 2)
                {
                    System.out.println("    1 Prefix");
                    System.out.println("    2 Wildcard");
                    option2 = kb2.nextInt();
                    switch(option2)
                    {
                        case 1:
                            option2 = 3;
                            break;
                        case 2:
                            option2 = 4;
                            break;
                        default:
                            System.out.println("Zlý vstup");
                            continue;
                    }
                }
                if(option1 == 3)
                {
                    System.out.println("    1 Prefix");
                    System.out.println("    2 Maska");
                    option2 = kb2.nextInt();
                    switch(option2)
                    {
                        case 1:
                            option2 = 5;
                            break;
                        case 2:
                            option2 = 6;
                            break;
                        default:
                            System.out.println("Zlý vstup");
                            continue;
                    }
                }
                System.out.println();
            }

            switch (option1)
            {
                case 1:
                    if(option2 == 1)
                    {
                        while (true)
                        {
                            System.out.print("Zadaj prefix: ");
                            int in = kb2.nextInt();
                            if (in >= 0 && in <= 32)
                            {
                                System.out.print("Maska je: ");
                                int[] mask = fromPrefixToMask(in);
                                writeAddress(mask);
                                break;
                            }
                            else
                            {
                                System.out.println("Zlý prefix");
                            }
                        }
                    }
                    if(option2 == 2)
                    {
                        while (true)
                        {
                            System.out.print("Zadaj Prefix: ");
                            int in = kb2.nextInt();
                            if (in >= 0 && in <= 32)
                            {
                                System.out.print("Wildcard je: ");
                                int[] wildcard = fromPrefixToWildcard(in);
                                writeAddress(wildcard);
                                break;
                            }
                            else
                            {
                                System.out.println("Zlý prefix");
                            }
                        }
                    }
                    break;
                case 2:
                    if(option2 == 3)
                    {
                        System.out.print("Zadaj masku: ");
                        int[] in = getAddress();
                        System.out.println("Prefix je " + fromMaskToPrefix(in));
                    }
                    if(option2 == 4)
                    {
                        System.out.print("Zadaj masku: ");
                        int[] in = getAddress();
                        int[] out = fromMaskToWildcard(in);
                        System.out.print("Wildcard je ");
                        writeAddress(out);
                    }
                    break;
                case 3:
                    if(option2 == 5)
                    {
                        System.out.print("Zadaj wildcard: ");
                        int[] in = getAddress();
                        System.out.println("Prefix je " + fromWildcardToPrefix(in));
                    }
                    if(option2 == 6)
                    {
                        System.out.print("Zadaj wildcard: ");
                        int[] in = getAddress();
                        int[] out = fromWildcardToMask(in);
                        System.out.print("Maska je ");
                        writeAddress(out);
                    }
                    break;
                default:
                    System.out.println("Zlý vstup");
                    continue;
            }
            kb2.nextLine();
            kb2.nextLine();

            System.out.println();
            System.out.println();

            System.out.println("Čo chceš robiť? Pokračovať s nastaveným prevodom = Enter  Nový prevod = 0  Menu = 1");
            String back = kb2.nextLine();
            try
            {
                int in = Integer.parseInt(back);
                if(in == 0)
                {
                	request = true;
                    option1 = 0;
                    option2 = 0;
                    continue;							// method are initiated from the beginning
                }
                if (in == 1)
                {
                    break;								// end method/transfer, and the program returns to the menu
                }
            }
            catch (Exception e)
            {
                request = false;
                continue;                               // Previous transfer
            }
            System.out.println();
            System.out.println();
        }
    }
    // method which do 3.IP Review
    public static void ipReviewUI()
    {
        Scanner kb7 = new Scanner(System.in);
        System.out.print("Zadaj IPv4 adresu: ");
        int[] address = getAddress();					// address
        System.out.print("Zadaj prefix: ");				// prefix network
        int prefix = kb7.nextInt();
        System.out.println();

        System.out.print("Maska je: ");
        writeAddress(fromPrefixToMask(prefix));			// subnet mask
        System.out.println();

        System.out.print("Network adress: ");			
        writeAddress(getNetOrBroAddress(address,prefix,true));		// network address
        System.out.println();

        System.out.print("Broadcast adress: ");						// Broadcast adress
        writeAddress(getNetOrBroAddress(address,prefix,false));
        System.out.println();

        System.out.println("Number of address: " + getSpaceAddress(prefix));	// Number of address

        System.out.println("Number of address for devices: " + (getSpaceAddress(prefix) - 2));	// Number of address for devices

        System.out.print("The first usable adress: ");				// The first usable adress
        writeAddress(getFirstOrLastAddress(address,prefix,true));
        System.out.println();

        System.out.print("The last usable adress: ");				// The last usable adress
        writeAddress(getFirstOrLastAddress(address,prefix,false));
        System.out.println();

        kb7.nextLine();
        kb7.nextLine();
    }
    //metod for blank lines in UI
    public static void blank(int num)
    {
        for (int i = 0; i < num;i++)
            System.out.println();
    }
    //metod for get IPv4 address from user in console
	public static int[] getAddress()
    {
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
        while (true)
        {
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
                    continue;
                }
            }
            catch (Exception e)
            {
                System.out.println("Zlý vstup");
                continue;
            }
        }

    }
	// method which write address with four octet
    public static void writeAddress(int[] address)
    {
        for (int i = 0; i < 4; i++)
        {
            System.out.print(address[i]);
            if (i != 3)
                System.out.print(".");
        }
    }
    public static int[] getBinNum()
    {
        Scanner kb3 = new Scanner(System.in);
        String in = kb3.nextLine();
        String[] inout = new String[in.length()];
        for (int i = 0; i < in.length(); i++)
        {
            String character = in.substring(i,i+1);
            inout[i] = character;
        }
        return stringArrayToIntArray(inout);
    }
    // method for calculating from decimal to binary
    public static int[] fromDecToBin(int dec)
    {
        int bits = bits(dec);       // variable which is stored the number of bits of the decimal number
        int[] bin = new int[bits];  // field to which will store a binary number
        int rest = dec;
        for(int index = bits - 1;index >= 0;index--)	// dividing the decimal number 2
        {
            bin[index] = rest %2;
            rest = rest / 2;
        }
        return bin;
    }
    /*
     *  special method for calculating from decimal to binary (8 bits)
     * 	method returns an eight bit binary number
     */
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
        int index = 0;								// prvok pola bin
        for(int i = bin.length - 1; i >= 0; i--)
        {
            if(bin[i] == 1)
            {
                dec += exponentiation(2,index);
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
                else							// ak sa dany prvok bude rovnat nule program vrati prefix
                    return prefix;
            }
        }
        return prefix;
    }
    // metoda ktora z prefixu uroby vildcard
    public static int[] fromPrefixToWildcard(int prefix)
    {
    	int[] mask = fromPrefixToMask(prefix);         // pole v ktorom je konvertovany prefix na masku
		int[] Wildcard = fromMaskToWildcard(mask);     // pole v ktorom je ulozeny vildcard
		return Wildcard;
    }
    // metoda ktora z masky uroby vildcard
    public static int[] fromMaskToWildcard(int[] mask)
    {
        int[] Wildcard = new int[4];      	// pole v ktorom je ulozeny vildcard
        for(int index = 0; index < Wildcard.length;index++)
        {
            Wildcard[index] = 255 - mask[index];
        }
        return Wildcard;
    }
    // metoda ktora z wildcardu uroby masku
    public static int[] fromWildcardToMask(int[] wildcard)
    {
    	int[] mask = new int[4];            //  pole v ktorom je ulozena maska
    	
    	for(int i = 0; i < mask.length;i++)
    	{
    		mask[i] = 255 - wildcard[i];
    	}
    	return mask;
    }
    // metoda ktora z wildcardu uroby prefix
    public static int fromWildcardToPrefix(int[] wildcard)
    {
    	int prefix = fromMaskToPrefix(fromWildcardToMask(wildcard));
    	return prefix;
    }
    // metoda na ziskanie sietovej alebo broadcastovej adresy
    public static int[] getNetOrBroAddress(int[] address,int prefix,boolean net)	// ak net==true->getNet  ak net==false->getBro
    {
    	if(prefix == 32)											// ak prefix = 32 vrati celu adresu lebo nic sa nemeni
    		return address;
        
    	int[] octet1 = fromDecToBin8(address[0]);
    	int[] octet2 = fromDecToBin8(address[1]);
    	int[] octet3 = fromDecToBin8(address[2]);
    	int[] octet4 = fromDecToBin8(address[3]);
    	int fromThisPrefix = prefix / 8 + 1; 	// vypocita v ktorom oktete sa za�inaju meni� 0/1
    	int border = prefix - (prefix / 8)*8;						// hranica zmeny 0/1 v prvom upravujucom sa oktete
    	
    	changeOctet(octet1, fromThisPrefix, 1, border, net);		// meni sa octet1
    	changeOctet(octet2, fromThisPrefix, 2, border, net);		// meni sa octet2
    	changeOctet(octet3, fromThisPrefix, 3, border, net);		// meni sa octet3
    	changeOctet(octet4, fromThisPrefix, 4, border, net);		// meni sa octet4
    	
    	int[] outAddress = new int[4];								// pole do ktoreho sa uklada vysledna adresa
    	outAddress[0] = fromBinToDec(octet1);						
    	outAddress[1] = fromBinToDec(octet2);
    	outAddress[2] = fromBinToDec(octet3);
    	outAddress[3] = fromBinToDec(octet4);
    	return outAddress;
    }
    // metoda na opravu octetu pre getNetOrBroAddress
    public static void changeOctet(int[] octet,int fromThisPrefix,int numOctet,int border,boolean net)	//oktet,fromThisPrefix,cislo oktetu,border,net
    {
    	if(fromThisPrefix <= numOctet)			// numOctet - cislo prave upravujuceho sa oktetu
    	{
    		border = (fromThisPrefix == numOctet) ? border : 0;
    		for(int i = border;i < 8;i++)
    		{
    			octet[i] = (net) ? 0 : 1;		// menia sa 1/0    - ak true cisla sa menia na 0  ak false na 1
    		}
    	}
    }
    // metoda na zis�ovanie po�tu adries
    public static int getSpaceAddress(int prefix)
    {
    	int spaceAddress;
    	spaceAddress = exponentiation(2,32 - prefix);
    	return spaceAddress;	
    }
    // metoda na zistenie prvej a poslednej pou�ite�nej adresy
    public static int[] getFirstOrLastAddress(int[] address,int prefix,boolean or)	//if or==true->getFirst if or==false->getLast
    {
    	int[] net = getNetOrBroAddress(address,prefix,true);     	// v tomto poli je ulozena sietova adresa	
    	int[] bro = getNetOrBroAddress(address,prefix,false);		// v tomto poli je ulozena broadcastova adresa
    	
    	net[3]++;
    	bro[3]--;
    	
    	return (or) ? net : bro;	
    }
    // mocninátor
    public static int exponentiation(int base, int exponent)
    {
        int out = 0;
        for(int i = 0; i <= exponent; i++)
        {
            if( i == 0)
                out = 1;
            else
                out = out * base;
        }
        return out;
    }
    // metoda ktor� zis�uje kolko bytov ma jedno �islo
    public static int bits(int dec)
    {
        if (dec == 0)		// ak decimalne cislo sa rovna 0 binarne ma potom 1 cislo a to je 0 
            return 1;
        for(int index = 0;;index++)
        {
            int f = exponentiation(2,index);   // f = vysledok mocnenia cisla 2 indexom
            if(dec / f == 0)
            {
                return index;
            }
        }
    }
    // metoda ktora uroby zo String pola int pole
    public static int[] stringArrayToIntArray(String[] array)
    {
        int[] arrayInt = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            arrayInt[i] = Integer.parseInt(array[i]);
        }
        return arrayInt;
    }

}