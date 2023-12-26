import java.security.SecureRandom;
import java.util.Scanner;
class Password{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user-defined criteria
        int passwordLength = getPasswordLength(scanner);
        boolean includeUppercase = getBooleanInput(scanner, "Include Uppercase? (y/n): ");
        boolean includeLowercase = getBooleanInput(scanner, "Include Lowercase? (y/n): ");
        boolean includeNumbers = getBooleanInput(scanner, "Include Numbers? (y/n): ");
        boolean includeSpecialChars = getBooleanInput(scanner, "Include Special Characters? (y/n): ");

        // Generate and display the password
        String generatedPassword = generatePassword(passwordLength, includeUppercase, includeLowercase, includeNumbers, includeSpecialChars);
        System.out.println("Generated Password: " + generatedPassword);

        scanner.close();
    }

    private static int getPasswordLength(Scanner scanner) {
        int length = 0;
        while (length <= 0) {
            try {
                System.out.print("Enter password length: ");
                length = Integer.parseInt(scanner.nextLine());
                if (length <= 0) {
                    System.out.println("Password length must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return length;
    }

    private static boolean getBooleanInput(Scanner scanner, String message) {
        System.out.print(message);
        String input = scanner.nextLine().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    private static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
                                           boolean includeNumbers, boolean includeSpecialChars) {
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

        String allChars = "";
        if (includeUppercase) allChars += uppercaseChars;
        if (includeLowercase) allChars += lowercaseChars;
        if (includeNumbers) allChars += numberChars;
        if (includeSpecialChars) allChars += specialChars;

        if (allChars.isEmpty()) {
            System.out.println("Error: At least one character set must be selected.");
            System.exit(1);
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }

        return password.toString();
    }
}
