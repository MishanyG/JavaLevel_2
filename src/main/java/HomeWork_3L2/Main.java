package HomeWork_3L2;

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        String[] text = {"Один", "Десять", "Три", "Два", "Пять", "Шесть", "Одинадцать", "Восемь", "Четыре", "Один",
                        "Пять", "Двенадцать", "Шесть", "Два", "Тринадцать", "Один", "Шесть", "Одинадцать", "Семь",
                        "Девять", "Десять", "Четырнадцать", "Пятнадцать", "Шестнадцать", "Один", "Семнадцать",
                        "Восемнадцать", "Девятнадцать", "Двадцать", "Семь"};

        LinkedHashSet<String> txt = new LinkedHashSet<>(Arrays.asList(text));
        for (String s : txt)
            System.out.println(s);

        HashMap<String, Integer> map = new HashMap<>();
        for (String s : text)
        {
            if (!map.containsKey(s))
                map.put(s, 0);
            map.put(s, map.get(s) + 1);
        }
        for (String s : map.keySet())
            System.out.println("Слово: " + "'" + s + "'" + " встречается в количестве: " + map.get(s));

        PhoneBook phoneBook = new PhoneBook();
        Person person;
        person = new Person("Ivanov", "111-222-333-444", "mail1@mail.ru");
        phoneBook.addData(person);
        person = new Person("Petrov", "555-666-777-888", "mail2@mail.ru");
        phoneBook.addData(person);
        person = new Person("Ivanov", "456-798-852-753", "mail3@mail.ru");
        phoneBook.addData(person);
        person = new Person("Sidorov", "159-426-759-884", "mail4@mail.ru");
        phoneBook.addData(person);
        person = new Person("Sergeev", "141-722-398-744", "mail5@mail.ru");
        phoneBook.addData(person);
        person = new Person("Ivanov", "654-547-357-657", "mail6@mail.ru");
        phoneBook.addData(person);
        person = new Person("Sidorov", "325-358-356-496", "mail7@mail.ru");
        phoneBook.addData(person);

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите фамилию для поиска номера и e-mail: ");
        String fam  = sc.nextLine();

        System.out.println(phoneBook.searchNum(fam));
        System.out.println(phoneBook.searchMail(fam));
    }
}
