
using System;
using System.IO;

class StudentFile
{
    static void Main9()
    {
        string filePath = "student.txt";
        using (StreamWriter writer = new StreamWriter(filePath))
        {
            writer.WriteLine("Student Details");
            writer.WriteLine("Name-Ankit");
            writer.WriteLine("Roll No:101");
            writer.WriteLine("Course:MCA");
            writer.WriteLine("Marks:85");
        }

        Console.WriteLine("Student details written successfully.");

        
        using (StreamReader reader = new StreamReader(filePath))
        {
            string content = reader.ReadToEnd();

            Console.WriteLine("Contents of the file:");
            Console.WriteLine(content);
        }
    }
}