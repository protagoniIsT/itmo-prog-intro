solutions for ITMO CT S1 y2023 programming course
============================================================================================================================================

# Тесты к курсу «Введение в программирование»

[Условия домашних заданий](https://www.kgeorgiy.info/courses/prog-intro/homeworks.html)

## Домашнее задание 14. Обработка ошибок

Модификации
 * *Base*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [TripleParser](java/expression/exceptions/TripleParser.java)
 * *Zeroes* (32, 33)
    * Дополнительно реализуйте унарные операции
      * `l0` – число старших нулевых бит, `l0 123456` равно 15;
      * `t0` – число младших нулевых бит, `t0 123456` равно 6.
 * *PowLog2* (34, 35)
    * Дополнительно реализуйте унарные операции:
        * `log2` – логарифм по уснованию 2, `log2 10` равно 3;
        * `pow2` – два в степени, `pow2 4` равно 16.
 * *MinMax* (36-39)
    * Дополнительно реализуйте бинарные операции (минимальный приоритет):
        * `min` – минимум, `2 min 3` равно 2;
        * `max` – максимум, `2 max 3` равно 3.
 * *Shifts* (38, 39)
    * Дополнительно реализуйте бинарные операции с минимальным приоритетом:
        * `<<` – сдвиг влево (`1 << 5 + 3` равно `1 << (5 + 3)` равно 256);
        * `>>` – сдвиг вправо (`1024 >> 5 + 3` равно `1024 >> (5 + 3)` равно 4);
        * `>>>` – арифметический сдвиг вправо (`-1024 >>> 5 + 3` равно `1024 >>> (5 + 3)` равно -4);


## Домашнее задание 13. Разбор выражений

Модификации
 * *Base*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [TripleParser](java/expression/parser/TripleParser.java)
    * Результат разбора должен реализовывать интерфейс
        [TripleExpression](java/expression/TripleExpression.java)
    * [Исходный код тестов](java/expression/parser/ParserTest.java)
        * Первый аргумент: `easy` или `hard`
        * Последующие аргументы: модификации
 * *Bitwise* (34-39)
    * Дополнительно реализуйте бинарные операции:
        * `&` – побитное И, приоритет меньше чем у `+` (`6 & 1 + 2` равно `6 & (1 + 2)` равно 2);
        * `^` – побитный XOR, приоритет меньше чем у `&` (`6 ^ 1 + 2` равно `6 ^ (1 + 2)` равно 5);
        * `|` – побитное ИЛИ, приоритет меньше чем у `^` (`6 | 1 + 2` равно `6 | (1 + 2)` равно 7);
 * *Ones* (36-39)
    * Дополнительно реализуйте унарные операции
      * `l1` – число старших единичных бит, `l1 -12345` равно 18;
      * `t1` – число младших единичных бит, `t1 12345` равно 3.
 * *LowHigh* (38, 39)
    * Дополнительно реализуйте унарные операции:
        * `low` – младший установленный бит, `low 123456` равно 64;
        * `high` – старший установленный бит, `high 123456` равно 65536.
 * *Not* (33, 35)
    * Дополнительно реализуйте унарную операцию
      `~` – побитное отрицание, `~-5` равно 4.


## Домашнее задание 12. Выражения

Модификации
 * *Base*
    * Реализуйте интерфейс [Expression](java/expression/Expression.java)
    * [Исходный код тестов](java/expression/ExpressionTest.java)
        * Первый аргумент: `easy` или `hard`.
        * Последующие аргументы: модификации.
 * *Triple* (32-39)
    * Дополнительно реализуйте поддержку выражений с тремя переменными: `x`, `y` и `z`.
    * Интерфейс/тесты [TripleExpression](java/expression/TripleExpression.java).
 * *BigInteger* (36, 37)
    * Дополнительно реализуйте вычисления в типе `BigInteger`.
    * Интерфейс/тесты [BigIntegerExpression](java/expression/BigIntegerExpression.java).
 * *BigDecimal* (38, 39)
    * Дополнительно реализуйте вычисления в типе `BigDecimal` .
    * Интерфейс/тесты [BigDecimalExpression](java/expression/BigDecimalExpression.java).


## Домашнее задание 11. Игра m,n,k

Тесты не предусмотрены. Решение должно находиться в пакете `game`.

Модификации
 * *Круг* (36-39)
    * Добавьте поддержку доски в форме круга.
    * В качестве примера, сделайте доску диаметром 10.
 * *Дополнительные ходы* (34 - 37)
    * Если в результате хода игрока на доске появляется новая последовательность
      из 4+ одинаковых символов, то он делает дополнительный ход.
    * Игрок может сделать несколько дополнительных ходов подряд.
 * *Техническая ничья* (38, 39)
    * Если при текущем состоянии доски ни один из игроков не может выиграть,
      даже если сделает `k` ходов подряд, то должна объявляться ничья.
 * *Олимпийская система* (32 - 39)
    * Добавьте поддержку турниров по 
      [олимпийской системе](https://ru.wikipedia.org/wiki/Олимпийская_система).
    * Стороны в матче выбираются случайно.
    * При ничьей игроки играют до результативной партии.
    * Выбывшие на одном круге делят одно место.


## Домашнее задание 9. Markdown to HTML

Модификации
 * *Базовая*
    * [Исходный код тестов](java/md2html/Md2HtmlTester.java)
    * [Откомпилированные тесты](artifacts/Md2HtmlTest.jar)
        * Аргументы командной строки: модификации
 * *Quote* (32, 33)
    * Добавьте поддержку `''цитат''`: `<q>цитат</q>`
 * *Var* (34, 35)
    * Добавьте поддержку `%переменных%`: `<var>переменных</var>`
 * *Pre* (36, 37)
    * Добавьте поддержку
      \`\`\``кода __без__ форматирования`\`\`\`:
      `<pre>кода __без__ форматирования</pre>`
 * *InsDel* (38, 39)
    * Добавьте поддержку
        `<<вставок>>`: `<ins>вставок</ins>` и
        `}}удалений{{`: `<del>удалений</del>`


## Домашнее задание 7. Разметка

Модификации
 * *Base*
    * Исходный код тестов:
        * [MarkupTester.java](java/markup/MarkupTester.java)
        * [MarkupTest.java](java/markup/MarkupTest.java)
        * Аргументы командной строки: модификации
    * Откомпилированных тестов не существуют,
      так как они зависят от вашего кода
 * *BBCode* (32-35)
    * Дополнительно реализуйте метод `toBBCode`, генерирующий [BBCode](https://en.wikipedia.org/wiki/BBCode)-разметку:
      * выделеный текст окружается тегом `[i]`;
      * сильно выделеный текст окружается тегом `[b]`;
      * зачеркнутый текст окружается тегом `[s]`.
 * *BBCodeList* (36-39)
    * Сделайте модификацию *BBCode*
    * Добавьте поддержку:
      * Нумерованных списков (класс `OrderedList`, тег `[list=1]`): последовательность элементов
      * Ненумерованных списков (класс `UnorderedList`, тег `[list]`): последовательность элементов
      * Элементов списка (класс `ListItem`, открывающий тег `[*]`): последовательность абзацев и списков
    * Для новых классов поддержка Markdown не требуется
    * [Исходный код тестов](java/markup/MarkupListTest.java)


## Домашнее задание 6. Подсчет слов++

Модификации
 * *Base*
    * Класс должен иметь имя `Wspp`
    * Исходный код тестов:
        [WsppTest.java](java/wspp/WsppTest.java),
        [WsppTester.java](java/wspp/WsppTester.java)
    * Откомпилированные тесты: [WsppTest.jar](artifacts/WsppTest.jar)
        * Аргументы командной строки: модификации
 * *Position* (32, 33)
    * Вместо номеров вхождений во всем файле надо указывать
      `<номер строки>:<номер в строке>`,
      где номер в строке считается с конца
    * Класс должен иметь имя `WsppPosition`
 * *SortedPosition* (34, 35)
    * В выходном файле слова должны быть упорядочены
      в лексикографическом порядке
    * Вместо номеров вхождений во всем файле надо указывать
      `<номер строки>:<номер в строке>`,
      где номер в строке считается с конца
    * Класс должен иметь имя `WsppSortedPosition`
 * *SortedFirst* (36, 37)
    * В выходном файле слова должны быть упорядочены
      в лексикографическом порядке
    * Вместо номеров вхождений во всем файле надо указывать
      только первое вхождение в каждой строке
    * Класс должен иметь имя `WsppSortedFirst`
 * *SortedRFirst* (38, 39)
    * В выходном файле слова должны быть упорядочены
      в лексикографическом порядке перевёрнутых слов
    * Вместо номеров вхождений во всем файле надо указывать
      только первое вхождение в каждой строке
    * Класс должен иметь имя `WsppSortedRFirst`


## Домашнее задание 5. Свой сканнер

Модификации
 * *Base*
    * Исходный код тестов: [FastReverseTest.java](java/reverse/FastReverseTest.java)
    * Откомпилированные тесты: [FastReverseTest.jar](artifacts/FastReverseTest.jar)
        * Аргументы командной строки: модификации
 * *MinRAbc* (32, 33)
    * Вместо каждого числа выведите минимум из чисел, предшествующих
      ему в строки и его самого
    * Во вводе и выводе десятичные числа пишутся буквами:
      нулю соответствует буква `a`, единице – `b` и так далее
    * Класс должен иметь имя `ReverseMinRAbc`
 * *MinСAbc* (34, 35)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите минимум из чисел,
      находящихся в его столбце в предыдущих строках, и его самого
    * Во вводе и выводе десятичные числа пишутся буквами:
      нулю соответствует буква `a`, единице – `b` и так далее
    * Класс должен иметь имя `ReverseMinCAbc`
 * *SumHexAbc* (36, 37)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите сумму чисел
      в прямоугольнике верхний левый угол матрицы — текущее число
    * На вход подаются десятичные и шестнадцатеричные числа
    * Шестнадцатеричные числа имеют префикс `0x`
    * Десятичные числа записаны буквами:
      нулю соответствует буква `a`, единице – `b` и так далее
    * В выходе должны быть десятичные числа, записаные буквами
    * Класс должен иметь имя `ReverseSumHexAbc`
 * *SumHexDecAbc* (38, 39)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите сумму чисел
      в прямоугольнике верхний левый угол матрицы — текущее число
    * На вход подаются десятичные и шестнадцатеричные числа
    * Шестнадцатеричные числа имеют префикс `0x`
    * Десятичные числа могут быть записаны буквами:
      нулю соответствует буква `a`, единице – `b` и так далее
    * В выходе должны быть десятичные числа, записаные буквами
    * Класс должен иметь имя `ReverseSumHexDecAbc`


## Домашнее задание 4. Подсчет слов

Модификации
 * *Base*
    * Класс должен иметь имя `WordStatInput`
    * Исходный код тестов:
        [WordStatTest.java](java/wordStat/WordStatTest.java),
        [WordStatTester.java](java/wordStat/WordStatTester.java),
        [WordStatChecker.java](java/wordStat/WordStatChecker.java)
    * Откомпилированные тесты: [WordStatTest.jar](artifacts/WordStatTest.jar)
        * Аргументы командной строки: модификации
 * *CountPrefixL* (32, 33)
    * Выходной файл должен содержать все различные префиксы длины 3
      слов, встречающихся во входном файле, упорядоченые
      по возрастанию числа вхождений, а при равном числе вхождений –
      по порядку первого вхождения во входном файле.
      Слова длины меньшей 3 игнорируются.
    * Класс должен иметь имя `WordStatCountPrefixL`
 * *Count* (34, 35)
    * В выходном файле слова должны быть упорядочены
      по возрастанию числа вхождений, а при равном числе вхождений –
      по порядку первого вхождения во входном файле.
    * Класс должен иметь имя `WordStatCount`
 * *CountMiddleL* (36, 37)
    * Назовём _серединой слова_ подстроку, полученую удалением
      первых и последних 2 символов слова.
      Слова длины меньшей 5 игнорируются.
    * Выходной файл должен содержать все различные середины слов
      слов, встречающихся во входном файле,
      упорядоченые по возрастанию числа вхождений, а при равном числе вхождений –
      по порядку первого вхождения во входном файле.
    * Класс должен иметь имя `WordStatCountMiddleL`
 * *CountAffixL* (38, 39)
    * Назовём _аффиксом слова_ его префикс и суффикс длины 2.
      Слова длины меньшей 2 игнорируются.
    * Выходной файл должен содержать все различные 
      аффиксы слов, встречающихся во входном файле,
      упорядоченые по возрастанию числа вхождений, а при равном числе вхождений –
      по порядку первого вхождения во входном файле.
    * Класс должен иметь имя `WordStatCountAffixL`
 * *FastSort* (36 - 39)
    * Пусть _n_ – число слов во входном файле,
      тогда программа должна работать за O(_n_ log _n_).




## Домашнее задание 3. Реверс

Модификации
 * *Base*
    * Исходный код тестов:
        [ReverseTest.java](java/reverse/ReverseTest.java),
        [ReverseTester.java](java/reverse/ReverseTester.java)
    * Откомпилированные тесты: [ReverseTest.jar](artifacts/ReverseTest.jar)
        * Аргументы командной строки: модификации
 * *MinR* (32, 33)
    * Вместо каждого числа выведите минимум из чисел, предшествующих
      ему в строки и его самого
    * Класс должен иметь имя `ReverseMinR`
 * *MinС* (34, 35)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите минимум из чисел,
      находящихся в его столбце в предыдущих строках, и его самого
    * Класс должен иметь имя `ReverseMinC`
 * *SumHex* (36, 37)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите сумму чисел
      в прямоугольнике верхний левый угол матрицы — текущее число.
    * Во вводе и выводе используются числа в шестнадцатеричной системе счисления.
    * Класс должен иметь имя `ReverseSumHex`.
 * *SumHexDec* (38, 39)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите сумму чисел
      в прямоугольнике верхний левый угол матрицы — текущее число.
    * На вход подаются десятичные и шестнадцатеричные числа.
    * Шестнадцатеричные числа имеют префикс `0x`.
    * Класс должен иметь имя `ReverseSumHexDec`.
 * *Memory* (36-39)
    * Пусть _M_ – объём памяти, необходимый для сохранения ввода
      в двумерном массиве `int` минимального размера.
      Ваша программа должна использовать не более 4_M_ + 1024 байт памяти.
    * Накладные расходы на запуск вашей программы JVM не учитываются.


## Домашнее задание 2. Сумма чисел

Модификации
 * *Double* (32, 33)
    * Входные данные являются 64-битными числами с формате с плавающей точкой
    * Класс должен иметь имя `SumDouble`
 * *DoubleSpace* (34, 35)
    * Входные данные являются 64-битными числами с формате с плавающей точкой
    * Класс должен иметь имя `SumDoubleSpace`
    * Числа разделяются [пробелами-разделителями](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#SPACE_SEPARATOR)
 * *LongSpace* (36, 37)
    * Входные данные являются 64-битными целыми числами
    * Класс должен иметь имя `SumLongSpace`
    * Числа разделяются [пробелами-разделителями](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#SPACE_SEPARATOR)
 * *BigIntegerSpace* (38, 39)
    * Входные данные помещаются в тип [BigInteger](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/math/BigInteger.html)
    * Класс должен иметь имя `SumBigIntegerSpace`
    * Числа разделяются [пробелами-разделителями](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#SPACE_SEPARATOR)


Для того, чтобы протестировать программу:

 1. Скачайте откомпилированные тесты ([SumTest.jar](artifacts/SumTest.jar))
 1. Откомпилируйте `Sum.java`
 1. Проверьте, что создался `Sum.class`
 1. В каталоге, в котором находится `Sum.class`, выполните команду
    ```
       java -ea -jar <путь к SumTest.jar> Base
    ```
    * Например, если `SumTest.jar` находится в текущем каталоге, выполните команду
    ```
        java -ea -jar SumTest.jar Base
    ```
 1. Для ускорени отладки рекомендуется сделать скрипт, выполняющий шаги 2−4.

Исходный код тестов:

* [SumTest.java](java/sum/SumTest.java)
* [SumTester.java](java/sum/SumTester.java)
* [Базовые классы](java/base/)


## Домашнее задание 1. Запусти меня!

Модификации
  * *RunMe*
    1. Скачайте исходный код ([RunMe.java](java/RunMe.java))
    1. Создайте скрипт, компилирующий и запускающий `RunMe` из командной строки
       с выданными вам аргументами командной строки
    1. Следуйте выведенной инструкции

Рекомендации по выполнению модификации

1. Проверьте версию Java:
    1. Запустите `javac --version` и проверьте, что версия
       находится в диапазоне 17..20 (в крайнем случае, 11..21).
    1. Запустите `java --version` и проверьте, что версия,
       такая же как и у `javac`.
1. Скачайте [RunMe.java](java/RunMe.java)
1. Откомпилируйте `RunMe.java`:
    1. Запустите `javac RunMe.java`
    1. Убедитесь, что компиляция завершилась без ошибок
    1. Проверьте, что появился `RunMe.class`
1. Запустите `RunMe`:
    1. Запустите `java RunMe [шесть] [слов] [пароля] [пришедшего] [на] [email]`
    1. При правильном исполнении вы должны получить ссылку.
       Если получено сообщение об ошибке — исправьте её и запустите повторно
    1. Зайдите по полученной ссылке и убедитесь, что она правильная
1. Напишите и протестируйте скрипт:
    1. Напишите скрипт, включающий команды компиляции и запуска.
       Если вы не умеете писать скрипты, воспользуйтесь одной из инструкций:
       [Windows](https://www.windowscentral.com/how-create-and-run-batch-file-windows-10),
       [Linux](https://linuxhint.com/write_simple_bash_script/),
       [macOS](https://phoenixnap.com/kb/write-bash-script)
    1. Запустите и проверьте, что вы получили ту же ссылку, что и в предыдущем пункте
    1. Сдайте скрипт преподавателю
1. Вы можете получить больше плюсиков, модифицируя код `RunMe.java`
