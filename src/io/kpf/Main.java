package io.kpf;

import java.util.ArrayList;

import static java.lang.StrictMath.abs;

public class Main {

    public static void main(String[] args) {
        // input string, remove punctuation and spilt it
	    String input = "" +
                "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated texted they lived in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then".replaceAll("\\p{P}", "");
	    String[] words = input.split(" ");
	    ArrayList<Inflection> inflections = new ArrayList<>();

	    int i = 0;
	    for (String word : words)
        {
            int j = 0;
            for (String word2 : words)
            {
                // for each word check against every other word in the input apart from this one
                if(j != i) {
                    // spilt them into arrays of strings
                    String[] charactersWord1 = word.split("");
                    String[] charactersWord2 = word2.split("");

                    int likeness = 0;
                    int k = 0;
                    // setup a flag so only the characters in the correct position are chosen
                    boolean flag = true;
                    // check that these aren't the same word
                    if(!word.equals(word2)) {
                        // if they aren't for every character in the first word
                        for (String character : charactersWord1) {
                            // avoid index out of bounds exception by making sure the second word
                            // isn't too short
                            if (word2.length() > k) {
                                // if the characters are the same, and the flag
                                if (character.equals(charactersWord2[k]) && flag) {
                                    // increase the likeness
                                    likeness++;
                                } else {
                                    // if the characters aren't the same ignore the rest of the word
                                    flag = false;
                                }
                                k++;
                            }
                        }
                    }

                    // if the likeness reaches a threshold we consider it complete
                    if(likeness > 3) {
                        boolean stemFlag = false;
                        String stem = "";
                        // if the likeness - the length of the word is 0 we have the stem of the word
                        if(likeness - word.length() == 0) {
                            stem = word;
                            System.out.println("Stem: " + stem);
                            stemFlag = true;
                        }
                        // otherwise we need to calculate the stem, this may not be correct
                        else {
                            stem = word.substring(0, likeness);
                            System.out.println("Stem? " + stem);
                            stemFlag = false;
                        }
                        // all the related characters we found
                        System.out.println("    " + word2 + " different characters = " + abs(likeness - word.length()));

                        // ADD ALL CONFIRMED FLAGS
                        boolean stemAlreadyExists = false;
                        for (Inflection inflection: inflections) {
                              // is the word a confirmed stem, and not already in the inflection list, add it
                             if(inflection.stem.equals(stem))
                             {
                                 if(stemFlag)
                                 {
                                     inflection.stemConfirmed = true;
                                 }
                                 stemAlreadyExists = true;
                             }
                        }

                        if(!stemAlreadyExists) {
                            inflections.add(new Inflection(stem, stemFlag));
                        }

                        for (Inflection inflection: inflections) {
                            if (inflection.stem.equals(stem)) {
                                if (!inflection.words.contains(word2)) {
                                    inflection.words.add(word2);
                                }
                            }
                        }

                    }
                }
                j++;
            }
            i++;
        }

        for (Inflection inflection : inflections)
        {
            System.out.println(inflection.stem + " Confirmed? " + inflection.stemConfirmed);
            for (String word:inflection.words
                 ) {
                System.out.println(" -" + word);
            }
        }
    }
}
