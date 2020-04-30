import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class BankManagerAccount {
    private String userid;
    private byte[] salt;
    private byte[] password;

    public BankManagerAccount(String userid, String Password){
        this.userid  = userid;
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        try {
            salt = passwordEncryptionService.generateSalt();
            password = passwordEncryptionService.getEncryptedPassword(Password,salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
    }

    public String getUserid() {
        return userid;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getPassword() {
        return password;
    }
}
