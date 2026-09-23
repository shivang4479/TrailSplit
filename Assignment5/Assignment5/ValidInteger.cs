using System;

class multiplecatch
{
    static void Main8()
    {
        try
        {
            Console.Write("Enter number");
            int num1 = Convert.ToInt32(Console.ReadLine());
            Console.Write("Enter number: ");
            int num2 = Convert.ToInt32(Console.ReadLine());
            int result = num1 / num2;
            Console.WriteLine("Result=" + result);
        }
        catch (FormatException)
        {
            Console.WriteLine("Please enter valid values");
        }
        catch (DivideByZeroException)
        {
            Console.WriteLine("Cannot divide  zero");
        }
        catch (Exception)
        {
            Console.WriteLine("Some unexpected error occurred");
        }
    }
}