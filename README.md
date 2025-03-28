# Задача Люди

## Описание
В этом задании попрактикуемся с шаблоном *Builder* (*Строитель*). Мы спроектируем класс `Person`, в котором будут храниться данные о человеке:
* **Имя** (`String`). Каждый человек обязан иметь имя, причём с момента создания объекта изменить его нельзя.
* **Фамилия** (`String`). Каждый человек обязан иметь фамилию, причём с момента создания объекта изменить её нельзя.
* **Возраст** (`int`). Если возраст человека известен, то с момента создания объекта он может быть изменён только увеличением на единицу через вызов метода `happyBirthday()`. Возраст человека может быть неизвестен, в этом случае метод `boolean hasAge()` должен вернуть `false`, иначе - `true`. Подумайте, как эффективнее хранить в объекте информацию о том, известен ли возраст человека.
* **Текущий город жительства** (`String`). Может быть известен (в этом случае метод `boolean hasAddress()` должен вернуть `true`, иначе - `false`) и выставлен в любой через `setAddress(String city)`.

Все данные о человеке должны быть доступны через соответствующие методы (например, `String getName()`), поля же класса не должны быть `public`. 

Также надо создать класс `PersonBuilder` для конструирования объектов класса `Person`. Объекту этого класса (далее - *билдер*) можно выставлять любые данные для будущего объекта класса `Person` через методы (например, `setName(String name)`). И в этом объекте будет метод `Person build()`, возвращающий объект класса `Person` с указанными билдеру данными. В случае, если мы билдеру не указали достаточное количество данных (например, не указали фамилию), то метод `build()` должен выкинуть `IllegalStateException` с осмысленным сообщением. Если же мы передали неподходящие данные билдеру (например, некорректный возрст `builder.setAge(-100)`), то именно этот метод должен выкинуть `IllegalArgumentException` с осмысленным сообщением. Каждый метод добавления данных в билдер должен возвращать самого себя чтобы можно было сделать, например, вот так:
```java
Person person = new PersonBuilder()
                  .setName("Антошка")
                  .setSurname("Лопатов")
                  .setAge(48)
                  .build();
```

Также в класс `Person` надо добавить метод `PersonBuilder newChildBuilder()`, который будет возвращать полузаполненный билдер для ребёнка, а именно: с уже заполненными фамилией (родительской), возрастом и текущим городом жительства (родительским).

Продемонстрируйте работу ваших классов в классе `Main` (необязательно реализовывать ввод данных от пользователя).

## Реализация
1. Создайте класс `Person` с полями, необходимыми для хранения данных, указанных в условии.
```java
public class Person {
  protected final String name;
  protected final String surname;
  //...

  public Person(String name, String surname) {
    //...
  }

  public Person(String name, String surname, int age) {
    //...
  }
}
```
2. Наполните класс `Person` методами, нужными для реализации поведения объектов этого класса как описано выше в условии.
```java
public class Person {
  //...

  public boolean hasAge() { /*...*/ }
  public boolean hasAddress() { /*...*/ }

  public String getName() { /*...*/ }
  public String getSurname() { /*...*/ }
  public OptionalInt getAge() { /*...*/ }
  public String getAddress() { /*...*/ }

  public void setAddress(String address) { /*...*/ }
  public void happyBirthday() { /*...*/ }

  @Override
  public String toString() { /*...*/ }

  @Override
  public int hashCode() { /*...*/ }
}
```
3. Создайте класс `PersonBuilder`, наполните его полями для данных будущего объекта класса `Person` и методами их наполняющими (не забудьте про `IllegalArgumentException` в случае ввода недопустимых данных)
```java
public class PersonBuilder {
  //...

  public PersonBuilder setName(String name) { /*...*/ }
  public PersonBuilder setSurname(String surname) { /*...*/ }
  public PersonBuilder setAge(int age) { /*...*/ }
  public PersonBuilder setAddress(String address) { /*...*/ }

  public Person build() { /*...*/ }
}
```
4. Добавьте метод для получения полузаполненного билдера для ребёнка в класс `Person`
```java
public class Person {
  //...

  public PersonBuilder newChildBuilder() { /*...*/ }
}
```
6. Добавьте класс `Main` для демонстрации
```java
public class Main {
  public static void main(String[] args) {
    Person mom = new PersonBuilder()
                  .setName("Анна")
                  .setSurname("Вольф")
                  .setAge(31)
                  .setAddress("Сидней")
                  .build();
    Person son = mom.newChildBuilder()
                  .setName("Антошка")
                  .build();
    System.out.println("У " + mom + " есть сын, " + son);

    try {
      // Не хватает обяхательных полей
      new PersonBuilder().build(); 
    } catch (IllegalStateException e) {
      e.printStackTrace(); 
    }

    try {
      // Возраст недопустимый
      new PersonBuilder().setAge(-100).build();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }
}
```

# Задача «Логгер»

## Описание
В этом задании попрактикуемся с шаблоном *Singleton* (*Одиночка*). Мы пишем программу, которая будет каждый свой шаг обильно логгировать в консоль, но не напрямую через `System.out.println`, а через объект-логгер нашего собственного класса-синглтона.

### Функционал программы
1. Программа здоровается с пользователем, просит ввести два числа: размер списка `N` и верхнюю границу значений элементов в списке `M`.
2. Программа создаёт список `ArrayList` из `N` элементов и заполняет их случайными числами от `0` до `M`.
3. Программа просит пользователя ввести число `f` для фильтрации списка.
4. Программа создаёт объект `filter` вашего класса `Filter`, передав в конструктор значение `f`
5. Программа вызывает у `filter` метод `List<Integer> filterOut(List<Integer> list)`, передавая созданный случайный список в качестве параметра и принимая в качестве ответа список, который идентичен исходному, если пропустить элементы меньше `f`
6. Программа выводит итоговый список на экран и завершает свою работу

### Логгер
Каждое действие программы, будь то некорректный ввод пользователем входных данных или решение не добавлять элемент в результирующий список в методе `filterOut`, должно быть залоггировано. Для этого надо создать синглтон-класс `Logger` у которого будет метод `void log(String msg)` для вывода на экран сообщения. Сообщение должно выводиться в формате: `[<дата и номер сообщения>] <текст сообщения>`, где `<номер сообщения>` это порядковый номер выводимого логгером сообщения, а `<текст сообщения>` это значение параметра `msg`.

## Пример запуска
*Жирным шрифтом в примере это ввод пользователем данных*
> [31.12.2019 15:38:22 1] Запускаем программу  <br/>
> [31.12.2019 15:38:22 2] Просим пользователя ввести входные данные для списка <br/>
> Введите размер списка: **7**<br/>
> Введите верхнюю границу для значений: **10** <br/>
> [31.12.2019 15:38:23 3] Создаём и наполняем список <br/>
> Вот случайный список: 3 5 5 1 0 3 6 <br/>
> [31.12.2019 15:38:23 4] Просим пользователя ввести входные данные для фильтрации <br/>
> Введите порог для фильтра: **4** <br/>
> [31.12.2019 15:38:23 5] Запускаем фильтрацию <br/>
> [31.12.2019 15:38:23 6] Элемент "3" не проходит <br/>
> [31.12.2019 15:38:23 7] Элемент "5" проходит <br/>
> [31.12.2019 15:38:23 8] Элемент "5" проходит <br/>
> [31.12.2019 15:38:23 9] Элемент "1" не проходит <br/>
> [31.12.2019 15:38:23 10] Элемент "0" не проходит <br/>
> [31.12.2019 15:38:24 11] Элемент "3" не проходит <br/>
> [31.12.2019 15:38:24 12] Элемент "6" проходит <br/>
> [31.12.2019 15:38:24 13] Прошло фильтр 3 элемента из 7 <br/>
> [31.12.2019 15:38:24 14] Выводим результат на экран <br/>
> Отфильтрованный список: 5 5 6 <br/>
> [31.12.2019 15:38:24 15] Завершаем программу <br/>

## Реализация
1. Создайте класс `Logger` с методом `void log(String msg)` для логгирования сообщения в консоль описанным выше форматом (для поддержки счётчика сообщений заведите у логгера и инкрементируйте при логгировании числовое поле `int num`).
```java
public class Logger {
  protected int num = 1;

  public void log(String msg) {
    System.out.println("[" + num++ + "] " + msg);
  }
}
```
2. Примените шаблон *Singleton* (*Одиночка*) к классу `Logger` чтобы во всей программе у этого класса был только один объект, для чего подобно примеру из лекции сделайте конструктор приватным и создайте статичный метод `Logger getInstance()` для получения одного и того же объекта класса `Logger` при любом повторном вызове (сам же этот объект храните в статичном приватном поле `private static Logger instance`).
```java
public class Logger {
  //...

  // В этом поле храним ссылку на тот
  // единственный объект этого класса
  // который будем отдавать пользователям
  private static Logger logger;

  // Запрещаем пользователям пользоваться
  // конструктором нашего класса
  private Logger() {}

  // Пользователи которым нужен объект
  // нашего класса получают всегда один
  // и тот же объект, который мы храним
  // в приватном статичном поле, которое
  // мы заполняем в этом методе если оно
  // до того не было заполнено
  public static Logger getInstance() {
    //...
  }
}
```
3. Создайте класс `Filter` с конструктором, принимающим параметр `f`, и методом `List<Integer> filterOut(List<Integer> list)` для фильтрации списка. Используйте внутри него логгирование (можно как в примере вывода выше), но не передавайте логгер объекту фильтра через параметры, а сделайте `Logger logger = Logger.getInstance()` прямо там в коде, где он нужен.
```java
public class Filter {
  protected int treshold;

  public Filter(int treshold) {
    this.treshold = treshold;
  }

  public List<Integer> filterOut(List<Integer> source) {
    Logger logger = Logger.getInstance();
    List<Integer> result = new ArrayList<>();
    //..
    return result;
  }
}
```
4. Создайте класс `Main`, в котором вы будете коммуницировать с пользователем и реализовывать функционал программы, не забывая обильно логгировать свои шаги через написанный вами логгер.
```java
public class Main {
  public static void main(String[] args) {
    Logger logger = Logger.getInstance();
    //...
  }
}
```
5. Для заполнения списка `ArrayList` случайными числами используйте генератор случайных чисел `Random random = new Random()` и его метод `random.nextInt(maxValue)`.


# Задача Бесконечная итерация

## Описание
В этом задании попрактикуемся с шаблоном *Iterator* (*Итератор*).

Нужно написать класс, расширяющий `Iterable<Integer>`, по которому мы будем итерироваться. В процессе итерирования мы будем от него получать случайные числа в диапазоне значений. Диапазон задаётся двумя числами - минимальным и максимальным значениями. Передаются они через параметры конструктора. Таким образом, итератор по объектам нашего класса будет итерироваться по бесконечной последовательности из случайных чисел в заданом интервале:


```java
public class Randoms implements Iterable<Integer> {
  protected Random random;

  public Randoms(int min, int max) {
    //...
  }

  //...
}
```

Пример использования вашего класса:
```java
public static void main(String[] args) {
  for (int r : new Randoms(90, 100)) {
    System.out.println("Случайное число: " + r);
    if (r == 100) {
      System.out.println("Выпало число 100, давайте на этом закончим");
      break;
    }
  }
}
```

## Реализация
1. Создайте класс `Randoms`, скопируйте его заготовку из кода выше.
2. Реализуйте требуемый интерфейсом `Iterable` метод, для чего вам может понадобиться создать вспомогательный класс для итератора (реализующего `Iterator<Integer>`) по вашему классу.
3. Для генерации случайных чисел используйте встроенный класс `Random`.



