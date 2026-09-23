using System;

class Indexerror
{
    static void Main3()
    {
        int[] arr = new int[5];
        Console.WriteLine("Enter 5 integers");
        for (int i = 0; i < 5; i++)
        {
            arr[i] = Convert.ToInt32(Console.ReadLine());
        }

        try
        {
            Console.Write("Enter an index (0-4)");
            int index = Convert.ToInt32(Console.ReadLine());
            Console.WriteLine("Element at index " + index + " = " + arr[index]);
        }
        catch (IndexOutOfRangeException)
        {
            Console.WriteLine("Index is out of range must between 0 and 4.");
        }
    }
}