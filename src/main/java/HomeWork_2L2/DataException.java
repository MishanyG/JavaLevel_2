package HomeWork_2L2;

public class DataException extends Exception
{
    public DataException(int i, int j)
    {
        super("В строке "+(++i)+" столбца "+(++j)+" находится символ или слово!");
    }
}