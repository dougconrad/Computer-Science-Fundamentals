// Congratulations! You've been invited to do the following
// online assessment for a co-op with tech giant Oodle.
//
// 1. Add the 3+ constants from the pre-exercise to the Gender enum.
// 2. Define at least 2 age-based constants where indicated below to
//    make your later ad-serving code more readable and maintainable.
//    Choose general names, such as MIN_ADULT_AGE rather than ones that
//    refer to ads, such as MIN_AGE_FOR_DATING_AD. You can also use
//    numeric literals. (Look up terms if you don't understand them.
//    They may appear on tests!)
// 3. Add properties minAge and maxAge to Ad, and set values for each
//    ad. For example, minAge for the dating ad might be MIN_ADULT_AGE.
// 4. Implement the provided fetchAd() function. You must use both "if"
//    and "when". Paste in the tests from the pre-exercise. Uncomment
//    the call to runTests() in main(). Run the tests, and see if they
//    all pass. In your write-up, you will need to describe the testing
//    and debugging process, so take notes.
// 5. Create a new data class named "Person". A person should have an
//    age (Int), gender (Gender), and income (Int). Use your judgment
//    as to which properties should be changeable.
// 6. Write a new fetchAd() method (without removing the original one)
//    that takes a single parameter of type Person and returns an Ad.
//    Instead of duplicating the code in your original fetchAd() method,
//    have your new method call your old method, passing the appropriate
//    properties as arguments.
// 7. Create a new function named "testFetchAdPerson" that tests your
//    new fetchAd() method. Modify runTests() to call this new function.
/**
 * Types of genders
 *
 * @property Male male
 * @property Female female
 * @property Nonbinary nonbinary
 */
enum class Gender {
    // 1. Add the 3+ constants from the pre-exercise to the Gender enum.
    Male,
    Female,
    Nonbinary
}

// Age-based constants
const val MIN_AGE_FOR_PERSONALIZATION = 13
const val MIN_ADULT_AGE = 18

// 2. Define at least 2 age-based constants where indicated below to
//    make your later ad-serving code more readable and maintainable.
//    Choose general names, such as MIN_ADULT_AGE rather than ones that
//    refer to ads, such as MIN_AGE_FOR_DATING_AD.
const val MIN_SENIOR_AGE = 60
const val MIDDLE_AGE = 30

// 3. Add properties minAge and maxAge to Ad, and set values for each
//    ad. For example, minAge for the dating ad might be MIN_ADULT_AGE.
//    You can also use numeric literals. (Look up terms if you don't understand
//    them. They may appear on tests!) Be sure to update the KDoc.

/**
 * Ads that may be shown to users.
 *
 * @property text the text of the ad
 * @property revenue the number of cents earned per click
 * @property minAge the minimum age for the ad target
 * @property maxAge the maximum age for the ad target
 */
enum class Ad(
    val text: String,
    val revenue: Int,
    val minAge: Int,
    val maxAge: Int
) {
    Diet("Lose weight now!", 5, MIDDLE_AGE, MIN_SENIOR_AGE),
    Dating("Meet other singles!", 4, MIN_ADULT_AGE, MIDDLE_AGE),
    Lego("Fun to step on!", 1, MIN_AGE_FOR_PERSONALIZATION, MIN_ADULT_AGE),
    Pet("You need a friend!", 1, MIN_ADULT_AGE, MIDDLE_AGE),
    PetToy("Amuse your pet!", 2, MIN_ADULT_AGE, MIDDLE_AGE),
    Pokemon("Gotta catch 'em all!", 2, MIN_AGE_FOR_PERSONALIZATION, MIDDLE_AGE),
    Retirement("Join AARP", 2, MIN_SENIOR_AGE, 100),
    Work("Apply for a job at Oodle!", 2, MIN_ADULT_AGE, MIN_SENIOR_AGE),
}

/**
 * Fetches an ad based on the user's [gender], [age], and
 * [income], unless the age is under [MIN_AGE_FOR_PERSONALIZATION],
 * in which case no personalization is permitted.
 */
fun fetchAd(
    gender: Gender,
    age: Int,
    income: Int,
): Ad {
    // 4a. Implement the provided fetchAd() function. You must use
    //     both "if" and "when".
    // return Ad.Work // This is just a temporary default return value.
    var adOut = Ad.Work
    if (income <= 19999 && age >= MIN_AGE_FOR_PERSONALIZATION) {
        adOut = when (gender) {
            Gender.Male -> Ad.Diet
            Gender.Female -> Ad.Work
            else -> Ad.Lego
        }
    } else if (income <= 39999 && age >= MIN_AGE_FOR_PERSONALIZATION) {
        adOut = when (gender) {
            Gender.Male -> Ad.Dating
            Gender.Female -> Ad.Diet
            else -> Ad.Dating
        }
    } else if (income <= 59999 && age >= MIN_AGE_FOR_PERSONALIZATION) {
        adOut = when (gender) {
            Gender.Male -> Ad.Lego
            Gender.Female -> Ad.Dating
            else -> Ad.Diet
        }
    } else if (income <= 99999 && age >= MIN_AGE_FOR_PERSONALIZATION) {
        adOut = when (gender) {
            Gender.Male -> Ad.Pet
            Gender.Female -> Ad.Lego
            else -> Ad.PetToy
        }
    } else if (age >= MIN_AGE_FOR_PERSONALIZATION) {
        adOut = when (gender) {
            Gender.Male -> Ad.Retirement
            Gender.Female -> Ad.Pet
            else -> Ad.Pokemon
        }
    }
    return adOut
}

// 4b. Paste in the tests from the pre-exercise.
fun testDiet1() {
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 30, 0))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 30, 19999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 30, 9999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 60, 0))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 60, 19999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 60, 9999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 45, 0))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 45, 19999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 45, 9999))
}


fun testDating2() {
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 18, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 18, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 18, 29999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 30, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 30, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 30, 29999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 24, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 24, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 24, 29999))
}


fun testLego3() {
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 13, 40000))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 13, 59999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 13, 49999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 18, 40000))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 18, 59999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 18, 49999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 15, 40000))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 15, 59999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 15, 49999))
}


fun testPet4() {
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 18, 60000))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 18, 99999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 18, 79999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 30, 60000))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 30, 99999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 30, 79999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 24, 60000))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 24, 99999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 24, 79999))
}


fun testRetirement5() {
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 60, 100000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 60, 999999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 60, 549999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 100, 100000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 100, 999999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 100, 549999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 80, 100000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 80, 999999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Male, 80, 549999))
}


fun testWork6() {
    assertEquals(Ad.Work, fetchAd(Gender.Female, 18, 0))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 18, 19999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 18, 9999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 60, 0))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 60, 19999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 60, 9999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 39, 0))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 39, 19999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 39, 9999))
}


fun testDiet7() {
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 30, 20000))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 30, 39999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 30, 29999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 60, 20000))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 60, 39999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 60, 29999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 45, 20000))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 45, 39999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 45, 29999))
}


fun testDating8() {
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 18, 40000))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 18, 59999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 18, 49999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 30, 40000))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 30, 59999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 30, 49999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 24, 40000))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 24, 59999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 24, 49999))
}


fun testLego9() {
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 13, 60000))
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 13, 99999))
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 13, 79999))
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 18, 60000))
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 18, 99999))
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 18, 79999))
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 15, 60000))
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 15, 99999))
    assertEquals(Ad.Lego, fetchAd(Gender.Female, 15, 79999))
}


fun testPet10() {
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 18, 100000))
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 18, 999999))
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 18, 549999))
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 30, 100000))
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 30, 999999))
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 30, 549999))
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 24, 100000))
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 24, 999999))
    assertEquals(Ad.Pet, fetchAd(Gender.Female, 24, 549999))
}


fun testLego11() {
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 13, 0))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 13, 19999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 13, 9999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 18, 0))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 18, 19999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 18, 9999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 15, 0))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 15, 19999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 15, 9999))
}


fun testDating12() {
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 18, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 18, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 18, 29999))
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 30, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 30, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 30, 29999))
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 24, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 24, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Nonbinary, 24, 29999))
}


fun testDiet13() {
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 30, 40000))
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 30, 59999))
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 30, 49999))
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 60, 40000))
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 60, 59999))
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 60, 49999))
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 45, 40000))
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 45, 59999))
    assertEquals(Ad.Diet, fetchAd(Gender.Nonbinary, 45, 49999))
}


fun testPetToy14() {
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 18, 60000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 18, 99999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 18, 79999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 30, 60000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 30, 99999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 30, 79999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 24, 60000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 24, 99999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 24, 79999))
}


fun testPokemon15() {
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 13, 100000))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 13, 999999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 13, 549999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 30, 100000))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 30, 999999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 30, 549999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 21, 100000))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 21, 999999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 21, 549999))
}


fun testAll() {
    testDiet1()
    testDating2()
    testLego3()
    testPet4()
    testRetirement5()
    testWork6()
    testDiet7()
    testDating8()
    testLego9()
    testPet10()
    testLego11()
    testDating12()
    testDiet13()
    testPetToy14()
    testPokemon15()
    testFetchAdPerson()
    print("All tests pass.")
}

// 4c. Uncomment the call to runTests() in main(). Run the tests, and see
//     if they all pass. In your write-up, you will need to describe the
//     testing and debugging process, so take notes.
fun main() {
    testAll()
}

// 5. Create a new data class named "Person". A person should have an
//    age (Int), gender (Gender), and income (Int). Use your judgment
//    as to which properties should be changeable.
/**
 * Person that ad is targeted towards and their demographics
 *
 * @property gender the gender of target
 * @property age the age of the target
 * @property income the income of the target
 */
data class Person(
    var gender: Gender,
    var age: Int,
    var income: Int
)

// 6. Write a new fetchAd() method (without removing the original one)
//    that takes a single parameter of type Person and returns an Ad.
//    Instead of duplicating the code in your original fetchAd() method,
//    have your new method call your old method, passing the appropriate
//    properties as arguments.
/**
 * Fetches targeted ad based on Person's demographics
 *
 */
fun fetchAd(subject: Person): Ad {
    return fetchAd(subject.gender, subject.age, subject.income)
}

// 7. Create a new function named "testFetchAdPerson" that tests your
//    new fetchAd() method. Modify runTests() to call this new function.
/**
 * Runs tests on fetchAd() function
 */
fun testFetchAdPerson() {
    val doug = Person(Gender.Male, 17, 999999)
    assertEquals(Ad.Retirement, fetchAd(doug))
    val roman = Person(Gender.Male, 19, 0)
    assertEquals(Ad.Diet, fetchAd(roman))
    val aidan = Person(Gender.Male, 60, 59999)
    assertEquals(Ad.Lego, fetchAd(aidan))
    val rose = Person(Gender.Female, 30, 59999)
    assertEquals(Ad.Dating, fetchAd(rose))
    val ava = Person(Gender.Female, 60, 20000)
    assertEquals(Ad.Diet, fetchAd(ava))
    val jane = Person(Gender.Female, 13, 999999)
    assertEquals(Ad.Pet, fetchAd(jane))
    val skylar = Person(Gender.Nonbinary, 30, 0)
    assertEquals(Ad.Lego, fetchAd(skylar))
    val kai = Person(Gender.Nonbinary, 60, 999999)
    assertEquals(Ad.Pokemon, fetchAd(kai))
    val nico = Person(Gender.Nonbinary, 13, 99999)
    assertEquals(Ad.PetToy, fetchAd(nico))
    val jo = Person(Gender.Female, 18, 0)
    assertEquals(Ad.Work, fetchAd(jo))
}
// Do not modify the following code.

/**
 * Verifies that [actual] is equal to [expected].
 *
 * @throws AssertionError if they are not equal
 */
fun assertEquals(
    expected: Any,
    actual: Any,
) {
    if (expected != actual) {
        throw AssertionError("Expected $expected, got $actual")
    }
}
