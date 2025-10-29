/**
 * Prompts a user to enter their name.
 *
 * @return the text entered by the user
 */
fun getUserName(): String {
    println("What is your name?")
    val name = readln()
    return name
}

/**
 * Greets the user by [name].
 */
fun greetUser(name: String) {
    println("Hello, $name!")
}

/**
 * Carries on a brief conversation with a user.
 */
fun converse() {
    val name = getUserName()
    greetUser(name)
    val animal = favAnimal()
    printFavAnimal(animal)
    askFavAnimal(name, animal)
    convoEnd(name)

}

/**
 * Asks the user what their favorite animal is.
 */
fun favAnimal(): String {
    println(" What's your favorite animal?")
    val animal = readln()
    return animal
}

/**
 * Tells the user they like their favorite animal.
 */
fun printFavAnimal(animal: String) {
    println("OMG no way I love ${animal}s!")
}

/**
 * Asks user a question about their favorite animal.
 */
fun askFavAnimal(name: String, animal: String) {
    println(" So ${name}, what do you like most about the ${animal}?")
    readln()
}

/**
 * Ends the conversation
 */
fun convoEnd(name: String) {
    println("Thank you for talking with me ${name}! Have an amazing rest of your day!")
}