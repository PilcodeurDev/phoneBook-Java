package io.pilcodeurdev.exowithjiraws;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PhoneBook {

   public static Scanner userInput = null;
   public static final String PHONE_BOOK_FILE_PATH = "/Users/despressimon/Desktop/test/phoneBook.txt";

   public static void main(String[] args) {

      userInput = new Scanner(System.in);

      String userFirstName = getUserInput("Entrer votre prénom: ");
      String userLastName = getUserInput("Entrer votre nom de famille: ");
      String userPhoneNumber = getUserInput("Entrer votre numéro de téléphone: ");

      Contact newContact = new Contact(userFirstName, userLastName, userPhoneNumber);

      File phoneBookFile = getOrCreatePhoneBookFile(PHONE_BOOK_FILE_PATH);

      appendContactToPhoneBook(newContact, phoneBookFile);

      userInput.close();
   }

   public static String getUserInput(String userRequest) {

      if (userRequest == null || userRequest.isEmpty() || userInput == null)
         return "";

      System.out.println(userRequest);
      return userInput.nextLine();

   }

   public static File getOrCreatePhoneBookFile(String phoneBookFilePath) {
      File phoneBookFile = new File(phoneBookFilePath);
      if (phoneBookFile.exists()) {
         return phoneBookFile;
      }

      try {
         System.out.println("Le fichier n'existe pas.");
         phoneBookFile.createNewFile();
         System.out.println("Création du fichier a l'emplacement suivant : " + phoneBookFilePath);
         System.out.println("Le fichier a été créé");
         return phoneBookFile;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   public static void appendContactToPhoneBook(Contact newContact, File phoneBookFile) {
      try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(phoneBookFile, true))) {
         fileWriter.append(newContact.toString() + System.getProperty("line.separator"));

         System.out.println("Nouveau contact a été ajouté : " + newContact.toString());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
