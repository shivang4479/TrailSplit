
using System;

class FiveStudentMarks
{
    static void Main()
    {
        int[] marks = new int[5];
        int total = 0;

        try
        {
            for (int i = 0; i < 5; i++)
            {
                Console.Write("Enter marks  " + (i + 1) );
                marks[i] = int.Parse(Console.ReadLine());
                if (marks[i] < 0 || marks[i] > 100)
                {
                    throw new ArgumentOutOfRangeException(
                        "Marks between 0 and 100."
                    );
                }
                total += marks[i];
            }
            double percentage = total / 5.0;

            Console.WriteLine("Total Marks" + total);
            Console.WriteLine("Percentage" + percentage);
        }
        catch (FormatException)
        {
            Console.WriteLine("Please enter numeric values only.");
        }
        catch (ArgumentOutOfRangeException)
        {
            Console.WriteLine("Marks must be between 0 and 100.");
        }
        catch (Exception ex)
        {
            Console.WriteLine("Error: " + ex.Message);
        }
    }
}