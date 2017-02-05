# Java Inflection Algorithm
## What is it
A simple naive algorithm for finding evidence inflection in a language.

##How does it work
The algorithm checks each word against every other word in the input and uses 
a ngram style approach, checking each character of each word and noting the likeness
of the words, if the likenness is above a certain threshold they are considered a different
case of the same word. This difference is then calculated and if there is a difference of
0 the word is considered a stem word. If not, the algorithms tries to guess the stem word

##Output
`countr Confirmed? false
  -country
  -countries
  `
 
  `
 live Confirmed? true
  -lived
  -live`
  
  `text Confirmed? true
    -texted
    -text
    -texts`