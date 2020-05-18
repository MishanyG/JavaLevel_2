package HomeWork_2L2;

import java.util.Arrays;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            System.out.println(divide(mat("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0")));
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
        catch (DataException e)
        {
            System.out.println(e.getMessage());
        }
        catch (SizeException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static String[][] mat(String line) throws SizeException
    {
        String[] a = line.split("\n");
        String[][] b;
            if (a.length == 4)
                b = new String[][]{a[0].split(" "), a[1].split(" "), a[2].split(" "), a[3].split(" ")};
            else
                throw new SizeException();
        return b;
    }

    public static int divide(String[][] mat) throws DataException
    {
        int[][] intMas = new int[mat.length][mat.length];
        int add = 0, divide;

        for (int i = 0; i <= mat.length - 1; i++)
            for (int j = 0; j <= mat[i].length - 1; j++)
            {
                try
                {
                    intMas[i][j] = Integer.parseInt(mat[i][j]);
                }
                catch (NumberFormatException e)
                {
                    throw new DataException(i, j);
                }
            }

        for (int i = 0; i <= intMas.length - 1; i++)
            for (int j = 0; j <= intMas[i].length - 1; j++)
                add += intMas[i][j];

        divide = add / 2;

        return divide;
    }
}
