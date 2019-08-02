package br.com.sme.teste.mock;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;


public class Main {
	private static String ipPorta = "http://www.sistemafocos.com.br:8480";

	private static String BASE_URL = "/br.com.vixti.focos.webinterface.teste/testManager";

	public static void main(String[] args) {
		
		try {
			String retorno = logar();
			
			System.out.println(retorno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static String logar() throws Exception {
		String usuario = "333";
		String senha = "12345678";

		String login = "l=" + usuario + "&" + "p=" + senha + "&mac=E0-69-95-FD-DB-18&tipo_teste=curto&dispositivo_teste=mobile";
		String loginZipped = StringConverter.string2zippedHex(login);
		String metodo = "idioma=pt_BR&m=login&data=" + loginZipped;

		//String testelalala = "[blockId=415;subBlockId=1112;type=1;responseTime=546;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=553;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=593;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=304;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=268;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=342;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=329;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=327;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=328;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=260;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=257;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=234;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=283;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=328;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=314;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=235;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=215;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=248;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=188;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=197;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=276;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=567;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=642;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=799;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=522;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=291;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=260;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=298;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=407;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=386;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=411;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=403;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=294;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=266;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=621;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=445;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=345;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=346;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=349;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=334;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=394;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[1624:0;1625:0;1626:0;1627:0;1628:0;1629:0;1630:0;1631:0;1632:0;1633:0;]";

		
		return consumeRest(metodo);
		
	}

	private static String consumeRest(String body) {
		try {
			String urlStr = ipPorta + BASE_URL;
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			OutputStream os = conn.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			
			
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			
			StringBuilder resposta = new StringBuilder();
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				
				resposta.append(output);
			}
			conn.disconnect();
		
			return resposta.toString();
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
			
			return null;
		}
	}

}
