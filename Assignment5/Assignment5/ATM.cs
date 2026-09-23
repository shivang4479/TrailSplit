using System;

class ATM
{
    static void Main2()
    {
        try
        {
            Console.Write("Enter account balance: ");
            double balance = Convert.ToDouble(Console.ReadLine());

            Console.Write("Enter withdraw amount: ");
            double amount = Convert.ToDouble(Console.ReadLine());

            if (amount > balance)
            {
                throw new Exception("Insufficient balance!");
            }

            balance = balance - amount;

            Console.WriteLine("Withd succes.");
            Console.WriteLine("Remain balance: " + balance);
        }
        catch (Exception ex)
        {
            Console.WriteLine("failed: " + ex.Message);
        }
        finally
        {
            Console.WriteLine("Thank using ATM.");
        }
    }
}