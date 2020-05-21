package HomeWork_3L2;

public class Person
{
    String family;
    String number;
    String mail;

    public Person(String fam, String ph, String ma)
    {
        family = fam;
        number = ph;
        mail = ma;
    }

    public String getFamily() {
        return family;
    }

    public String getNumber() {
        return number;
    }

    public String getMail() {
        return mail;
    }
}
