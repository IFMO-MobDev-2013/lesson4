#GCalc - инженерный калькулятор под андройд.

(Работа Агапова Георгия, группа 2537)

Калькулятор работает в двух режимах:
 * int - целочисленная арифметика, использующая в реализации класс BigInteger
 * real - арифметика вещественных чисел, построена на double

Переключение меду режимами происходит по нажатию на крайнюю левую верхнюю кнопку (int/real).
Режим real содержит 5 наборов функций: exp, trig/atrig, hyp/ahyp.
Переключение между ними происходит с помощью кнопок в крайнем левом столбце (для обратных функций нажать дважды).

Калькулятор работает как в портретном, так и в ландшафтном режиме. В портретном функционал весьма ограничен.

Для возврата последнего вычисленного выражения в строку вычислений, следует нажать кнопку ↑ (крайний правый ряд, вторая снизу).
В случае, если введено некорректное выражение, строка ввода будет подсвечена красным.

Приоритеты операций расставлены по аналогии с C++. Операции из режима int не применимы к вещественным числам.

Галерея скриншотов: http://ge.tt/7r5LC7u?c

APK: https://dl.dropboxusercontent.com/u/3693476/apk/GCalc.apk

Материалы четвёртой лекции:
=======
http://yadi.sk/d/LNNSQXvPAWe8M<br />
http://yadi.sk/d/MyR0wn17AWeA3

Домашнее задание:
=======
Написать калькулятор, который обрабатывает следующие действия: + (включая унарный плюс), - (включая унарный минус), *, /, и поддерживает скобки. Калькулятор должен поддерживать как целые, так и вещественные числа, т.е. на экране должны содержаться следующие контролы:
* Поле ввода текста
* Кнопки цифр: "0"-"9"
* Кнопка десятичной точки "."
* Кнопки действий: "+", "-", "*", "/"
* Кнопка "равно"
* Кнопки скобок "(", ")"  (по желанию можно сделать одну кнопку "()", т.к. при вводе выражения всегда однозначно понятно какая скобка вводится)
* Кнопка удаления последнего введенного символа "<="
* Кнопка стирания всего выражения "C"

Также необходимо покрыть движок калькулятора unit test-ами, обычных JUnit (не android-specific) будет достаточно (см. http://developer.android.com/tools/testing/testing_android.html#JUnit)

Порядок сдачи:
=======
Сдавать задание нужно в виде форка и пулл-реквеста к https://github.com/IFMO-MobDev-2013/lesson4, в описании укажите ФИО и номер группы.
Пожалуйста, не забывайте коммитить проект целиком (включая apk), а не только activity.
Подробнее про пулл-реквесты можно почитать тут, например: http://habrahabr.ru/post/125999/ и https://help.github.com/articles/using-pull-requests.

Результат принимается до четверга (10 октября) 23:59. После этого оценка за это домашнее задание автоматически снижается в два раза.

Оценки:
=======
https://docs.google.com/spreadsheet/ccc?key=0AkYNnR0IM6SpdEJPcWRpUGNKYzRCUExnamJ4NmJMYXc&usp=sharing