/* Liam Coffey 1206617 HashTable*/

import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
class Hashtable{
    private File FileS;
    private String CRS;
    private int size;
    private int[] HashTableKey;
    private String[] HashTableData;
    private int count;
    private int probe;
    private String p = "";
    private String Key = "";
    private int key = 0;
    private String data = "";
    //constructor function
    public Hashtable(int _size, String _CRS){
	CRS = _CRS;
	size = _size;
	HashTableKey = new int[size];
	HashTableData = new String[size];
	count = 0;
	//Hard coded test file please change here if the name is incorrect!
	//
	//
	FileS = new File("TestFile.txt");
	//
	//
	//please dont miss it!
    }
    //checks to see if a valid number and CSR value was entered then reads in the file and creates the hashtable
    public static void main(String[] args){
	int s = 0;
	
	try
        {
        s = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex)
        {
	    System.out.println("please enter Real number");
        }
	if(args.length == 0){
            System.out.println("Please enter a Value");
            System.exit(0);
        }
	if(args[1].equals("k") || args[1].equals("k")){
        Hashtable T = new Hashtable(s, "K");
	T.Scan();
	T.proformance();
	}
	else if(args[1].equals("l") || args[1].equals("L")){
        Hashtable T = new Hashtable(s, "l");
	T.Scan();
	T.proformance();
	}
	else{
	    System.err.println("Please enter valid Collision Resolution Stratagy");
	}
    }
    //scans the data file if it starts with p read in the hash code and the name, if it starts with g just read the hash code,
    public void Scan(){
	if(FileS.exists()){
		try{
		Scanner sc = new Scanner(FileS);
		while(sc.hasNext()){
		if(count/size<.8){
		p = sc.next();
		if(p.equals("p")){
		    Key = sc.next();
		try{
		   key  = Integer.parseInt(Key);
		}
                catch(Exception ex) {
		    System.out.print("Not a String");
		}
		data = sc.next();
		put(key,data);
		}
		else if(p.equals("g")){
		Key = sc.next();
		try{
		   key  = Integer.parseInt(Key);
		}
                catch(Exception ex) {
		    System.out.print("Not a String");
		}
	       }	
		}else{
		    System.err.println("LOAD OVER80");
		    break;
		}
	    }
	}catch(Exception e){};}
	else{
	    System.out.println("File Not Found");
	}
    }
    //checks to see if the spot in the table is empty, if it is add it to the table if it is not add it in with one of the CRS
    public void put(int x, String y){
	if(get(x) == 0){
	    HashTableKey[x%size]=x;
	    HashTableData[x%size]=y;
	    System.out.println(y+" was added to "+x%size);
	}else{
	    if(CRS.equals("l") || CRS.equals("L")){
		rehashLinear(x, x);
	    }
	    else{
		rehashKey(x,x);
	    }
	}
	count++;
    }
    //Checks the table to see if its empty
    public int get(int x){
	probe++;
	return(HashTableKey[x%size]);
    }
    //gives an avrage of the number of probes to the table
    public void proformance(){
	System.out.println("Avarage number of probes:"+probe/count);
    }
    //linear rehash function if the spot is full +1 to the hashcode until a new spot is found, loop if it excedes the size of the list
    public void rehashLinear(int k, int hc){
	while(true){   
	if(get(k) == 0){
	    HashTableKey[k%size]=hc;
	    HashTableData[k%size]=data;
	    System.out.println(data+" was added to "+k%size+" with Linear rehashing");
	    probe++;
	    break;
	}else{
	}
	if(k != size){
	    k++;
	    }
	else{
	    k=0;
	}
	}

    }
    //double hashing method, gets a prime number dose the equation p-(k%p) to figure out how many times it has to jump, then jumps along the list untill it finds a new location, loops if it reaches the end of the list
    public void rehashKey(int k, int hc){
	int prime = getPrime();
	int HashVal = k%size; 
	int insert = prime - k%prime;
	int N = HashVal + insert;
	while(true){
	if(N >= size){
	    N = N - size;
	}
	if(get(N) == 0){
	HashTableKey[N]=hc;
	HashTableData[N]=data;
	System.out.println(data+" was added to "+N+ " With Double hashing");
	break;
	}else{
	    N = N + insert;
	    probe++;
	}
	}
    }
    //equation for finding the nearest prime to the table size
    public int getPrime(){
        for (int i = size - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        return 3;
    }
}
