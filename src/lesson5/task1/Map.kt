@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import java.lang.Exception

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    var newmap: MutableMap<String, String> = mutableMapOf()
    var num: String?

    newmap.putAll(mapA)

    for ((service, number) in mapB) {
        if (newmap.containsKey(service)) {
            num = newmap[service]
            if (number != num) newmap[service] = "$num, $number"
        } else {
            newmap.put(service, number)
        }
    }
    return newmap
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {

    var mmap = mutableMapOf<Int, MutableList<String>>()
//    var mlistOf2 = mutableListOf<String>()
//    var mlistOf3 = mutableListOf<String>()
//    var mlistOf4 = mutableListOf<String>()
//    var mlistOf5 = mutableListOf<String>()
//
//    for ((name, grade) in grades) {
//        when (grade){
//            2 -> mlistOf2.add(name)
//            3 -> mlistOf3.add(name)
//            4 -> mlistOf4.add(name)
//            5 -> mlistOf5.add(name)
//        }
//    }
//    if (!mlistOf2.isEmpty()) mmap.put(2, mlistOf2)
//    if (!mlistOf3.isEmpty()) mmap.put(3, mlistOf3)
//    if (!mlistOf4.isEmpty()) mmap.put(4, mlistOf4)
//    if (!mlistOf5.isEmpty()) mmap.put(5, mlistOf5)

    for ((name, grade) in grades) {
        if (!mmap.containsKey(grade)) {
            mmap.put(grade, mutableListOf<String>())
        }
        mmap[grade]!!.add(name)
    }

    return mmap
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for ((key, value) in a) {
        if (!(b.containsKey(key) && a[key] == b[key])) return false
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    var mmap = mutableMapOf<String, Double>()
    var sum: Double = 0.0
    var counter: Int = 0
    for (name in stockPrices) {
        for (another in stockPrices) {
            if (name.first == another.first) {
                sum += another.second
                counter++
            }
        }
        if (!mmap.containsKey(name.first)) mmap.put(name.first, (sum/counter.toDouble()))
        sum = 0.0
        counter = 0
    }

    return mmap
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var name: String? = null
    var cost: Double = Double.MAX_VALUE
    var mstuff = mutableMapOf<String, Double>()

    for ((key, value) in stuff) {
        if (stuff[key]!!.first == "$kind") {
            mstuff.put(key, stuff[key]!!.second)
        }
    }

    if (!mstuff.isEmpty()) {
        for ((key, value) in mstuff) {
            if (mstuff[key]!!.toDouble() < cost) {
                cost = mstuff[key]!!.toDouble()
                name = key
            }
        }
    }
    return name
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    //создаю map в который буду писать с именами всех
    var map_back = mutableMapOf<String, MutableList<String>>()
    for (name in friends.keys) {
        for (set_name in friends[name]!!) {
            map_back.put(set_name, mutableListOf<String>())
        }
        map_back.put(name, mutableListOf<String>())
    }
    //реализую цикл в котором обойду весь сет принадлежащий текущему имени
    for (name in map_back.keys) {
        if (friends.containsKey(name)) map_back[name] = friends[name]!!.toMutableList()
        //для всех имен принадлежащих искомому обхожу все их имена и добавляю в список к искомому
        var index: Int = 0
        while (index < map_back[name]!!.size) {
            var set_name = map_back[name]!![index]
            if (friends.containsKey(set_name) && !friends[set_name]!!.isEmpty()) {
                for (element in friends[set_name]!!) {
                    if (!map_back[name]!!.contains(element) && element != name) map_back[name]!!.add(element)
                }
            }
            index++
        }
    }
    //так делаю до тех пор пока не упрусь в ситуацию когда я не нашел ничего либоя встретил только себя либо все кого я встретил уже есть в списке
    //это нужно проделать для всех

    //привожу возвращаемое значение к требуемому типу и возвращаю
    var map_last = mutableMapOf<String, Set<String>>()
    for (name in map_back.keys) {
        map_last.put(name, mutableSetOf<String>())
    }
    for (name_set in map_last.keys) {
        map_last[name_set] = map_back[name_set]!!.toSet()
    }
    return map_last
}


/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    if (!b.isEmpty() || !a.isEmpty()) {
        for (b_key in b.keys) {
            if (a.containsKey(b_key) && a[b_key] == b[b_key]) a.remove(b_key)
        }
    }
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    var back_list = mutableListOf<String>()

    for (man in a) {
        if (b.contains(man)) back_list.add(man)
    }

    return back_list.toList()
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    var word_letters = mutableListOf<Char>()
    word_letters = word.toMutableList()

    for (i in word_letters) {
        if (!chars.contains(i)) return false
    }
    return true
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    var map_back = mutableMapOf<String, Int>()
    if (!list.isEmpty()) {
        for (i in list) {
            if (!map_back.containsKey(i)) map_back.put(i, 0)
            var counter: Int = 0
            for (j in list) {
                if (i == j) {
                    counter++
                }
            }
            map_back.put(i, counter)
        }
        while (true) {
            var size: Int = map_back.size
            for ((key, value) in map_back) {
                if (map_back[key] == 1) {
                    map_back.remove(key)
                    break
                }
            }
            if (size > map_back.size) continue else break
        }
    }
    return map_back.toMap()
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {

    for (i in words) {                              //берем все слова поочереди
        var number_of_word: Int = words.indexOf(i)
        i.toMutableList()                           // и разбиваем на буквы
        while (number_of_word < words.size - 1) {
            if (words[number_of_word + 1].length == i.length) {
                for (index_of_char in i) {
                    if (words[number_of_word + 1].contains(index_of_char)) {
                        if (index_of_char == i.last()) return true
                        continue
                    } else {
                        number_of_word++
                        break
                    }
                }
            } else {
                number_of_word++
                continue
            }
        }
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    if (!list.isEmpty()) {
        for (i in 0..list.size - 1) {
            for (j in i + 1..list.size - 1) {
                if (list[i] + list[j] == number) {
                    return Pair(i, j)
                }
            }
        }
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    var map_of_treasures = mutableMapOf<Double, String>()

    for (i in treasures.keys) {
        map_of_treasures.put((treasures[i]!!.second/treasures[i]!!.first).toDouble(), i)
    }
    var list_of_costs = mutableListOf<Double>()
    for (i in map_of_treasures.keys) {
        list_of_costs.add(i)
    }
    list_of_costs.sortDescending()

    var set_back = mutableSetOf<String>()
    var total_weight: Int = 0

    for (i in list_of_costs) {
        if (total_weight + treasures[map_of_treasures[i]]!!.first <= capacity) {
            set_back.add(map_of_treasures[i]!!)
            total_weight += treasures[map_of_treasures[i]]!!.first
        }
    }
    return set_back

}
