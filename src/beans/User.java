package beans;

public class User
{
    private String ID;
    private String userType;

    public User(String ID, String userType)
    {
        this.ID = ID;
        this.userType = userType;
    }

    public String getID()
    {
        return ID;
    }

    public String getUserType()
    {
        return userType;
    }
}
