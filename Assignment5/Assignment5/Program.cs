using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment5
{
    internal class Program
    {
        static void Main0(string[] args)
        {
            Console.WriteLine("Enter Number ");
            int a = Convert.ToInt32(Console.ReadLine());
            Console.WriteLine("Enter Number");
            int b = Convert.ToInt32(Console.ReadLine());

            try
            {
                int result = a/b;
                Console.WriteLine("Division of a and b is" + result);

            }
            catch(DivideByZeroException ex) {
                Console.WriteLine("Divide by Zero not Allowed."+ex.Message);
            }
            finally
            {
                Console.WriteLine("Finally block Executed");
            }
        }
    }
}
