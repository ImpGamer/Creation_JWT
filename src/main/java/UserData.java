public class UserData {
    private String user;
    private String email;
    //Fecha de creacion del token en formato UNIX
    private long iat;
    private long expireAt;

    public UserData(String user, String email, long iat, long expireAt) {
        this.user = user;
        this.email = email;
        this.iat = iat;
        this.expireAt = expireAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(long expireAt) {
        this.expireAt = expireAt;
    }
}
