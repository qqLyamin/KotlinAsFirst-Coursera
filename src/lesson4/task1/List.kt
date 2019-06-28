@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    if (!v.isEmpty()) {
        var sum: Double = 0.0
        for (i in 0..(v.size - 1)) {
            sum += v[i].pow(2)
        }
        return sqrt(sum)
    } else {
        return 0.0
    }
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (!list.isEmpty()) {
        var sum: Double = 0.0
        for (i in 0..(list.size - 1)) {
            sum += list[i]
        }
        return sum/(list.size)
    } else {
        return 0.0
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (!list.isEmpty()){
        var middlevalue: Double = 0.0
        var sum: Double = 0.0

        for (i in 0..(list.size - 1)) {
            sum += list[i]
        }

        middlevalue = sum / list.size

        for (i in 0..(list.size - 1)) {
            list[i] -= middlevalue
        }
    }

    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    if (!a.isEmpty() && !b.isEmpty()){
        val size: Int
        var sum: Double = 0.0
        if (a.size > b.size) size = a.size else size = b.size
        for (i in 0..size - 1){
            sum += a[i] * b[i]
        }
        return sum
    } else {
        return 0.0
    }
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (!p.isEmpty()) {
        val size: Int = p.size
        var sum: Double = 0.0
        for (i in 1..size - 1) {
            sum += p[i] * x.pow(i)
        }
        sum += p[0]
        return sum
    } else {
        return 0.0
    }

}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (!list.isEmpty()) {
        var sum: Double = 0.0
        for (i in 0..list.size - 1) {
            sum += list[i]
            list[i] = sum
        }
        return list
    } else {
        return list
    }
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val my_mutablelist = mutableListOf<Int>()
    if (n > 1) {
        var my_n: Int = n

        while (my_n > 1) {
            for (i in 2..n / 2 + 1) {
                if (my_n % i == 0) {
                    my_n = my_n / i
                    my_mutablelist.add(i)
                    break
                }
            }
        }
        my_mutablelist.sort() // под эти тесты необязательная строка
        return my_mutablelist
    } else {
        my_mutablelist.add(1)
        return my_mutablelist
    }
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var str: String
    val my_mutablelist = mutableListOf<Int>()
    var my_n: Int = n
    var counter: Int = 0

    while (my_n > 1) {
        for (i in 2..sqrt(my_n.toDouble()).toInt()) {
            if (my_n % i == 0) {
                my_n = my_n / i
                my_mutablelist.add(i)
                break
            }
        }
        counter++
        if (counter != my_mutablelist.size){
            my_mutablelist.add(my_n)
            break
        }
    }
    my_mutablelist.sort()
    str = my_mutablelist.joinToString(separator = "*")
    return str
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var mList = mutableListOf<Int>()
    if (n < base) {
        mList.add(n)
        return mList
    }
    var mn: Int = n
    var mbase: Int = base

    var multiplier: Int = 1
    var degree: Int = 0

    while (true){
        if (mn > (mbase.toDouble().pow(degree.toDouble())).toInt()) {
            degree++
            continue
        } else {
            degree--
            break
        }
    }

    while (true){
        if (mn > (mbase.toDouble().pow(degree.toDouble())).toInt() * multiplier) {
            multiplier++
            continue
        } else {
            multiplier--
            break
        }
    }


    mList.add(multiplier)
    mn -= ((mbase.toDouble()).pow(degree.toDouble())).toInt() * multiplier

    for (i in degree - 1 downTo 0 step 1){
        multiplier = 1
        while (true){
            if (mn >= (mbase.toDouble().pow(i.toDouble())).toInt() * multiplier) {
                multiplier++
                continue
            } else {
                multiplier--
                break
            }
        }
        mList.add(multiplier)
        mn -= ((mbase.toDouble()).pow(i.toDouble())).toInt() * multiplier
    }

    return mList
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var mList = mutableListOf<Int>()
    if (n < base) {
        when (n) {
            10 -> return "a"
            11 -> return "b"
            12 -> return "c"
            13 -> return "d"
            14 -> return "e"
            15 -> return "f"
            16 -> return "g"
            17 -> return "h"
            18 -> return "i"
            19 -> return "j"
            20 -> return "k"
            21 -> return "l"
            22 -> return "m"
            23 -> return "n"
            24 -> return "o"
            25 -> return "p"
            26 -> return "q"
            27 -> return "r"
            28 -> return "s"
            29 -> return "t"
            30 -> return "u"
            31 -> return "v"
            32 -> return "w"
            33 -> return "x"
            34 -> return "y"
            35 -> return "z"
            else -> {
                return n.toString()
            }
        }
    }
    var mn: Int = n
    var mbase: Int = base

    var multiplier: Int = 1
    var degree: Int = 0
    while (true){
        if (mn > (mbase.toDouble().pow(degree.toDouble())).toInt()) {
            degree++
            continue
        } else {
            degree--
            break
        }
    }

    multiplier = mn / (mbase.toDouble().pow(degree.toDouble()).toInt())

    mList.add(multiplier)
    mn -= ((mbase.toDouble()).pow(degree.toDouble())).toInt() * multiplier

    for (j in degree - 1 downTo 0 step 1){
        multiplier = 1
        while (true){
            if (mn >= (mbase.toDouble().pow(j.toDouble())).toInt() * multiplier) {
                multiplier++
                continue
            } else {
                multiplier--
                break
            }
        }
        mList.add(multiplier)
        mn -= ((mbase.toDouble()).pow(j.toDouble())).toInt() * multiplier
    }

    var mstrList = mutableListOf<String>()

    for (i in 0..mList.size - 1){
        if (mList[i] > 9){
            when (mList[i]) {
                10 -> mstrList.add("a")
                11 -> mstrList.add("b")
                12 -> mstrList.add("c")
                13 -> mstrList.add("d")
                14 -> mstrList.add("e")
                15 -> mstrList.add("f")
                16 -> mstrList.add("g")
                17 -> mstrList.add("h")
                18 -> mstrList.add("i")
                19 -> mstrList.add("j")
                20 -> mstrList.add("k")
                21 -> mstrList.add("l")
                22 -> mstrList.add("m")
                23 -> mstrList.add("n")
                24 -> mstrList.add("o")
                25 -> mstrList.add("p")
                26 -> mstrList.add("q")
                27 -> mstrList.add("r")
                28 -> mstrList.add("s")
                29 -> mstrList.add("t")
                30 -> mstrList.add("u")
                31 -> mstrList.add("v")
                32 -> mstrList.add("w")
                33 -> mstrList.add("x")
                34 -> mstrList.add("y")
                35 -> mstrList.add("z")
            }
        } else {
            mstrList.add(mList[i].toString())
        }
    }

    return mstrList.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    if (!digits.isEmpty()) {
        var degree: Int = digits.size - 1
        var sum: Int = 0
        var counter: Int = 0

        for (i in 0..degree) {
            sum += digits[i] * ((base).toDouble().pow(degree - counter)).toInt()
            counter++
        }
        return sum
    } else {
        return 0
    }
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    if (str != "") {
        var degree: Int = str.length - 1
        var sum: Int = 0
        var counter: Int = 0
        var x = mutableListOf<Int>()

        for (i in 0..degree){
            if (str[i].toInt() >= 97){
                when (str[i].toInt()) {
                    97 -> x.add(str[i].toInt() - 87)
                    98 -> x.add(str[i].toInt() - 87)
                    99 -> x.add(str[i].toInt() - 87)
                    100 -> x.add(str[i].toInt() - 87)
                    101 -> x.add(str[i].toInt() - 87)
                    102 -> x.add(str[i].toInt() - 87)
                    103 -> x.add(str[i].toInt() - 87)
                    104 -> x.add(str[i].toInt() - 87)
                    105 -> x.add(str[i].toInt() - 87)
                    106 -> x.add(str[i].toInt() - 87)
                    107 -> x.add(str[i].toInt() - 87)
                    108 -> x.add(str[i].toInt() - 87)
                    109 -> x.add(str[i].toInt() - 87)
                    110 -> x.add(str[i].toInt() - 87)
                    111 -> x.add(str[i].toInt() - 87)
                    112 -> x.add(str[i].toInt() - 87)
                    113 -> x.add(str[i].toInt() - 87)
                    114 -> x.add(str[i].toInt() - 87)
                    115 -> x.add(str[i].toInt() - 87)
                    116 -> x.add(str[i].toInt() - 87)
                    117 -> x.add(str[i].toInt() - 87)
                    118 -> x.add(str[i].toInt() - 87)
                    119 -> x.add(str[i].toInt() - 87)
                    120 -> x.add(str[i].toInt() - 87)
                    121 -> x.add(str[i].toInt() - 87)
                    122 -> x.add(str[i].toInt() - 87)
                }
            } else if (str[i].toInt() >= 48 && str[i].toInt() <= 57){
                x.add(str[i].toInt() - 48)
            }
        }

        for (i in 0..degree) {
            sum += x[i] * ((base).toDouble().pow(degree - counter)).toInt()
            counter++
        }
        return sum
    } else {
        return 0
    }
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()


/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()