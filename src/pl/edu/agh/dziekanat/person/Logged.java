package pl.edu.agh.dziekanat.person;

public class Logged {

    private Person logged;

    private static Logged instance = null;

    protected Logged() {

    }

    /**
     *
     * @return Logged
     */
    public static Logged getInstance() {
        if (instance == null) {
            instance = new Logged();
        }
        return instance;
    }

    public Person getLogged() {
        return logged;
    }

    public void setLogged(Person logged) {
        this.logged = logged;
    }

    public boolean isValid() {
        return this.logged != null;
    }

}
