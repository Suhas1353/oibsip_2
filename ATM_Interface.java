import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction{
    String type;
    double amount;

    Transaction(String t, double a){
        this.type=t;
        this.amount=a;
    }

    String getType(){
        return type;
    }

    double getAmount(){
        return amount;
    }
}

class userAccount{
    String userId;
    int userPin;
    double balance;
    List<Transaction> TransactionHistory;
    userAccount(String userId,int userPin,double balance){
        this.userId=userId;
        this.userPin=userPin;
        this.balance=balance;
        this.TransactionHistory=new ArrayList<Transaction>();
    }

    String getUserId(){
        return userId;
    }

    int getUserPin(){
        return userPin;
    }

    double getBalance(){
        return balance;
    }

    public void displayTransactionHistory(){
        for(Transaction transaction : TransactionHistory){
            System.out.println("Transaction Type: "+transaction.getType()+", Amount: "+transaction.getAmount());
        }
        System.out.println("----------------");
    }

    public void Withdraw(double amount){
        if(amount<=balance){
            balance-=amount;
            String Trans_type="Withdraw";
            double Trans_amount=amount;
            Transaction transaction = new Transaction(Trans_type, Trans_amount);
            TransactionHistory.add(transaction);
            System.out.println("Withdrawl Successfull");
            System.out.println("Your account Balance : "+balance);
        }
        else{
            System.out.println("Insufficient Balance");
        }
    }

    public void Deposit(double amount){
        balance+=amount;
        String Trans_type="Deposit";
        double Trans_amount=amount;
        Transaction transaction=new Transaction(Trans_type, Trans_amount);
        TransactionHistory.add(transaction);
        System.out.println("Deposited amount successfully");
        System.out.println("Your account Balance : "+balance);
    }

    public void Transfer(double amount){
        if(balance>=amount){
            balance-=amount;
            String Trans_type="Transferred";
            double Trans_amount=amount;
            Transaction transaction = new Transaction(Trans_type, Trans_amount);
            TransactionHistory.add(transaction);
            System.out.println("Transferred amount to the Receiver Successfully");
            System.out.println("Your account Balance : "+balance);
        }
        else{
            System.out.println("Insufficient balance to Transfer");
        }
    }


}

class bankAccounts{
    List<userAccount> userAccounts = new ArrayList<userAccount>();
    int q=0;

    public void register(String userId,int userPin,double balance){
        userAccount account = new userAccount(userId, userPin, balance);
        userAccounts.add(account);
    }

    public int getUserPin(String ui){
        int pin=0;
        for(userAccount account : userAccounts){
            if(account.getUserId().equals(ui)){
                pin=account.getUserPin();
                break;
            }
        }
        return pin;
    }

    public userAccount getUserAccount(String userId){
        userAccount acc=null;
        for(userAccount ac : userAccounts){
            if(ac.getUserId().equals(userId)){
                acc=ac;
                break;
            }
        }
        return acc;
    }

    public void login(String userid,int pin){
        int p=getUserPin(userid);
        if(p==pin){
            System.out.println("Login Successfull");
            userAccount acc=getUserAccount(userid);
            ATM_Menu(acc);
        }
        else{
            System.out.println("Login Invalid");
        }
    }

    

    public void ATM_Menu(userAccount account){
         Scanner sc = new Scanner(System.in);
            int c=0;
            while(c != 5){
                System.out.println("1. Transactions Hsistory");
                System.out.println("2. Withdraw Amount");
                System.out.println("3. Deposit Amount");
                System.out.println("4. Transfer to another Account");
                System.out.println("5. Quit");
                System.out.println("Enter your Choice");
                c=sc.nextInt();
                switch(c){
                    case 1:
                        account.displayTransactionHistory();
                        break;
                    case 2:
                        System.out.println("Enter the amount to be withdrawn");
                        double am=sc.nextDouble();
                        account.Withdraw(am);
                        break;
                    case 3:
                        System.out.println("Enter the amount to be deposited");
                        double amt=sc.nextDouble();
                        account.Deposit(amt);
                        break;
                    case 4:
                        System.out.println("Enter the amount to be Transferred");
                        double amn=sc.nextDouble();
                        System.out.println("Enter the reciever bank account number");
                        long num=sc.nextInt();
                        account.Transfer(amn);
                        break;
                    case 5:
                        System.out.println("THANK YOU");
                        System.out.println("Quitting");
                        System.out.println("---------------------------------");
                        break;
                    default:
                        System.out.println("Invalid Choice. Choose the correct one");

                }

            }
    }

}

public class ATM_Interface {
    public static void main(String args[]){
        Scanner sc =  new Scanner(System.in);
        System.out.println("Welcome to ATM");
        System.out.println("Register if you are new person else Login with your userId and userPin\n");
        System.out.println("Type 1 for Registration of your New Account\nType 2 for Login with your Exixting account\nType 3 for Qutting ATM");
        System.out.println("\nEnter your choice");
        bankAccounts ba=new bankAccounts();
        int choice;
        choice=sc.nextInt();
        sc.nextLine();
        while(choice<3){
            System.out.println("-----------------------------------------------");
            if(choice==1){
                System.out.println("Thank you for joining our Bank");
                System.out.println("Please provide the following details\n");
                System.out.println("Enter the userId for your account");
                String userId=sc.nextLine();
                System.out.println("Enter the private and unique four-digit pin");
                int userPin=sc.nextInt();
                System.out.println("Enter the initial deposit in your account in Rs.");
                double balance=sc.nextDouble();

                ba.register(userId,userPin,balance);
                System.out.println("Congratulations! you have registered Successfully");

                System.out.println("You have been redirected to your account menu page");
        
                ba.login(userId,userPin);
            
            }
            else if(choice ==2 ){
                System.out.println("Welcome Back!\n");
                System.out.println("Enter the userId of your account");
                String userId=sc.nextLine();
                System.out.println("Enter the userPin of your account");
                int userPin=sc.nextInt();
                sc.nextLine();
                ba.login(userId,userPin);
            }
            else if(choice == 3){
                break;
            
            }
            else{
                System.out.println("Invalid Entry. Please try again");
            }
            System.out.println("Enter your choice");
            System.out.println("Type 1 for Registration of your New Account\nType 2 for Login with Exixting Account\nType 3 for Quitting ATM");
            choice=sc.nextInt();
            sc.nextLine();
        }
    } 
}
