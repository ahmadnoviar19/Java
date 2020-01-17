package telepati;  
 
import java.io.*;  
import java.math.BigInteger;
import java.util.*;
import java.security.*;  
import java.security.spec.InvalidKeySpecException;  
import java.security.spec.RSAPublicKeySpec;  
import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;  
	 
public class Enkripsi {  
	 
	public static void main(String[] args) throws IOException {
	 
	System.out.print("Plaintext = ");
	Scanner scan = new Scanner(System.in);
	String Plaintext = scan.nextLine();
	System.out.println(Plaintext);
	scan.close();
	Enkripsi rsaObj = new Enkripsi();  
	 
	//mengenkripsi data menggunakan public key    
	byte[] encryptedData = rsaObj.encryptData(Plaintext); 
	 
	}  
	
	private byte[] encryptData(String data) throws IOException {  
	System.out.println("\n----------------MEMULAI ENKRIPSI------------");  
	System.out.println("Data Before Encryption :" + data);  
	byte[] dataToEncrypt = data.getBytes();
	byte[] encryptedData=null;
	try {  
	PublicKey pubKey = readPublicKeyFromFile("C:/Users/ahmad/Belajar Java/Eclipse Workspace/KP/Public.key");  
	Cipher cipher = Cipher.getInstance("RSA");  
	cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
	encryptedData = cipher.doFinal(dataToEncrypt);  
	System.out.println("Encrypted Byte :> " + encryptedData);
	String hex = bytesToHexString(encryptedData);              
	System.out.println("Encrypted Hexa :> " + hex);         
	} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {  
	e.printStackTrace();  
	}  
	System.out.println("----------------ENKRIPSI SELESAI------------");  
	return encryptedData;  
	}  
	
	public PublicKey readPublicKeyFromFile(String fileName) throws IOException {  
	FileInputStream fis = null;  
	ObjectInputStream ois = null;  
	try {  
	fis = new FileInputStream(new File(fileName));  
	ois = new ObjectInputStream(fis);  
	BigInteger modulus = (BigInteger) ois.readObject();  
	BigInteger exponent = (BigInteger) ois.readObject();  
	//mengambil public key    
	RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);  
	KeyFactory fact = KeyFactory.getInstance("RSA");  
	PublicKey publicKey = fact.generatePublic(rsaPublicKeySpec);  
	return publicKey;  
	} catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException e) {  
	e.printStackTrace();  
	} finally {  
	if (ois != null) {  
	ois.close();  
	if (fis != null) {  
	fis.close();  
	}  
	}  
	}  
	return null;  
	}
	 
  private static String bytesToHexString(byte[] bytes) {
   	StringBuilder sb = new StringBuilder(bytes.length * 2);
   	Formatter formatter = new Formatter(sb);
   	for (byte b : bytes) {
   		formatter.format("%02x",b);
   	}
   	formatter.close();
   	return sb.toString();
   }
}
