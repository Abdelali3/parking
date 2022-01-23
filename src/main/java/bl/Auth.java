package bl;

import dal.UserDal;

import entities.User;

public class Auth {
	
	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
	
	public static User login(String email, String password) {
		UserDal ud = UserDal.getInstance();
        User u = ud.findByEmail(email);
        System.out.println(u.toString());
        if (u != null) {
            if (u.getPassword().equals(MD5(password))) {
                return u;
            }
        }
        return null; 
	}
	
	public static boolean register(String firstname, String lastname, String email, String password) {
		UserDal ud = UserDal.getInstance();
		return ud.create(new User(lastname, firstname, MD5(password), email, "none"));
	}
}
