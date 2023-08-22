/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

import java.util.*;

class NotEnoughMoneyInAccountException extends Exception
{
    String message;
    NotEnoughMoneyInAccountException(String msg)
    {
        message=msg;
    }
}

class MinimumBalanceRequiredException extends Exception
{
    String message;
    MinimumBalanceRequiredException(String msg)
    {
        message=msg;
    }
}

class AccountNotFoundException extends Exception
{
    String message;
    AccountNotFoundException(String msg)
    {
        message=msg;
    }
}

class AadharMismatchException extends Exception
{
    String message;
    AadharMismatchException(String msg)
    {
        message=msg;
    }
}

class Account
{
public double balance;
public long  accountnumber;
public String accounttype;
public String accountholder;
public String aadharnumber;
public long  mobilenumber;



public void CreateAccount() throws AadharMismatchException
{
   Scanner sc=new Scanner(System.in);
   balance=500;
   System.out.println("Enter Account Number:");
   accountnumber=sc.nextLong();
   System.out.println("Enter Account Type(Current/Savings) :");
   accounttype=sc.next();
   System.out.println("Enter Account Holder's Name:");
   accountholder=sc.next();
   System.out.println("Enter Mobile Number:");
   mobilenumber=sc.nextLong();
   System.out.println("Enter Aadhar Number:");
   aadharnumber=sc.next();
   if(aadharnumber.length()!=12)
   {
       throw new AadharMismatchException("Enter valid Aadhar Number");
   }
   
   
   System.out.println("\n");
}

public void Deposit()
{
   Scanner sc=new Scanner(System.in);
   System.out.println("Enter Amount to be Deposited :");
   double DepositAmount=sc.nextDouble();
   balance+=DepositAmount;
}

public void Withdraw()throws MinimumBalanceRequiredException,NotEnoughMoneyInAccountException
{
   Scanner sc=new Scanner(System.in);
   System.out.println("Enter Amount to Withdraw :");
   double WithdrawAmount=sc.nextDouble();
   if(WithdrawAmount>balance)
   {
       throw new NotEnoughMoneyInAccountException("Insufficient Money in Account");
   }
   else if(balance-WithdrawAmount<500)
   {
       throw new MinimumBalanceRequiredException("Mininum Balance Required");
   }
   balance-=WithdrawAmount;
}

public double getBalance()
{
   return balance;
}
public void display()
{
       System.out.println("Account Number :"+accountnumber);
       System.out.println("Account Holder's Name :"+accountholder);
       System.out.println("Account Type :"+accounttype);
       System.out.println("Aadhar Number :"+aadharnumber);
       System.out.println("Balance :"+balance);
       System.out.println("Mobile Number :"+mobilenumber);
}

public void checkAccount(long accnumber)throws AccountNotFoundException
{
   int flag=0;
   if(accnumber==accountnumber)
   {
       flag=1;
       
       
   }
   if(flag==0)
   {
       System.out.println("Account Not Found");
       throw new AccountNotFoundException("Account Not Found");
       
   }
   else
   {
       System.out.println("*****Account Found*****");
       display();
       System.out.println("\n");
   }
   
}


}

class Main
{
    public static void main (String[] args)
    {
        Scanner sc=new Scanner(System.in);
        char ch;
        int choice=0;
        System.out.println("Enter number of Accounts to Store :");
        int n=sc.nextInt();
        Account Acc[]=new Account[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("\nAccount Holder "+(i+1));
            try
            {
                Acc[i]=new Account();
                Acc[i].CreateAccount();
            }
            catch(AadharMismatchException ae)
            {
                System.out.println("Error Occured :"+ae.message);
            }
           
        }
        for(int i=0;i<n;i++)
        {
            System.out.println("*****Account Details*****");
            Acc[i].display();
            System.out.println("\n");
        }
       
        do
        {
            {
           

        System.out.println("Enter Your Account Number :");
        long accno=sc.nextLong();
        System.out.println("\n");
        try
        {
        for(int i=0;i<n;i++)
        {
            Acc[i].checkAccount(accno);
           
           
          break;
           
           
       
        }
        }
         catch(AccountNotFoundException ae)
         {
             System.out.println(" ");
         }
           
        do
         {
           
           
               
                for(int i=0;i<n;i++)
                {
                   
                  if(Acc[i].accountnumber==accno)
                  {
                         System.out.println("\n 1.Deposit \n 2.Withdraw \n 3.Get Balance \n 4.Get Details \n 5.Exit");
                         System.out.println("Enter Your Choice :");
                         choice=sc.nextInt();
                         System.out.println("\n");
                         switch(choice)
                         {
                          case 1:
                           {
                             Acc[i].Deposit();
                             break;
                           }
                           case 2:
                           {
                            try
                            {
                               Acc[i].Withdraw();
                            }
                            catch(MinimumBalanceRequiredException me)
                            {
                                System.out.println("Error Occured : "+me.message);
                            }
                            catch(NotEnoughMoneyInAccountException ne)
                            {
                                System.out.println("Error Occured : "+ne.message);
                            }
                            break;
                            }
                            case 3:
                            {
                                System.out.println("Balance :"+Acc[i].getBalance());
                             break;
                            }
                            case 4:
                            {
                                Acc[i].display();
                                break;
                            }
                         }
                         
                         
                           
                    }
                }
            }while(choice!=5);
        }
       
            System.out.println("Do You Want to Proceed(Y/N)");
            ch=sc.next().charAt(0);
        }while(ch!='N');
    }
}
