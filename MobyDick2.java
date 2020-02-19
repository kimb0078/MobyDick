package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MobyDick2 {

    List<String> words = new ArrayList<>();
    Set<String> hashSet = new HashSet<>();
    Map<String, Integer> wordMap = new TreeMap<String, Integer>(); //I dette program har jeg byttet om på key og value

    public static void main(String[] args) {
        MobyDick2 mobyDick = new MobyDick2();
        mobyDick.wordFinder();
        mobyDick.countWordInstancesOver500();
        mobyDick.userWordCounter();

    }
    //Læser filen og lægger alle de individuelle ord i ArrayListen words od derefter alle unikke ord i HashSettet hashSet
    public void wordFinder(){

        try{
            System.out.println("Reading text...\n");
            Scanner fileReader = new Scanner(new File("C:\\Users\\Kimberly\\Documents\\Datamatiker\\Kode\\MobyDick\\src\\com\\company\\whale2.txt"));

            do{
                words.add(fileReader.next().replaceAll("\\p{Punct}", "").replaceAll(("[0-9]"), "").toLowerCase());
                hashSet.add(fileReader.next().replaceAll("\\p{Punct}", "").replaceAll(("[0-9]"), "").toLowerCase());
            }while(fileReader.hasNext());
        }
        catch(FileNotFoundException fileEx){
            System.out.println("File not found!");
        }
    }

    /* Looper igennem hashSet og ArrayListen og tæller hvor mange gange de er ens
     * Sætter ordene fra hashSet, som findes mindst 500 gange i Arraylisten i TreeMap'en wordMap
     * Derefter bliver KEYS i wordMap printet ud
     */
    public void countWordInstancesOver500(){
        System.out.println("Finding words that exist at least 500 times in the text...\n");
        for(String w:hashSet){
            int counter = 1;
            for(String word:words){
                if(w.equals(word)){
                    counter++;
                }
            }
            if(counter >= 500){
                wordMap.put(w,counter);
            }
        }
        System.out.println("The following words exist at least 500 times in the text: \n" + wordMap.keySet());
    }

    /*Tager brugerens input og tjekker om ordet findes i hashSet
     *Hvis ordet er der, bliver den fundet i wordMap og dens value bliver printet.
     */
    public void userWordCounter() {
        System.out.println("\nWhich word would you like to count?");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();

        if(hashSet.contains(choice) == true){
            if(wordMap.get(choice) == null) { //Det gik op for mig, at hvis ordet findes under 500 gange i teksten, er den ikke i wordMap endnu og skal sættes ind...
                int counter = 1;
                for (String word : words) {
                    if (choice.equals(word)) {
                        counter++;
                    }
                }
                wordMap.put(choice, counter);
            }
            System.out.println("This words exists " + wordMap.get(choice) + " times in the text.");
        }
        else{
            System.out.println(choice + " is not in the text.");
        }
    }
}
