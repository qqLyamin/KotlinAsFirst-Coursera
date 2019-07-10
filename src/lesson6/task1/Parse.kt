@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    var result_str = String()
    if (parts.size == 3) {
        for (part in parts) {
            if (part == parts[0]) {
                if (part.length <= 2 && part.toInt() <= 31) {
                    result_str += "${String.format("%02d", part.toInt())}" + "."
                } else {
                    return String()
                }
            }

            if (part == parts[1]) {
                when (part) {
                    "января" -> result_str += "01."
                    "февраля" -> {
                        result_str += "02."
                        if (parts[0].toInt() !in 1..28) return String()
                    }
                    "марта" -> result_str += "03."
                    "апреля" -> {
                        result_str += "04."
                        if (parts[0].toInt() !in 1..30) return String()
                    }
                    "мая" -> result_str += "05."
                    "июня" -> {
                        result_str += "06."
                        if (parts[0].toInt() !in 1..30) return String()
                    }
                    "июля" -> result_str += "07."
                    "августа" -> result_str += "08."
                    "сентября" -> {
                        result_str += "09."
                        if (parts[0].toInt() !in 1..30) return String()
                    }
                    "октября" -> result_str += "10."
                    "ноября" -> {
                        result_str += "11."
                        if (parts[0].toInt() !in 1..30) return String()
                    }
                    "декабря" -> result_str += "12."
                    else -> return String()
                }
            }

            if (part == parts[2]) {
                part.toMutableList()
                for (i in 0..part.lastIndex) {
                    if (part[i].toInt() !in 48..57) return String()
                }
                result_str += part
            }
        }
    } else {
        return String()
    }

    return result_str
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    var result_str = String()
    if (parts.size == 3) {
        for (part in parts) {
            if (part == parts[0]) {
                if (part.length <= 2) {
                    var a = part.toIntOrNull()
                    if (a != null && part.toInt() <= 31) {
                        result_str += "${part.toInt()}" + " "
                    } else {
                        return String()
                    }
                } else {
                    return String()
                }
            }

            if (part == parts[1]) {
                when (part) {
                    "01" -> result_str += "января "
                    "02" -> {
                        result_str += "февраля "
                        if (parts[0].toInt() !in 1..28) return String()
                    }
                    "03" -> result_str += "марта "
                    "04" -> {
                        result_str += "апреля "
                        if (parts[0].toInt() !in 1..30) return String()
                    }
                    "05" -> result_str += "мая "
                    "06" -> {
                        result_str += "июня "
                        if (parts[0].toInt() !in 1..30) return String()
                    }
                    "07" -> result_str += "июля "
                    "08" -> result_str += "августа "
                    "09" -> {
                        result_str += "сентября "
                        if (parts[0].toInt() !in 1..30) return String()
                    }
                    "10" -> result_str += "октября "
                    "11" -> {
                        result_str += "ноября "
                        if (parts[0].toInt() !in 1..30) return String()
                    }
                    "12" -> result_str += "декабря "
                    else -> return String()
                }
            }

            if (part == parts[2]) {
                part.toMutableList()
                for (i in 0..part.lastIndex) {
                    if (part[i].toInt() !in 48..57) return String()
                }
                result_str += part
            }
        }
    } else {
        return String()
    }

    return result_str
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    var back_phonenumber: String
    back_phonenumber = phone
    back_phonenumber = Regex("""-|\ |\(|\)""").replace(back_phonenumber, "")
    if (back_phonenumber.length !in 0..13) return String()
    for (i in back_phonenumber) {
        if (i.toInt() == 43 && i != back_phonenumber[0]) return String()
        if (i.toInt() !in 48..57 && i.toInt() != 43) {
            return String()
        }
    }
    return back_phonenumber
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var back_jumping: String
    back_jumping = jumps
    //второй вариант решения (дольше на 10мс)
//    back_jumping = Regex("""%|-""").replace(back_jumping, "")
//    while (back_jumping.contains("  ")) {
//        back_jumping = Regex("""\   |\  """).replace(back_jumping, " ")
//    }
//
//    val parts = back_jumping.split(" ")
//    var max_result: Int = 0
//
//    for (part in parts) {
//        var another: Int? = part.toIntOrNull() ?: return -1
//        if (part.toInt() > max_result) max_result = part.toInt()
//    }
    var max_result: Int = 0
    val parts = back_jumping.split(" ")
    for (i in parts.indices) {
        var a = parts[i].toIntOrNull()
        if (a != null) { //parts.drop(i)
            if (parts[i].toInt() > max_result) max_result = parts[i].toInt()
        } else {
            if (parts[i] != " " && parts[i] != "%" && parts[i] != "-") return -1
        }
    }
    if (max_result == 0) return -1
    return max_result
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var maxHigh: Int = -1
    var back_jump = jumps.split(" ")
    var counter: Int = -1
    for (jump in back_jump) {
        counter++
        if (counter % 2 != 0) {
            for (i in 0..jump.length - 1) {
                if (jump[i] != '%' && jump[i] != '-' && jump[i] != '+') return -1
            }

            if (jump.endsWith("+")) {
                var a = back_jump[counter - 1].toIntOrNull()
                if (a == null) return -1
                if (back_jump[counter - 1].toInt() > maxHigh) maxHigh = back_jump[counter - 1].toInt()
            }
        }
    }
    return maxHigh
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    var ansver: Int = 0
    var values = expression.split(" ")
    var counter: Int = -1
    for (i in values) {
        counter++
        if (counter <= values.lastIndex) {
            if (counter % 2 == 0) {
                if (counter == 0) {
                    var a = i.toIntOrNull()
                    if (a == null) throw IllegalArgumentException()
                    ansver += values[counter].toInt()
                }

                var a = i.toIntOrNull()
                if (a == null) throw IllegalArgumentException()

                for (letter in i) {
                    if (letter.toInt() !in 48..57) throw IllegalArgumentException()
                }

            } else if (counter % 2 != 0) {
                if (i == "+") {
                    var b = values[counter + 1].toIntOrNull()
                    if (b == null) throw IllegalArgumentException()
                    ansver += values[counter + 1].toInt()
                } else if (i == "-") {
                    var c = values[counter + 1].toIntOrNull()
                    if (c == null) throw IllegalArgumentException()
                    ansver -= values[counter + 1].toInt()
                } else {
                    throw IllegalArgumentException()
                }
            }
        }
    }
    return ansver
}


/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var input: String = str
    var finded: String = ""
    var output: Int = -1
    var j: Int
    var cuted: Int = 0
    input = input.toLowerCase()
    var list_input = input.split(" ")
    for (i in 0..list_input.lastIndex) {
        if (finded != "") break
        j = i + 1
        if (i + 1 > list_input.lastIndex) break
        if (list_input[i] == list_input[j]) {
            finded = list_input[i]
            break
        }
    }
    if (finded != "") {
        var counter: Int = 0
        var maximum: Int = input.length
        while (true) {
            counter++
            if (input[input.indexOf(finded)] == input[input.indexOf(finded) + finded.length + 1]) {
                output = input.indexOf(finded)
                break
            } else {
                var before_length = input.length
                input = input.drop(input.indexOf(finded) + finded.length)
                cuted += before_length - input.length
            }
            if (counter >= maximum) break
        }
    } else {
        return output
    }

    return output + cuted
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    var my_description = description.split("; ")
    var map_cost_goods = mutableMapOf<Double, String>()
    for (i in my_description) {
        var stuff = i.split(" ")

        if (stuff.size != 2) return String()
        var a = stuff[1].toDoubleOrNull()

        if (a == null) return String()
        map_cost_goods.put(stuff[1].toDouble(), stuff[0])
    }

    var maximum: Double = 0.0
    for (j in map_cost_goods.keys) {
        if (j > maximum) maximum = j
    }

    return map_cost_goods[maximum]!!
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    var income: String = roman
    if (income.toUpperCase() != roman) return -1

    for (i in income) {
        var a = i.toInt()
        if (a != 73 && a != 86 && a != 88 && a !in 76..77 && a !in 67..68) return -1
    }

    var income_list = income.split("")
    income_list = income_list.drop(1)
    income_list = income_list.dropLast(1)

    var int_list = mutableListOf<Int>()

    var count: Int = 0
    for (i in income_list) {
        when (i) {
            "I" -> int_list.add(count, 1)
            "V" -> int_list.add(count, 5)
            "X" -> int_list.add(count, 10)
            "L" -> int_list.add(count, 50)
            "C" -> int_list.add(count, 100)
            "D" -> int_list.add(count, 500)
            "M" -> int_list.add(count, 1000)
        }
        count++
    }

    if (int_list.isEmpty()) return -1
    if (int_list.size == 1) return int_list[0]
    var summ: Int = 0
    var tmp: Int = 0
    var i: Int = int_list.size - 1

    while (true) {
        if (tmp == 0) {
            if (i - 1 > 0) {
                if (int_list[i] > int_list[i - 1]) {
                    tmp -= int_list[i - 1]
                    tmp += int_list[i]
                }

                if (int_list[i] == int_list[i - 1]) {
                    if (i != int_list.size - 1) {
                        tmp += int_list[i - 1]
                    } else {
                        tmp = int_list[i - 1] + int_list[i]
                    }
                }

                if (int_list[i] < int_list[i - 1]) {
                    tmp += int_list[i - 1]
                }
                i--
            } else {
                summ += tmp
                if (summ < 1) return -1
                break
            }
        } else {
            if (int_list[i] > int_list[i - 1]) {
                tmp -= int_list[i - 1]
                if (i - 1 == 0) {
                    summ += tmp
                    break
                }
            }

            if (int_list[i] == int_list[i - 1]) {
                if (i == int_list.size - 1) {
                    tmp = int_list[i - 1] + int_list[i]
                } else {
                    tmp += int_list[i - 1]
                    if (i - 1 == 0) {
                        summ += tmp
                        break
                    }
                }
            }

            if (int_list[i] < int_list[i - 1]) {
                tmp += int_list[i - 1]
                if (i - 1 == 0) {
                    summ += tmp
                    break
                }
            }
            i--
        }
    }

    return summ
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    var command = commands.toMutableList()
    var mlist = mutableListOf<Int>()
    var Position: Int = cells / 2

    for (i in 0..cells - 1) {
        mlist.add(i, 0)
    }

    var command_limit: Int = limit
    var counter: Int = 0
    var i: Int = 0

    var counter_open: Int = 0
    var counter_close: Int = 0
    while (i < command.size) {
        when (command[i]) {
            ']' -> {
                counter_close++
            }
            '[' -> {
                counter_open++
                if (counter_open < counter_close) throw IllegalArgumentException()
            }
        }
        i++
    }

    if (counter_open != counter_close) throw IllegalArgumentException()

    i = 0
    while (i < command.size) {
        counter++
        if (command_limit <= 0) break
        when (command[i]) {
            ' ' -> {
                command_limit--
            }
            '>' -> {
                Position++
                if (Position > mlist.size - 1) throw IllegalStateException()
                command_limit--
            }
            '<' -> {
                Position--
                if (Position < 0) throw IllegalStateException()
                command_limit--
            }
            '+' -> {
                mlist[Position]++
                command_limit--
            }
            '-' -> {
                mlist[Position]--
                command_limit--
            }
            '[' -> {
                command_limit--
                if (mlist[Position] == 0) {
                    var tmp: Boolean = false
                    var j: Int = counter
                    var copy_counter: Int = 1
                    while (j < command.size - 1) {
                        if (command[j] == '[') copy_counter++
                        if (command[j] == ']' && copy_counter >= 1) copy_counter--
                        if (command[j] == ']' && copy_counter == 0) {
                            i = j
                            counter = j + 1
                            tmp = true
                            break
                        }
                        j++
                    }
                    if (!tmp) throw IllegalArgumentException()
                    j++
                }
            }
            ']' -> {
                command_limit--
                if (mlist[Position] != 0) {
                    var copy_counter: Int = 1
                    var tmp: Boolean = false
                    var j: Int = counter - 2
                    while (j > 0) {
                        if (command[j] == ']') copy_counter++
                        if (command[j] == '[' && copy_counter >= 1) copy_counter--

                        if (command[j] == '[' && copy_counter == 0) {
                            i = j
                            counter = j + 1
                            tmp = true
                            break
                        }
                        j--
                    }
                    if (!tmp) throw IllegalArgumentException()
                }
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
        i++
    }
    return mlist
}
