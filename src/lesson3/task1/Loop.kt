@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun digitNumber(n: Int): Int {
    if (n > 999999999) return 10
    //без рекурсии
//    var number: Int = 1
//    var counter: Int = 10
//    var maxcounter: Int = 100
//    while(n % counter != n % maxcounter){
//        counter *= 10
//        maxcounter *= 10
//        number++
//    }
//    return number

    //с рекурсией

    var income: Int = n
    var counter: Int = 10
    var maxcounter: Int = 100

    if (income % counter != income % maxcounter) {
        return digitNumber(income / 10) + 1
    }
    return 1
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int{
    var a: Int = 1
    var b: Int = 1
    var i: Int = 1
    var tmp: Int

    for (i in 1..n) {
        if (n == 1) return 1
        if (n == 2) return 1
        if (i >= 3) {
            tmp = b
            b += a
            a = tmp
        }
    }
    return b
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int{
    var NOK: Int = 1
    var NOD: Int = 0

    if (m % n == 0 || n % m == 0){
        if (m > n) return m
        if (n > m) return n
        if (n == m) return n
    }

    var x: Int = m
    var y: Int = n
    var tmp: Int = y
    if (m > n) {
        while (x % y != 0) {
            while (x - y > 0) {
                x = x - y
            }
            y = x
            x = tmp
            tmp = y
        }
        NOD = y
        NOK = (m * n)/NOD
    }

    if (n > m){
        var tmp: Int = x
        while (y % x != 0) {
            while (y - x > 0) {
                y = y - x
            }
            x = y
            y = tmp
            tmp = x
        }
        NOD = x
        NOK = (m * n)/NOD
    }
    return NOK
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var i: Int = 2
    while (true) {
        if (n % i == 0){
            return i
        } else {
            i++
        }
    }
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var i: Int = n / 2
    while (i > 0){
        if (n % i == 0){
            return i
        } else {
            i--
        }
    }
    return i
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {

    if (m == 1 || n == 1) return true

    var min: Int
    var max: Int
    if (m > n){
        min = n
        max = m
    } else {
        min = m
        max = n
    }

    var i: Int = kotlin.math.sqrt(min.toDouble()).toInt()
    if (max % min == 0) return false
    while (i > 1){
        if (m % i == 0){
            if (n % i == 0){
                return false
            }
        }
        i--
    }
    return true
}


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val min: Int = kotlin.math.sqrt(m.toDouble()).toInt()
    val max: Int = kotlin.math.sqrt(n.toDouble()).toInt()

    for (i in min..max){
        if (i * i <= n && i * i >= m) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    if (x == 1) return 0
    var m: Int = x
    var counter: Int = 0

    while (m != 1){
        if (m % 2 == 0) m = m / 2 else m = m * 3 + 1
        counter++
    }
    return counter
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = TODO()
//{
////    var n: Int = 3
////    var tmp: Double = kotlin.math.abs(x.pow(n) / n)
////    var sin: Double = 0.0
////    while (tmp > eps){
////        tmp = kotlin.math.abs(x.pow(n) / n)
////        sin -= tmp * (-1)
////        n += 2
////        tmp *= -1
////    }
////    return sin
//}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int = TODO()

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = TODO()

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
