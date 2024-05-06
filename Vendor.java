package javafx;

public class Vendor extends User{
    private String name;
    private String email;
    private int id;
    private static int vendorsCount = 0;

    
    public Vendor(String name, String email) {
        super(name,email);
    	this.name = name;
    	this.email = email;
    	vendorsCount++;
        this.id = vendorsCount;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }


    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", ID: " + id;
    }
    
}
