# SUMMARY

Answer all the questions. Please put your answers _after_ the italicized instructions
and point values.

## Homework 1

### Conversation transcript

_Paste in the transcript of your conversation between the triple back-quotes._ [3 points]

```
/Users/dougconrad/Library/Java/JavaVirtualMachines/corretto-21.0.4/Contents/Home/bin/java -Dkotlin.repl.ideMode=true -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 @/private/var/folders/gs/b3tsy4h96sg9t2yqvrtlgkjr0000gn/T/idea_arg_file1037934410
Kotlin IDE REPL support is experimental. It may be slow or unstable. Please, report problems to https://youtrack.jetbrains.com/issues/KTIJ.
Welcome to Kotlin version 1.9.24-release-822 (JRE 21.0.4+7-LTS)
Type :help for help, :quit for quit

converse()
What is your name?doug
Hello, doug!What's your favorite animal?dog
OMG no way I love dogs!So doug, what do you like most about the dog?i like it
Thank you for talking with me doug! Have an amazing rest of your day!
```

### Debugging

_How did you find the bug in the original `CharacterGenerator.kt`? Was it by
looking at the code, running the code, or talking with another student?_ [1 point]
I ran the code multiple times in REPL and realized all outputs were multiples of 3,
meaning the 3 random numbers generated were all the same.

### CharacterGenerator transcript

_Paste in the transcript of your conversation between the triple back-quotes._ [6 points]

```
/Users/dougconrad/Library/Java/JavaVirtualMachines/corretto-21.0.4/Contents/Home/bin/java -Dkotlin.repl.ideMode=true -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 @/private/var/folders/gs/b3tsy4h96sg9t2yqvrtlgkjr0000gn/T/idea_arg_file530243288
Kotlin IDE REPL support is experimental. It may be slow or unstable. Please, report problems to https://youtrack.jetbrains.com/issues/KTIJ.
Welcome to Kotlin version 1.9.24-release-822 (JRE 21.0.4+7-LTS)
Type :help for help, :quit for quit

makeCharacter()
Enter your character's name:doug
res0: kotlin.String = doug has charisma 9, agility 18, and speed 12.

makeHero()
Enter your character's name:doug
res1: kotlin.String = doug has charisma 11, agility 15, and speed 12.

makeAdventurer()
Enter your character's name:doug
Enter minimum value:2
Enter your maximum value:9
res2: kotlin.String = doug has charisma 16, agility 9, and speed 20.

```

## Logistics

This section is usually the same for every assignment.

### Did you successfully implement everything that was requested, to the best of your knowledge?

_Yes._ [1 point]

### How long did the assignment take?

_1.5 hours._
[1 point]

### Who did you work with and how?

_I worked with Rico DeGuzman, we discussed what the questions were actually asking and motivated each other through the
assignment._ [1 points]

### What resources did you use?

_I used the slides from the first and second lecture, Piazza, and the kotlinlang.org website to find functions and their
uses._ [2 points]

## Reflections

_Give **one or more paragraphs** reflecting on your experience with the
assignment, including answers to all of these questions:_

* How did the assignment go?
* What was the most difficult part of the assignment?
* What was the most rewarding part of the assignment?
* What did you learn doing the assignment? Were there any learning outcomes
  (shown near the top of the Canvas page) you did not achieve?

_Constructive and actionable suggestions for improving assignments, office hours,
and lecture are always welcome._

This assignment went very well! It was a fun challenge and I learned a lot about the language and this
course in general. It took me approximately 1.5-2 hours to complete.
The most difficult part of the assignment was the debugging task because although finding the bug was not
difficult, I struggled figuring out a solution in this language that I'm not very familiar with.
However, because of this, completing this part of the assignment was also the most rewarding. From this assignment,
I learned more functions that kotlin offers and their syntax, ultimately becoming more familiar with
the language. This assignment also taught me how to better learn and overcome challenges I
find in my code. I also used and got more familiar with the available resources for this course, such as Piazza
and the lecture slides.

[5 points]