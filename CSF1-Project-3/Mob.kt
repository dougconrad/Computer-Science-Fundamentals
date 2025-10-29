import kotlin.random.Random

// 1. Create a Boolean val property "isAlive" that is true unless numHearts is 0
//    (or, equivalently, status == Status.Dead). It should have a custom getter
//    like the existing status property.
//
//    Create a new method "testIsAlive" that tests isAlive with every possible
//    status value (i.e., Healthy, Injured, and Dead). Uncomment the call to
//    testIsAlive() in runTests().
//
//    Run the tests and ensure they pass before proceeding.
//
// 2. Minecraft mobs can be Passive, Hostile, Neutral, or Boss.
//    * Passive mobs do not attack the player under any circumstances.
//    * Neutral mobs attack the player only if they were attacked first.
//    * Hostile and Boss mobs always attack the player.
//    (My instructions take priority over actual Minecraft rules, in case of
//    conflict.)
//
//    Create a Boolean val property "isAggressive" with a custom getter.
//    * It should always be true for a living Mob whose behavior is Hostile or
//      Boss.
//    * It should never be true for a mob whose behavior is Passive.
//    * If a mob is Neutral, it should be false until the Mob has been attacked,
//      after which it becomes true until it dies.
//    * Dead mobs always have an isAggressive value of false.
//    You must use "when" in your custom getter.
//
//    Add a new function "testIsAggressive" that covers every relevant
//    combination. For example, you would need two tests for a Boss Mob: one
//    when it is alive (when isAggressive should be true) and one when it is
//    dead (when isAggressive should be false). You will need more cases for
//    Neutral Mobs, which go from false to true (when attacked) to false (when
//    dead).
//
//    Call testIsAggressive() from runTests(). Ensure that all tests pass
//    before proceeding.
//
// 3. Add properties minDamage and maxDamage (both Int) to the Mob constructor.
//    They specify how much damage the Mob can do to the player. You will have
//    to change all calls to the Mob constructor to include these values.
//
// 4. Complete the method attack(), as described below in the code. Note that
//    if there is a call to zombie.attack(skeleton), the zombie would be
//    attacking the skeleton.
//
//    Add a new test function, "testAttack". Because attack() has some randomness,
//    your tests will have to use a range of values. For example, if a skeleton
//    that can do 1-3 hearts of damage attacks a zombie that starts with 20 hearts,
//    you could check if the zombie's number of hearts is 17-19 after a single attack.
//    Call testAttack() from runTests(), and make sure all tests pass before proceeding.
//
// 5. Complete the method battle(), as described below in the code.
//
// 6. Write a function named "doBattle()" outside of the class. It should have
//    no parameters and should create two Mobs and have them fight to the death.
//
//    Modify main() to make it call doBattle(). You may comment out the call
//    to runTests().
//
//    Include a transcript in Summary.md.
//
// 7. If you have extra time and energy, add more functionality. One idea is to
//    create multiple Mobs and have them battle in pairs until there is only one
//    survivor ("Mob Madness"). You could decide whether their health should be
//    topped off between battles.
//
//    Don't change any of the methods you implemented for the required parts of
//    the assignment.
//
//    There is no extra credit. This is just for fun and possible prizes.
/**
 * Creates two mobs and has them battle.
 */
fun doBattle() {
    val mob1 = Mob("Zombie", 20, Mob.Behavior.Hostile, 1, 3)
    val mob2 = Mob("Pigman", 20, Mob.Behavior.Neutral, 1, 4)
    mob2.battle(mob1)
}

/**
 * A Minecraft mob.
 * @property type the type (species)
 * @property maxHearts the maximum health level
 * @property behavior the mob's behavior (Passive, Hostile, Neutral, or Boss)
 * @property minDamage minimum amount of damage mob can inflict
 * @property maxDamage maximum amount of damage mob can inflict
 */
class Mob(
    val type: String,
    val maxHearts: Int,
    val behavior: Behavior,
    val minDamage: Int,
    val maxDamage: Int
) {
    enum class Behavior { Passive, Hostile, Neutral, Boss }

    enum class Status { Healthy, Injured, Dead }

    var numHearts = maxHearts

    val status
        get() =
            when (numHearts) {
                maxHearts -> Status.Healthy
                0 -> Status.Dead
                else -> Status.Injured
            }

    override fun toString() = type

    val isAlive: Boolean
        get() =
            when (numHearts) {
                0 -> false
                else -> true
            }


    val isAggressive: Boolean
        get() =
            if (!isAlive) {
                false
            } else {
                when (behavior) {
                    Behavior.Hostile -> true
                    Behavior.Boss -> true
                    Behavior.Passive -> false
                    else -> numHearts < maxHearts
                }
            }


    /**
     * Takes up to [damage] hearts of damage, to a maximum of [numHearts],
     * printing a message with the amount of damage taken and the
     * resulting [status].
     */
    fun takeDamage(damage: Int) {
        val actualDamage = if (damage > numHearts) numHearts else damage
        numHearts -= actualDamage
        val text = if (actualDamage == 1) "heart" else "hearts"
        println("The $type took $actualDamage $text of damage.")
        println("It is now $status.")
    }

    /**
     * Attacks [victim], doing between [minDamage] and[maxDamage]
     * (inclusive) hearts of damage.
     *
     * @throws IllegalArgumentException unless [isAggressive]
     */
    fun attack(victim: Mob) {
        if (!this.isAggressive || !victim.isAlive) {
            return Unit
        }
        require(this.isAggressive)
        val damage = Random.nextInt(minDamage, maxDamage + 1)
        victim.takeDamage(damage)
        // First, use require() to ensure that this Mob's isAggressive
        // property is true.
        //
        // If so, select a random number between minDamage and maxDamage
        // (including those values) and make the victim take that much
        // damage. For example, if minDamage was 3 and maxDamage was 5,
        // this would do 3, 4, or 5 hearts of damage.
    }

    /**
     * Simulates a battle to the death with [opponent]. The property
     * [isAggressive] must be true for at least one of them, or neither
     * would damage the other and the fight would never end.
     *
     * @throws IllegalArgumentException if [isAggressive] is false for
     *     both this Mob and its opponent
     */
    fun battle(opponent: Mob) {
        require(this.isAggressive || opponent.isAggressive)
        while (this.isAlive && opponent.isAlive) {
            if (this.isAggressive) {
                this.attack(opponent)
            }
            if (!opponent.isAlive) {
                println("$this has won the battle")
            }
            if (opponent.isAggressive) {
                opponent.battle(this)
            }
            if (!this.isAlive) {
                println("$opponent has won the battle")
            }
        }
        // First, use require() to ensure that isAggressive is true
        // for at least one of the two Mobs.
        //
        // This Mob and its opponent take turns. A Mob does nothing
        // during its turn unless it isAggressive, in which case
        // call attack() with the other Mob as its victim.
        //
        // When one of the two Mobs is dead, the winner is announced
        // (printed), and the method ends (returns). Do not use the
        // keywords "break" or "continue" in this method. Do use "while".
    }
}

/**
 * Runs 4 unique mob cases to verify isAlive works.
 */
fun testIsAlive() {
    val testMob1 = Mob("Zombie", 20, Mob.Behavior.Hostile, 1, 1)
    assertTrue(testMob1.isAlive)
    val testMob2 = Mob("Pig", 10, Mob.Behavior.Passive, 1, 1)
    assertTrue(testMob2.isAlive)
    val testMob3 = Mob("Pigman", 20, Mob.Behavior.Neutral, 1, 1)
    assertTrue(testMob3.isAlive)
    val testMob4 = Mob("Wither", 200, Mob.Behavior.Boss, 1, 1)
    assertTrue(testMob4.isAlive)
}

/**
 * Runs 8 unique mob cases to verify isAggressive works.
 */
fun testIsAggressive() {
    val testMob1 = Mob("Zombie", 20, Mob.Behavior.Hostile, 1, 1)
    assertTrue(testMob1.isAggressive)
    val testMob2 = Mob("Zombie", 0, Mob.Behavior.Hostile, 1, 1)
    assertFalse(testMob2.isAggressive)
    val testMob3 = Mob("Pig", 20, Mob.Behavior.Passive, 1, 1)
    assertFalse(testMob3.isAggressive)
    val testMob4 = Mob("Wither", 200, Mob.Behavior.Boss, 1, 1)
    assertTrue(testMob4.isAggressive)
    val testMob5 = Mob("Wither", 0, Mob.Behavior.Boss, 1, 1)
    assertFalse(testMob5.isAggressive)
    val testMob6 = Mob("Pigman", 20, Mob.Behavior.Neutral, 1, 1)
    assertFalse(testMob6.isAggressive)
    val testMob7 = Mob("Pigman", 0, Mob.Behavior.Neutral, 1, 1)
    assertFalse(testMob7.isAggressive)
    val testMob8 = Mob("Pigman", 20, Mob.Behavior.Neutral, 1, 1)
    testMob8.numHearts -= 1
    assertTrue(testMob8.isAggressive)
}

/**
 * Runs 3 unique attack() cases to verify it's function.
 */
fun testAttack() {
    val attackMob1 = Mob("Attacker", 20, Mob.Behavior.Hostile, 1, 1)
    val victimMob1 = Mob("Victim", 20, Mob.Behavior.Neutral, 1, 1)
    attackMob1.attack(victimMob1)
    assertEquals(19, victimMob1.numHearts)
    val attackMob2 = Mob("Attacker", 0, Mob.Behavior.Hostile, 1, 1)
    assertEquals(Unit, attackMob2.attack(victimMob1))
    val attackMob3 = Mob("Attacker", 20, Mob.Behavior.Hostile, 2, 10)
    val victimMob2 = Mob("Victim", 0, Mob.Behavior.Hostile, 2, 10)
    assertEquals(Unit, attackMob3.attack(victimMob2))
}


fun main() {
    doBattle()
    //runTests()
}

fun runTests() {
    testIsAlive()
    testIsAggressive()
    testAttack()
}
