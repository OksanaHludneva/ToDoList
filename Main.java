public class Main {

    public static void main(String[] args) {

        MenuController menuController = new MenuController();
        menuController.start();

        Database database = new Database();
        database.initialize();
    }
}
