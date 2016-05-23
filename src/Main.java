/*
 * 
 * Copyright 2016   Migas Simon
 * 			        Skirka Lubomir
 * 			        Sokol Kristian
 * 			        Stankovic Tomas
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        cleanConsole();
        System.out.println("IP Review");
        menu();

    }
    // Applications menu
    public static void menu()
    {
        while (true)
        {
            System.out.println("Functions:");
            System.out.println("    1 Transfer between numeral system");
            System.out.println("    2 Mask/Prefix/Wildcard");
            System.out.println("    3 IP Review");
            System.out.println("    4 About");
            System.out.println("    5 Exit");
            int y = getNumFromTo("Choose function: ",1,5,"Wrong function");
            blank(1);
            cleanConsole();
            switch (y)
            {
                case 1:
                    convertUI();
                    cleanConsole();
                    break;
                case 2:
                    prefixUI();
                    cleanConsole();
                    break;
                case 3:
                    ipReviewUI();
                    cleanConsole();
                    break;
                case 4:
                    aboutUI();
                    cleanConsole();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
    // method which do 1 Transfer between numeral system
    public static void convertUI()		
    {
        boolean request = true;			// true-from beginning,false-Previous transfer
        int option1 = 0;				// preset option
        int option2 = 0;				// preset option
        while(true)
        {
            Scanner kb2 = new Scanner(System.in);
            if (request)
            {
                System.out.println("Transfer from:");
                System.out.println("    1 Binary");
                System.out.println("    2 Decimal");
                option1 = getNumFromTo("From: ",1,2,"1 or 2?");			// select an option
                System.out.println("Transfer to:");			// display will offer no possibility that was already selected
                option2 = 0;
                if(option1 == 2)
                {
                    System.out.println("    1 Binary");
                    option2 = getNumFromTo("To: ",1,1,"Wrong choice");	// select an option
                    switch (option2)
                    {
                        case 1:
                            option2 = 1;
                    }
                }
                if(option1 == 1)
                {
                    System.out.println("    1 Decimal");
                    option2 = getNumFromTo("To: ",1,1,"Wrong choice");				// select an option
                    switch (option2)
                    {
                        case 1:
                            option2 = 2;
                    }

                }
                blank(1);
            }
            cleanConsole();
            switch (option1)
            {
                case 1:
                    if(option2 == 2)
                    {
                        int[] in = getBinNum("Enter binary number: ");
                        System.out.println("The number entered in decimal is: " + fromBinToDec(in));
                    }
                    break;
                case 2:
                    if(option2 == 1)
                    {
                        int in = getNum("Enter decimal number: ");
                        System.out.print("The number entered in binary is: ");
                        for (int x : fromDecToBin(in))
                        {
                            System.out.print(x);
                        }
                    }
                    break;
            }
            enterToContinue();

            blank(1);

            System.out.println("What do you want to do? Continue with conversion settings = Enter, New convert = 0, 1 = Menu");
            String back = kb2.nextLine();

            try {
                int in = Integer.parseInt(back);
                if(in == 0)
                {
                	request = true;
                    option1 = 0;
                    option2 = 0;
                    cleanConsole();
                    continue;				// method are initiated from the
                }
                if (in == 1)
                {
                    cleanConsole();
                    break;					// end method/transfer, and the program returns to the menu
                }
            }
            catch (Exception e)
            {
                request = false;
                cleanConsole();
                continue;					// previous transfer
            }
        }
    }
    // method which do 2.maska/prefix
    public static void prefixUI()	 
    {
        boolean request = true;				// true-from the beginning,false-Previous transfer
        int option1 = 0;					// preset option
        int option2 = 0;					// preset option
        while(true)
        {
            Scanner kb2 = new Scanner(System.in);
            if (request)
            {
                System.out.println("Convert from:");
                System.out.println("    1 Prefix");
                System.out.println("    2 Mask");
                System.out.println("    3 Wildcard");
                option1 = getNumFromTo("From: ",1,3,"Wrong choice");                 // select an option
                System.out.println("Convert to:"); 		 // display will offer no possibility that was already selected
                option2 = 0;
                if(option1 == 1)
                {
                    System.out.println("    1 Mask");
                    System.out.println("    2 Wildcard");
                    option2 = getNumFromTo("To: ", 1, 2, "Wrong choice");
                }
                if(option1 == 2)
                {
                    System.out.println("    1 Prefix");
                    System.out.println("    2 Wildcard");
                    option2 = getNumFromTo("To: ", 1, 2, "Wrong choice");
                    switch(option2)
                    {
                        case 1:
                            option2 = 3;
                            break;
                        case 2:
                            option2 = 4;
                            break;
                    }
                }
                if(option1 == 3)
                {
                    System.out.println("    1 Prefix");
                    System.out.println("    2 Mask");
                    option2 = getNumFromTo("To: ", 1, 2, "Wrong choice");
                    switch(option2)
                    {
                        case 1:
                            option2 = 5;
                            break;
                        case 2:
                            option2 = 6;
                            break;
                    }
                }

            }
            blank(1);
            cleanConsole();
            switch (option1)
            {
                case 1:
                    if(option2 == 1)
                    {
                        while (true)
                        {
                            int in = getNumFromTo("Enter prefix: ", 1, 32, "Prefix is only from 1 to 32");
                                System.out.print("Mask is: ");
                                int[] mask = fromPrefixToMask(in);
                                writeAddress(mask);
                                break;
                        }
                    }
                    if(option2 == 2)
                    {
                        while (true)
                        {
                            int in = getNumFromTo("Enter prefix: ", 1, 32, "Prefix is only from 1 to 32");
                            System.out.print("Wildcard is: ");
                            int[] wildcard = fromPrefixToWildcard(in);
                            writeAddress(wildcard);
                            break;
                        }
                    }
                    break;
                case 2:
                    if(option2 == 3)
                    {
                        int[] in = getMask("Enter mask: ");
                        System.out.println("Prefix is: " + fromMaskToPrefix(in));
                    }
                    if(option2 == 4)
                    {
                        int[] in = getMask("Enter mask: ");
                        int[] out = fromMaskToWildcard(in);
                        System.out.print("Wildcard is:  ");
                        writeAddress(out);
                    }
                    break;
                case 3:
                    if(option2 == 5)
                    {
                        int[] in = getAddress("Enter wildcard: ");
                        System.out.println("Prefix is: " + fromWildcardToPrefix(in));
                    }
                    if(option2 == 6)
                    {
                        int[] in = getAddress("Enter wildcard: ");
                        int[] out = fromWildcardToMask(in);
                        System.out.print("Maska is: ");
                        writeAddress(out);
                    }
                    break;
                default:
                    System.out.println("Wrong input");
                    continue;
            }
            enterToContinue();

            blank(1);

            System.out.println("What do you want to do? Continue with conversion settings = Enter, New convert = 0, 1 = Menu");
            String back = kb2.nextLine();
            try
            {
                int in = Integer.parseInt(back);
                if(in == 0)
                {
                	request = true;
                    option1 = 0;
                    option2 = 0;
                    cleanConsole();
                    continue;							// method are initiated from the beginning
                }
                if (in == 1)
                {
                    cleanConsole();
                    break;								// end method/transfer, and the program returns to the menu
                }
            }
            catch (Exception e)
            {
                request = false;
                cleanConsole();
                continue;                               // previous transfer
            }
            System.out.println();
            System.out.println();
        }
    }
    // method which do 3.IP Review
    public static void ipReviewUI()
    {
        int[] address = getAddress("Enter IPv4 address: ");	// address
        int prefix = getNumFromTo("Enter prefix: ",1,32,"Prefix is only from 1 to 32");			    // prefix network
        System.out.println();

        System.out.print("Maska is: ");
        writeAddress(fromPrefixToMask(prefix));			// subnet mask
        System.out.println();

        System.out.print("Network adress: ");			
        writeAddress(getNetOrBroAddress(address,prefix,true));		// network address
        System.out.println();

        System.out.print("Broadcast adress: ");						// broadcast address
        writeAddress(getNetOrBroAddress(address,prefix,false));
        System.out.println();

        System.out.println("Number of address: " + getSpaceAddress(prefix));					// number of address

        System.out.println("Number of address for devices: " + (getSpaceAddress(prefix) - 2));	// number of address for devices

        System.out.print("The first usable adress: ");				// the first usable address
        writeAddress(getFirstOrLastAddress(address,prefix,true));
        System.out.println();

        System.out.print("The last usable adress: ");				// the last usable address
        writeAddress(getFirstOrLastAddress(address,prefix,false));
        System.out.println();

        enterToContinue();
        blank(2);
    }
    // method for blank lines in UI
    public static void blank(int num)
    {
        for (int i = 0; i < num;i++)
            System.out.println();
    }
    // method for clean console
    public static void cleanConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    //method for pause program and continuo after enter
    public static void enterToContinue()
    {
        Scanner enter = new Scanner(System.in);
        enter.nextLine();
    }
    // method for get IPv4 address from user in console
	public static int[] getAddress(String text)
    {
		Scanner kb = new Scanner(System.in);
        while (true)
        {
            System.out.print(text);
            String [] inout = kb.nextLine().split("\\.");   //get adress from user and cut to index after .
            try
            {
                int[] address = stringArrayToIntArray(inout);
                if(address.length != 4)                    //if address havent got 4 octet
                {
                    System.out.print("Enter the address with four octet");
                    enterToContinue();                      //again while
                }
                else if(!isThisIPv4Address(address))        //if address is not address
                {
                    System.out.print("This is not address");
                    enterToContinue();                      //again while
                }
                else
                {
                    return address;                         //correct address is returned
                }
            }
            catch (Exception e)                             //if user enter no number to inout
            {
                System.out.print("Enter number only");
                enterToContinue();
            }
        }

    }
    //method for getMask and chceck if mask is really mask
    public static int[] getMask(String what)
    {
        while (true)
        {
            int[] out = getAddress(what);
            boolean isThere = true;     //in mask is right octet
            boolean continueFor = true; //stop for ater find nex octet where
            boolean next = true;        //next shoud be 0
            boolean ismask = true;      //is mask, ex. 255.128.255.0 is not
            for (int octet : out)
            {
                if (!next && octet != 0)
                {
                    ismask = false;
                    continue;
                }
                for (int exponent = 7; exponent >= 0 && continueFor; exponent--) {
                        if (!(octet == exponentiation(2, exponent) || octet == 255 || octet == 0))
                        {
                            isThere = false;
                            continueFor = false;
                        }
                }
                if (255 != octet)
                {
                    next = false;
                }
                continueFor = true;
            }
            if (!isThere || !ismask)
            {
                System.out.print("This is not mask");
                enterToContinue();
                continue;
            }
            else
                return out;
        }
    }
    //method for chceck is input array address
    public static boolean isThisIPv4Address(int[] address)
    {
        for (int i : address)
        {
            if (i < 0 || i > 255)
                return false;
        }
        return true;
    }
    //method for get int from user in console and preventing crash program for not number to int
    public static int getNum(String what)
    {
        int out = 0;
        while (true)
        {
            Scanner kb9 = new Scanner(System.in);
            System.out.print(what);
            try {
                out = kb9.nextInt();
                break;
            }
            catch (Exception e)
            {
                System.out.print("Wrong input");
                enterToContinue();
            }

        }
        return out;
    }
    //method for get int from user in console and preventing crash program for not number to int and wrong range of number to returnwith default error message
    public static int getNumFromTo(String what, int from, int to)
    {
        return getNumFromTo(what, from, to, "Wrong input");
    }
    //method for get int from user in console and preventing crash program for not number to int and wrong range of number to returnwith custom error message
    public static int getNumFromTo(String what, int from, int to, String error)
    {
        int out;
        while (true)
        {
            out = getNum(what);
            if (out >= from && out <= to)
                break;
            else
            {
                System.out.print(error);
                enterToContinue();
            }
        }
        return out;
    }
    //method for get bin number from user, preventing enter other num as 0, 1 and other character as num
    public static int[] getBinNum(String text)
    {
        while (true)
        {
            System.out.print(text);
            int[] in;
            try {
                in = inBinNum();
                if (isThisBinArray(in))
                    return in;
                else
                    System.out.print("Bin nunmber is composit by 0 and 1. You should keep attention on lesson.");
                    enterToContinue();
            }
            catch (Exception e)
            {
                System.out.print("Enter 0 and 1 only");
                enterToContinue();
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
    //part of getBinNum, get bin number from user and cut bin number in one String to String array
    public static int[] inBinNum()
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
    //method for chceck is array bin number
    public static boolean isThisBinArray(int[] array)
    {
        for (int i : array)
        {
            if (i != 0 && i != 1)
                return false;
        }
        return true;
    }
    // method for transfer from decimal to binary
    public static int[] fromDecToBin(int dec)
    {
        int bits = bits(dec);       // variable which is stored the number of bits of the decimal number
        int[] bin = new int[bits];  // array where will be saved a binary number
        int rest = dec;
        for(int index = bits - 1;index >= 0;index--)	// dividing the decimal number 2
        {
            bin[index] = rest %2;
            rest = rest / 2;
        }
        return bin;
    }
    /*
     *  special method for transfer from decimal to binary (8 bits)
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
    // method for transfer from binary to decimal
    public static int fromBinToDec(int[] bin)
    {
        int dec = 0;                                // The resulting number in decimal
        int index = 0;								// array element bin
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
    // method for the convert from prefix to mask
    public static int[] fromPrefixToMask(int prefix)
    {
        int[] mask = new int[4];  			// array where will be saved the resulting mask  
        int full = prefix / 8;				// find out how many octets will be 255
        for(int i = 0; i < full;i++)
        {
            mask[i] = 255;
        }
        if(full < 4)
        {
            prefix = prefix - (full*8);		// deducted from the total prefix octets which are 255
            int[] activeOctet = new int[8];	// array where will be saved remaining 1 of prefix
            for(int i = 0; i < prefix;i++)
            {
                activeOctet[i] = 1;
            }
            mask[full] = fromBinToDec(activeOctet);
        }

        return mask;
    }
    // method for convert from mask to prefix
    public static int fromMaskToPrefix(int[] mask)
    {
        int prefix = 0;                     			// prefix
        for(int i = 0; i < mask.length;i++)				// browse the mask octets
        {
            int[] pole = fromDecToBin(mask[i]);			// octet of the mask converts to the binary number
            for(int a = 0; a < pole.length;a++)			// cycle find out how many 1 there are in octet
            {
                if(pole[a] == 1)
                    prefix++;
                else									// if will be given element of array zero the program returns prefix
                	return prefix;
            }
        }
        return prefix;
    }
    // method for convert from prefix to wildcard
    public static int[] fromPrefixToWildcard(int prefix)
    {
    	int[] mask = fromPrefixToMask(prefix);         // array where is converted prefix to mask
		int[] Wildcard = fromMaskToWildcard(mask);     // array where will be saved wildcard
		return Wildcard;
    }
    // method for convert from mask to wildcard
    public static int[] fromMaskToWildcard(int[] mask)
    {
        int[] Wildcard = new int[4];      				// array where will be saved wildcard
        for(int index = 0; index < Wildcard.length;index++)
        {
            Wildcard[index] = 255 - mask[index];
        }
        return Wildcard;
    }
    // method for convert from wildcard to mask
    public static int[] fromWildcardToMask(int[] wildcard)
    {
    	int[] mask = new int[4];            			// array where will be saved mask
    	
    	for(int i = 0; i < mask.length;i++)
    	{
    		mask[i] = 255 - wildcard[i];
    	}
    	return mask;
    }
    // method for convert from wildcard to prefix
    public static int fromWildcardToPrefix(int[] wildcard)
    {
    	int prefix = fromMaskToPrefix(fromWildcardToMask(wildcard));
    	return prefix;
    }
    // method for get network or broadcast address
    public static int[] getNetOrBroAddress(int[] address,int prefix,boolean net)	// if net==true->getNet  if net==false->getBro
    {
    	if(prefix == 32)											// if prefix = 32 return full address because  nothing changes 
    		return address;
        
    	int[] octet1 = fromDecToBin8(address[0]);
    	int[] octet2 = fromDecToBin8(address[1]);
    	int[] octet3 = fromDecToBin8(address[2]);
    	int[] octet4 = fromDecToBin8(address[3]);
    	int fromThisPrefix = prefix / 8 + 1; 						// calculated in which the octet is starting to change 0/1
    	int border = prefix - (prefix / 8)*8;						// border where starts change 0/1
    	
    	changeOctet(octet1, fromThisPrefix, 1, border, net);		// change octet1
    	changeOctet(octet2, fromThisPrefix, 2, border, net);		// change octet2
    	changeOctet(octet3, fromThisPrefix, 3, border, net);		// change octet3
    	changeOctet(octet4, fromThisPrefix, 4, border, net);		// change octet4
    	
    	int[] outAddress = new int[4];								// array where will be saved the resulting address
    	outAddress[0] = fromBinToDec(octet1);						
    	outAddress[1] = fromBinToDec(octet2);
    	outAddress[2] = fromBinToDec(octet3);
    	outAddress[3] = fromBinToDec(octet4);
    	return outAddress;
    }
    // method for change octet for method getNetOrBroAddress 
    public static void changeOctet(int[] octet,int fromThisPrefix,int numOctet,int border,boolean net)	//octet,fromThisPrefix,number octet,border,net
    {
    	if(fromThisPrefix <= numOctet)			// numOctet - number octets which is changing 
    	{
    		border = (fromThisPrefix == numOctet) ? border : 0;
    		for(int i = border;i < 8;i++)
    		{
    			octet[i] = (net) ? 0 : 1;		// change 1/0    - if true numbers are changing on 0  if false on 1
    		}
    	}
    }
    // method for find out number all address 
    public static int getSpaceAddress(int prefix)
    {
    	int spaceAddress;
    	spaceAddress = exponentiation(2,32 - prefix);
    	return spaceAddress;	
    }
    // method for find out the first or the last address 
    public static int[] getFirstOrLastAddress(int[] address,int prefix,boolean or)	//if or==true->getFirst if or==false->getLast
    {
    	int[] net = getNetOrBroAddress(address,prefix,true);     	// array for network address	
    	int[] bro = getNetOrBroAddress(address,prefix,false);		// array for broadcast address
    	
    	net[3]++;
    	bro[3]--;
    	
    	return (or) ? net : bro;	
    }
    // method for exponentiation
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
    // method for find out number bits decimal number 
    public static int bits(int dec)
    {
        if (dec == 0)		// if dec = 0 binary is had 1 number-> 0 
            return 1;
        for(int index = 0;;index++)
        {
            int f = exponentiation(2,index);   
            if(dec / f == 0)
            {
                return index;
            }
        }
    }
    // method for convert from String array to int array 
    public static int[] stringArrayToIntArray(String[] array)
    {
        int[] arrayInt = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            arrayInt[i] = Integer.parseInt(array[i]);
        }
        return arrayInt;
    }
    //UI method for about info
    public static void aboutUI()
    {
        blank(2);
        System.out.println("IP Review");
        System.out.println("Version : 1.0.1");
        blank(1);
        System.out.println("Copyright 2016");
        System.out.println("Migaš Šimon  Skirka Ľubomír  Sokol Kristián  Stankovič Tomáš");
        blank(1);
        System.out.println("   Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "   you may not use this file except in compliance with the License.\n" +
                "   You may obtain a copy of the License at\n" +
                "\n" +
                "       http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "   Unless required by applicable law or agreed to in writing, software\n" +
                "   distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "   See the License for the specific language governing permissions and\n" +
                "   limitations under the   License.");
        blank(1);
        System.out.println("Sources avaible on https://github.com/lubomirskirka/IP-Review");
        blank(1);
        System.out.println("Latest version you can find on https://github.com/lubomirskirka/IP-Review/releases");
        enterToContinue();
        blank(2);
    }
}