package mainmodule.LoginMenu;

import java.io.IOException;
import java.util.HashMap;

public class DataBase {
    private static DataBase dataBase;
    HashMap<String, String> usernameAndPasswords = new HashMap<>();

    public static DataBase getInstanse() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public String checkForError(String username, String password) {
        if (usernameAndPasswords.containsKey(username) && usernameAndPasswords.get(username).equals(password)) {
            return "ok";
        } else {
            return "error";
        }
    }

    public void serialize() throws IOException {
//        File file = new File("users.json");
//        if(file == null){
//            System.out.println("hello");
//            file.createNewFile();
//        }
//        HashMap<String,String> jsonString = new Gson().fromJson(String.valueOf(Files.readAllBytes(Paths.get(file.getPath()))),new TypeToken<HashMap<String,String>>(){}.getType());
//        usernameAndPasswords.putAll(jsonString);
    }

    public void addUser(String username, String password) {
        usernameAndPasswords.put(username, password);
    }
}
