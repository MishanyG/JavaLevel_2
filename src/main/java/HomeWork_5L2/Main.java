package HomeWork_5L2;

public class Main
{
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args)
    {
        float[] arr = new float[size];
        long a;

        a = matrixRun(arr);
        System.out.println("Время работы метода №1: " + (System.currentTimeMillis() - a));

        try {
            a = matrixSplit(arr, h);
            System.out.println("Время работы метода №2: " + (System.currentTimeMillis() - a));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static long matrixRun(float[] arr)
    {
        long a = System.currentTimeMillis();
        long b;
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1;
        b = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        System.out.println("Время выполнения расчётов метода №1: " + (System.currentTimeMillis() - b));
        return a;
    }

    private static long matrixSplit(float[] arr, int h) throws InterruptedException
    {
        long a = System.currentTimeMillis();
        long b;
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = 1;
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        b = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

            Runnable r1 = () -> {
                for (int i = 0; i < a1.length; i++)
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

                System.arraycopy(a1, 0, arr, 0, h);
            };
            Runnable r2 = () -> {
                for (int i = 0; i < a1.length; i++)
                        a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

                System.arraycopy(a2, 0, arr, h, h);
            };

        Thread stream_1 = new Thread(r1);
        Thread stream_2 = new Thread(r2);

        stream_1.start();
        stream_2.start();
        stream_1.join();
        stream_2.join();
        System.out.println("Время выполнения расчётов метода №2: " + (System.currentTimeMillis() - b));
        return a;
    }
}
