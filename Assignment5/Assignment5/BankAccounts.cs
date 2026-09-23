
using System;


class InsufficientBalanceException : Exception
{
    public InsufficientBalanceException(string message)
        : base(message)
    {
    }
}


class BankAccounts
{
    public double Balance { get; private set; }

    public BankAccounts(double balance)
    {
        Balance = balance;
    }

    public void Withdraw(double amount)
    {
        if (amount <= 0)
        {
            throw new ArgumentException(
                "Withdrawal amount must be positive."
            );
        }

        if (amount > Balance)
        {
            throw new InsufficientBalanceException(
                "Insufficient balance! Withdrawal denied."
            );
        }

        Balance -= amount;
        Console.WriteLine("Withdrawal successful.");
        Console.WriteLine("Withdrawn Amount: " + amount);
        Console.WriteLine("Remaining Balance: " + Balance);
    }
}

class BankAccount
{
    static void Main1()
    {
        BankAccounts account = new BankAccounts(5000);

        try
        {
            Console.WriteLine("Available Balance: " +
                              account.Balance);

            Console.Write("Enter withdrawal amount: ");
            double amount = double.Parse(Console.ReadLine());

            account.Withdraw(amount);
        }
        catch (InsufficientBalanceException ex)
        {
            Console.WriteLine("Error: " + ex.Message);
        }
        catch (ArgumentException ex)
        {
            Console.WriteLine("Error: " + ex.Message);
        }
        catch (FormatException)
        {
            Console.WriteLine("Error: Please enter a valid number.");
        }
        finally
        {
            Console.WriteLine("Transaction process completed.");
            Console.WriteLine("Final Balance: " +
                              account.Balance);
        }
    }
}