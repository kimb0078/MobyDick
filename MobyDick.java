package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MobyDick {

    List<String> words = new ArrayList<>();
    Set<String> hashSet = new HashSet<>();
    Map<Integer, String> wordMap = new TreeMap<Integer, String>();

    public static void main(String[] args) {
        MobyDick mobyDick = new MobyDick();

        mobyDick.wordCounter();
        mobyDick.searchForWord();
        mobyDick.countWordInstancesOver1000();
    }

    //Denne metode læser fra filen og lægger alle de individuelle ord i ArrayListen words od derefter alle unikke ord i HashSettet hashSet
    //Den tæller derefter hvor mange ord der er i hashSet
    public void wordCounter(){

        int counter = 0;

        try{
            Scanner fileReader = new Scanner(new File("C:\\Users\\Kimberly\\Documents\\Datamatiker\\Kode\\MobyDick\\src\\com\\company\\whale2.txt"));

            do{
                words.add(fileReader.next().replaceAll("\\p{Punct}", "").replaceAll(("[0-9]"), "").toLowerCase());
                hashSet.add(fileReader.next().replaceAll("\\p{Punct}", "").replaceAll(("[0-9]"), "").toLowerCase());
            }while(fileReader.hasNext());

            System.out.println("Unique words in the text:\n" + hashSet);

            for(String w: hashSet){
                counter++;
            }
            System.out.println("This file contains " + (counter-1) + " unique words.\n");
        }
        catch(FileNotFoundException fileEx){
            System.out.println("File not found!");
        }
    }

    //Metode til at søge efter et ord i HashSettet, som brugeren indtaster.
    public void searchForWord(){
        System.out.println("Which word would you like to search for?");
        Scanner input = new Scanner(System.in);
        String choice = input.next();

        if(hashSet.contains(choice) == true){
            System.out.println(choice + " is in the text.");
        }
        else{
            System.out.println(choice + " is not in the text.");
        }
    }

    /* Looper igennem hashSet og ArrayListen og tæller hvor mange gange de er ens
     * Sætter ordene fra hashSet, som findes mindst 1000 gange i Arraylisten i TreeMap'en wordMap
     * Derefter bliver values i wordMap printet ud
     */
    public void countWordInstancesOver1000(){
        System.out.println("\nFinding words that exist at least 1000 times in the text...\n");
        for(String w:hashSet){
            int counter = 1;
            for(String word:words){
                if(w.equals(word)){
                    counter++;
                }
            }
            if(counter >= 1000){
                wordMap.put(counter,w);
            }
        }
        System.out.println("The following words exist at least 1000 times in the text:\n" + wordMap.values());
    }
}
