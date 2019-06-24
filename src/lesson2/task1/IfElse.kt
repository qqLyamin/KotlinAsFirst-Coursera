@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if ((age%100 <= 20 && age%100 > 4) || (age%10 == 5 || age%10 == 6 || age%10 == 7 || age%10 == 8 || age%10 == 9 || age%10 == 0))
    {
        return "$age лет"
    }
    else if ((age%100 > 20 || age%100 in 1..4) && (age%10 == 2 || age%10 == 3 || age%10 == 4))
    {
        return "$age года"
    }
    else if (age%10 == 1) {
        return "$age год"
    }
    return ""
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val half: Double = (t1 * v1 + t2 * v2 + t3 * v3)/2.0
    var S: Double = 0.0
    var time: Double = 0.0

    while (S < half){
        if (time < t1){
            time += 0.01
            S = time * v1
        } else if (time < t1 + t2){
            time += 0.01
            S = t1 * v1 + (time - t1) * v2
        } else if (time < t1 + t2 + t3){
            time += 0.01
            S = t1 * v1 + t2 * v2 + (time - t1 - t2) * v3
        }
    }
    return time
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    var counter: Int = 0
    var both: Boolean = false
    if (kingX == rookX1 || kingY == rookY1){
        counter = 1
        both = true
    }

    if (kingX == rookX2 || kingY == rookY2){
        counter = 2
        if (both){
            counter += 1
        }
    }

    return counter
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    var counter: Int = 0
    var n: Int = 1
    var both: Boolean = false

    if (kingX == rookX || kingY == rookY){
        counter = 1
        both = true
    }
    while (n != 7) {
        if (bishopX + n == kingX && bishopY + n == kingY) {
            counter = 2
            if (both) {
                counter += 1
                return counter
            } else{
                return counter
            }
        } else {
            n++
        }
    }
    n = 1
    while (n != 7) {
        if (bishopX + n == kingX && bishopY - n == kingY) {
            counter = 2
            if (both) {
                counter += 1
                return counter
            } else{
              return counter
            }
        } else {
            n++
        }
    }
    n = 1
    while (n != 7) {
        if (bishopX - n == kingX && bishopY + n == kingY) {
            counter = 2
            if (both) {
                counter += 1
                return counter
            } else{
                return counter
            }
        } else {
            n++
        }
    }
    n = 1
    while (n != 7) {
        if (bishopX - n == kingX && bishopY - n == kingY) {
            counter = 2
            if (both) {
                counter += 1
                return counter
            } else{
                return counter
            }
        } else {
            n++
        }
    }
    return counter
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */


fun triangleKind(a: Double, b: Double, c: Double): Int {
    if (a > 0 && b > 0 && c > 0 && (a + b > c && a + c > b && b + c > a)) {
        if ((a * a == b * b + c * c) || (c * c == b * b + a * a) || (b * b == c * c + a * a)) {
            return 1
        } else if ((a * a < b * b + c * c) && (c * c < b * b + a * a) && (b * b < c * c + a * a)) {
            return 0
        } else if ((a * a > b * b + c * c) || (c * c > b * b + a * a) || (b * b > c * c + a * a)) {
            return 2
        }
    }
    return -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int{
    if ((a < d && a > c) || (c < b && c > a)) {
        if (a < d && b <= d && a >= c){
            return b - a
        }
        if (d <= b && c < b && c >= a){
            return d - c
        }
        if (b > d && a < d){
            return d - a
        }
        if (b > c && a < c){
            return b - c
        }
    } else {
        if (b == c){
            return 0
        }
    }
    return -1
}
