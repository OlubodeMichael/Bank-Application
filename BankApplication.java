import java.util.Scanner;
import java.util.ArrayList;

public class BankApplication {
    double checkingsBalance = 1000;
    double savingsBalance = 1000;
    ArrayList<Double> checkingsTransaction = new ArrayList<Double>();
    ArrayList<Double> savingsTransaction = new ArrayList<Double>();
    Scanner input = new Scanner(System.in);

    public BankApplication() {
        System.out.println("System is working.");
    }

    public int getAccountType() {
        System.out.println("1: Checking account.");
        System.out.println("2: Savings account.");
        System.out.println("3: Other options");
        System.out.println("Enter your option: ");
        return input.nextInt();
    }

    public void run() {
        int accountType;
        char continueOperation;

        do {
            accountType = getAccountType();

            switch (accountType) {
                case 1:
                    checkingAccount();
                    break;
                case 2:
                    savingsAccount();
                    break;
                case 3:
                    otherOption();
                    break;
                default:
                    System.out.println("Invalid option");
            }

            askToContinue(); // Ask the user if they want to continue
        } while (true); // Continue until the user decides to exit
    }
    public void checkingAccount() {
        System.out.println("Checking account working");
        System.out.println("1: Deposit");
        System.out.println("2: Withdraw");
        System.out.println("3: View Bank statement");
        System.out.println("Enter your choice: ");
        int choice = input.nextInt();
        if (choice == 1) {
            deposit(checkingsTransaction);
        } else if (choice == 2) {
            withdraw(checkingsTransaction, checkingsBalance);
        } else if (choice == 3) {
            bankStatement(checkingsTransaction);
        }
    }

    public void savingsAccount() {
        System.out.println("Savings account working");
        System.out.println("1: Deposit");
        System.out.println("2: Withdraw");
        System.out.println("3: View Bank statement");
        System.out.println("Enter your choice: ");
        int choice = input.nextInt();
        if (choice == 1) {
            deposit(savingsTransaction);
        } else if (choice == 2) {
            withdraw(savingsTransaction, savingsBalance);
        } else if (choice == 3) {
            bankStatement(savingsTransaction);
        }
    }

    public void otherOption() {
        System.out.println("1: Transfer");
        System.out.println("2: Pay bills");
        System.out.println("Enter your choice: ");
        int choice = input.nextInt();
        if (choice == 1) {
            transfer();
        } else if (choice == 2) {
            payBills();
        } else {
            System.out.println("Invalid option");
            //invalid option
        }
    }

    public void deposit(ArrayList<Double> transaction) {
        System.out.println("Deposit system working");
        System.out.println("How much do you want to deposit: ");
        double depositedAmount = input.nextDouble();
        transaction.add(depositedAmount);
        System.out.println("Total account balance is: " + getTotalBalance(transaction));
        addTransaction(transaction, depositedAmount);
    }

    public void withdraw(ArrayList<Double> transaction, double balance) {
        System.out.println("Withdraw system working");
        System.out.println("How much do you want to withdraw: ");
        double withdrawAmount = input.nextDouble();
        if (withdrawAmount > getTotalBalance(transaction)) {
            System.out.println("Insufficient Balance");
        } else {
            transaction.add(-withdrawAmount);
            System.out.println("Total account balance is: " + getTotalBalance(transaction));
            addTransaction(transaction, withdrawAmount);
        }
    }

    public void bankStatement(ArrayList<Double> transaction) {
        if (!transaction.isEmpty()) {
            for (Double amount : transaction) {
                System.out.println(amount);
            }
        } else {
            System.out.println("No Transactions");
        }
    }

    public void transfer() {
        System.out.println("Transfer system working");
        System.out.println("Transfer from:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int sourceAccount = input.nextInt();
        System.out.println("Enter the amount to transfer: ");
        double amountToTransfer = input.nextDouble();

        if (sourceAccount == 1) {
            if (amountToTransfer > checkingsBalance) {
                System.out.println("Insufficient Balance");
            } else {
                checkingsTransaction.add(-amountToTransfer);
                savingsTransaction.add(amountToTransfer);
                System.out.println("Transfer successful");
            }
        } else if (sourceAccount == 2) {
            if (amountToTransfer > savingsBalance) {
                System.out.println("Insufficient Balance");
            } else {
                savingsTransaction.add(-amountToTransfer);
                checkingsTransaction.add(amountToTransfer);
                System.out.println("Transfer successful");
            }
        } else {
            System.out.println("Invalid source account");
        }
    }

    public void payBills() {
        System.out.println("Pay bill system working");
        String[] bills = {"Phone bill", "Netflix subscription", "Light and Water bill", "Insurance", "Car note", "Mortgage"};
        double[] billsPrice = {109.34, 14.90, 605.78, 180.00, 350.90, 2500.90};

        for (int i = 0; i < bills.length; i++) {
            System.out.println(i + 1 + ". " + bills[i] + ": $" + billsPrice[i]);
        }

        System.out.println("Enter the bill number you want to pay: ");
        int choice = input.nextInt();
        if (choice >= 1 && choice <= bills.length) {
            double billAmount = billsPrice[choice - 1];
            if (billAmount > savingsBalance) {
                System.out.println("Insufficient Balance");
            } else {
                savingsTransaction.add(-billAmount);
                System.out.println(bills[choice - 1] + " payment confirmed.");
                System.out.println("Amount paid: $" + billAmount);
                System.out.println("Account balance: $" + savingsBalance);
            }
        } else {
            System.out.println("Invalid bill number");
        }
    }

    public double getTotalBalance(ArrayList<Double> transaction) {
        double balance = 0;
        for (Double amount : transaction) {
            balance += amount;
        }
        return balance;
    }



    public void askToContinue() {
        char response;
        do {
            System.out.print("Do you want to continue (Y/N)? ");
            response = input.next().charAt(0);
            response = Character.toUpperCase(response); // Convert to uppercase for case-insensitive comparison
        } while (response != 'Y' && response != 'N');

        if (response == 'N') {
            System.out.println("Exiting the program.");
            System.exit(0);
        }
    }


    public void addTransaction(ArrayList<Double> transaction, double amount) {
        transaction.add(amount);
    }
}

