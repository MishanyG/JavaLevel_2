package HomeWork_3L2;

import java.util.*;

public class PhoneBook
{
    HashMap<String, Set<String>> map = new HashMap<>();

    public void addData(Person person)
    {
        Set<String> data = add(person.getFamily());
        data.add(person.getNumber());
        data.add(person.getMail());
        map.put(person.getFamily(), data);
        System.out.println(map);
    }

    public ArrayList<String> searchNum(String fam)
    {
        String[] b = map.get(fam).toArray(new String[0]);
        return new ArrayList<>(Arrays.asList(b).subList(0, map.get(fam).size() / 2));
    }
    public ArrayList<String> searchMail(String fam)
    {
        String[] b = map.get(fam).toArray(new String[0]);
        return new ArrayList<>(Arrays.asList(b).subList(map.get(fam).size() / 2, map.get(fam).size()));
    }
    public Set<String> add(String q)
    {
        Set<String> dat = map.getOrDefault(q, new TreeSet<>());
        if (dat.isEmpty())
            map.put(q, dat);
        return dat;
    }
}
