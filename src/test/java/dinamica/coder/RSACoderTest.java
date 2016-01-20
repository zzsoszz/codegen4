package dinamica.coder;

import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class RSACoderTest {
	private String publicKey;
	private String privateKey;

	@Before
	public void setUp() throws Exception {
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥： \n\r" + privateKey);
	}

	@Test
	public void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String inputStr = "abc";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,privateKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

	}

	@Test
	public void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String inputStr = "sign";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
		
		byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = RSACoder.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = RSACoder.verify(encodedData, publicKey, sign);
		System.err.println("状态:\r" + status);
		assertTrue(status);

	}
	
	
	public  static String encode(String data) throws Exception
	{
		String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVLDBAKA1/a0IrYXzAhMqmf4aFk2slSosVpJzzXHtjSj3adkuwLk5R+9RTkcNQmhuc1EQ98hJib31Zxy+Mc835MKHztgqJv9mer2bkkOen2kTmBN88P2t/La2QB/wyMrso+H06XcULgTnQFtBH7WTNVWaasA1PEoQdcajT6DgMywIDAQAB";
		byte[] decodedData = RSACoder.encryptByPublicKey(data.getBytes(), publickey);
		return  new Base64().encodeAsString(decodedData);
	}
	public  static String decode(String data) throws Exception
	{
		String privatekey="MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBANUsMEAoDX9rQithfMCEyqZ/hoWTayVKixWknPNce2NKPdp2S7AuTlH71FORw1CaG5zURD3yEmJvfVnHL4xzzfkwofO2Com/2Z6vZuSQ56faROYE3zw/a38trZAH/DIyuyj4fTpdxQuBOdAW0EftZM1VZpqwDU8ShB1xqNPoOAzLAgMBAAECgYEApygVVdmSa/3xTw8MwfHe7pxFIe0tMXq4gWopYHN+lacbxXjJdQd6hrMQFc/Q9h1B1cJlGKX/YIzIIGQeluYf9f5aUcsxATaC01JZ6k0hf9YpJmZGQeCkgM6mixdfMUhdOJZwzbbpAT8vwZKYvZ6q4mgW0dzUUonOiU2Teo7AF6ECQQDylyfvJ1uzc9YRnGtikgDilcoF5D9tQFFPGtw5dVMQRdilPZ1eJU2/2I2rKPSLfShgONOxqvS3f2ZB11j8H0WDAkEA4PS/QX0L/0k6/vvqp5h4LEEGa0Fdm3hBgsHMg/ZOCQe0rKtxPgntKJT5R7mySw6f1PZVU8bA54L9zd4sIixBGQJBANOuqI8cNf2ST7LQXjgGFTWi5UVc1SfdHtY7Jv4QbaneeNw/tqknPf/w1DYNp85sKSZFkBwRhDb2uWEsq55dH0cCQQDEJoTyHJAufHM1zQGyO29l3xkywp9Vpgkxc7zZwBYRJ6fx+9fVaaBih2vJj/EYI1tYvaZvU1n3RK/8J2LCNWZZAkEAntdqpTa80QR5qqQMF9sKURF7gH8cn1XrorTDQ6GcMmRTOyXCR8xokOSMTXeypwHkEMozq21az0i0GflnkZTP4w==";
		byte[] decodedData = RSACoder.decryptByPrivateKey(new Base64().decodeBase64(data.getBytes()), privatekey);
		String outputStr = new String(decodedData);
		return outputStr;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(RSACoderTest.encode("hello"));;
		System.out.println(RSACoderTest.decode(RSACoderTest.encode("hello")));;
//		String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVLDBAKA1/a0IrYXzAhMqmf4aFk2slSosVpJzzXHtjSj3adkuwLk5R+9RTkcNQmhuc1EQ98hJib31Zxy+Mc835MKHztgqJv9mer2bkkOen2kTmBN88P2t/La2QB/wyMrso+H06XcULgTnQFtBH7WTNVWaasA1PEoQdcajT6DgMywIDAQAB";
//		String privatekey="MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBANUsMEAoDX9rQithfMCEyqZ/hoWTayVKixWknPNce2NKPdp2S7AuTlH71FORw1CaG5zURD3yEmJvfVnHL4xzzfkwofO2Com/2Z6vZuSQ56faROYE3zw/a38trZAH/DIyuyj4fTpdxQuBOdAW0EftZM1VZpqwDU8ShB1xqNPoOAzLAgMBAAECgYEApygVVdmSa/3xTw8MwfHe7pxFIe0tMXq4gWopYHN+lacbxXjJdQd6hrMQFc/Q9h1B1cJlGKX/YIzIIGQeluYf9f5aUcsxATaC01JZ6k0hf9YpJmZGQeCkgM6mixdfMUhdOJZwzbbpAT8vwZKYvZ6q4mgW0dzUUonOiU2Teo7AF6ECQQDylyfvJ1uzc9YRnGtikgDilcoF5D9tQFFPGtw5dVMQRdilPZ1eJU2/2I2rKPSLfShgONOxqvS3f2ZB11j8H0WDAkEA4PS/QX0L/0k6/vvqp5h4LEEGa0Fdm3hBgsHMg/ZOCQe0rKtxPgntKJT5R7mySw6f1PZVU8bA54L9zd4sIixBGQJBANOuqI8cNf2ST7LQXjgGFTWi5UVc1SfdHtY7Jv4QbaneeNw/tqknPf/w1DYNp85sKSZFkBwRhDb2uWEsq55dH0cCQQDEJoTyHJAufHM1zQGyO29l3xkywp9Vpgkxc7zZwBYRJ6fx+9fVaaBih2vJj/EYI1tYvaZvU1n3RK/8J2LCNWZZAkEAntdqpTa80QR5qqQMF9sKURF7gH8cn1XrorTDQ6GcMmRTOyXCR8xokOSMTXeypwHkEMozq21az0i0GflnkZTP4w==";
//		String inputStr = "sign";
//		byte[] data = inputStr.getBytes();
//		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privatekey);
//		byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publickey);
//		String outputStr = new String(decodedData);
//		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		
		
	}
}

//
//公钥: 
//MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVLDBAKA1/a0IrYXzAhMqmf4aFk2slSosVpJzz
//XHtjSj3adkuwLk5R+9RTkcNQmhuc1EQ98hJib31Zxy+Mc835MKHztgqJv9mer2bkkOen2kTmBN88
//P2t/La2QB/wyMrso+H06XcULgTnQFtBH7WTNVWaasA1PEoQdcajT6DgMywIDAQAB
//
//私钥： 
//MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBANUsMEAoDX9rQithfMCEyqZ/hoWT
//ayVKixWknPNce2NKPdp2S7AuTlH71FORw1CaG5zURD3yEmJvfVnHL4xzzfkwofO2Com/2Z6vZuSQ
//56faROYE3zw/a38trZAH/DIyuyj4fTpdxQuBOdAW0EftZM1VZpqwDU8ShB1xqNPoOAzLAgMBAAEC
//gYEApygVVdmSa/3xTw8MwfHe7pxFIe0tMXq4gWopYHN+lacbxXjJdQd6hrMQFc/Q9h1B1cJlGKX/
//YIzIIGQeluYf9f5aUcsxATaC01JZ6k0hf9YpJmZGQeCkgM6mixdfMUhdOJZwzbbpAT8vwZKYvZ6q
//4mgW0dzUUonOiU2Teo7AF6ECQQDylyfvJ1uzc9YRnGtikgDilcoF5D9tQFFPGtw5dVMQRdilPZ1e
//JU2/2I2rKPSLfShgONOxqvS3f2ZB11j8H0WDAkEA4PS/QX0L/0k6/vvqp5h4LEEGa0Fdm3hBgsHM
//g/ZOCQe0rKtxPgntKJT5R7mySw6f1PZVU8bA54L9zd4sIixBGQJBANOuqI8cNf2ST7LQXjgGFTWi
//5UVc1SfdHtY7Jv4QbaneeNw/tqknPf/w1DYNp85sKSZFkBwRhDb2uWEsq55dH0cCQQDEJoTyHJAu
//fHM1zQGyO29l3xkywp9Vpgkxc7zZwBYRJ6fx+9fVaaBih2vJj/EYI1tYvaZvU1n3RK/8J2LCNWZZ
//AkEAntdqpTa80QR5qqQMF9sKURF7gH8cn1XrorTDQ6GcMmRTOyXCR8xokOSMTXeypwHkEMozq21a
//z0i0GflnkZTP4w==
//
//私钥加密——公钥解密
//加密前: sign
//解密后: sign
//私钥签名——公钥验证签名
//RvLLYuVJJgmyZTMg8vbwRAAQnq157+nb4rpZl/Z5eR8zchjSTurxeunKp2c72izpODAxtHS82ksQ
//A99csAy+eMPY27kEX1PxX1PM4Duxi13kwZ8glUp2eXquwjsPS+PDkWu/qCK4jqiLcn0x+GDfYF5N
//N+mkNoKsI4IkcXRBtb8=
//
//true
//公钥: 
//MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCg+tRF98RJsw0BeD8FV+jHD4IWAs+z8D1rhhjT
//JLci/CgPh3PZNMqBLn7fAwqxsiKJMFr/WzqGL4Zfc/nwT2m93diTJHgHUYqKJlQDK7EfWUSo2Y8s
//bTaX9dK3PV/drthYSTHHzK3JBYYtTA0tE1sBfTFbK72hhTKWchqlRscW2wIDAQAB
//
//私钥： 
//MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKD61EX3xEmzDQF4PwVX6McPghYC
//z7PwPWuGGNMktyL8KA+Hc9k0yoEuft8DCrGyIokwWv9bOoYvhl9z+fBPab3d2JMkeAdRioomVAMr
//sR9ZRKjZjyxtNpf10rc9X92u2FhJMcfMrckFhi1MDS0TWwF9MVsrvaGFMpZyGqVGxxbbAgMBAAEC
//gYAn9ZtzsskaskNsNfkOzn+Nyh+0bwY32E6mviazYs3C9WzMTgdHx37tCimcGhkxetJv030DHhYs
//8OceEp/cMxpADgnorKW7xZ/fsTkFmxDpTQd+VK1py9EcMzk2fHRTlcBE+cwd/kPCM1SHmhawoipq
//kPIj78a6L/0KB28llBuiAQJBANzKektLiz6RUcjQDHQW4IMUtZ/4OoUAIbdB9j+JM9ZI/F2Ukjd8
//GLuHGf+R9Xi+Xko5+IETXxr6GjPN7ZtkycECQQC6pqJ4Y238AQE1J/Sje7YGdEGW8SuLw0VksDMm
//FFl+nOmUGjkB36nBzU1bPMuTY1ht/43xb+C+VAYWP2uEVq+bAkEAqGrO5HaIjb592ydQCJGWyoZW
//kOLhiKh0D30CYK7A2tkroBp/elFv8EP7AecNjv/vnsEfnR3wsvxKVm/jqmA0QQJAFu9k5XAqUp34
///QQk3nXUHyTn7llCJZFgFIhGMLatbI5xcT5rpNxL6sGEcOKejUTW1+VgFrvNnPWMLe2rE37nYwJA
//OAbFyAOoK8HEofI3mY+9jS9TNcdJKgbIuKn2auMEVMdqs2QAf/bf6NbOELjQLyyxgPz/7NBwQcGW
//AGZusxSsyQ==
//
//公钥加密——私钥解密
//加密前: abc
//解密后: abc
//
//
