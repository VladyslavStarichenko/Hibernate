package ua.com.alevel.helpers;


import ua.com.alevel.dao.UserDao;
import ua.com.alevel.model.User;

import java.util.Scanner;

public class HelperUser {

    private static final String messageToCreateName = "Enter the User's name to create";
    private static final String messageToCreateSurname = "Enter the User's surname to create";
    private static final String messageToCreateMail = "Enter the User's mail to create";
    private static final String messageToCreateAddress = "Enter the User's address to create";
    private static final String messageToCreatePostCode = "Enter the User's post code to create";
    private static final String messageToGetIdUpdate = "Enter User's id to update";
    private static final String messageToUpdateName = "Enter the User's name to update";
    private static final String messageToUpdateSurname = "Enter the User's surname to update";
    private static final String messageToUpdateMail = "Enter the User's mail to update";
    private static final String messageToUpdateAddress = "Enter the User's address to update";
    private static final String messageToUpdatePostCode = "Enter the User's post code to update";
    private static final String messageToDeleteId = "Enter the User's id to delete";
    private static final String messageCantCreate = "User with same mail is already Exists";

    public static String suggestUserMethod() {
        System.out.println("Here you can Create, Read, Update and Delete Users or press Back to return to the menu");
        System.out.println("Enter the method you wanna do.");
        Scanner scanner = new Scanner(System.in);
        String method = scanner.nextLine();
        return method;
    }

    public static void methodsUser(String method){
        if(method.equals("Read")){
            UserDao.showUser();
            methodsUser(suggestUserMethod());
        }else if(method.equals("Update")){
            Integer userIdTOUpdate = getUserInteger(messageToGetIdUpdate);
            String newName = getUserString(messageToUpdateName);
            String newSurname = getUserString(messageToUpdateSurname);
            String newAddress = getUserString(messageToUpdateAddress);
            String newMail = getUserString(messageToUpdateMail);
            Integer newPostCode = getUserInteger(messageToUpdatePostCode);
            UserDao.updateUser(userIdTOUpdate,newName,newSurname,newPostCode,newAddress,newMail);
            methodsUser(suggestUserMethod());
        }else if(method.equals("Create")){
            User user = createUserModel();
            UserDao.createUser(user.getEmail(),user,messageCantCreate);;
            methodsUser(suggestUserMethod());
        }else if(method.equals("Delete")){
            Integer userIdToDelete = getUserInteger(messageToDeleteId);
            UserDao.deleteUser(userIdToDelete);
            methodsUser(suggestUserMethod());
        }else if(method.equals("Back")){
            HelperApp.methodsApp(HelperApp.appSuggestion());
        }
    }






    public static User createUserModel(){
        String userName = getUserString(messageToCreateName);
        String userSurname = getUserString(messageToUpdateSurname);
        String userMail = getUserString(messageToCreateMail);
        Integer userPostCode = getUserInteger(messageToCreatePostCode);
        String userAddress = getUserString(messageToCreateAddress);
        User user = new User(userName,userSurname,userAddress,userPostCode,userMail);
        return user;
    }

    private static String getUserString(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str;
    }

    private static Integer getUserInteger(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        Integer integer = scanner.nextInt();
        return integer;
    }
}
