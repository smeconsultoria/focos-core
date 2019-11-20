package br.com.sme.teste.mock;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

public class Main {
	
	
	//private static String ipPorta = "http://52.86.48.96:8180"; // HOMOLOGACAO
	private static String ipPorta = "http://localhost:8080"; //LOCALHOST
	private static String usuario = "333";
	
	
	private static String senha = "12345678";
	private static String macAdress = "78-2B-CB-BC-71-39"; 
	private static String testeComQueixaDeSono = "504b03041400080808009d52034f0000000000000000000000000100000061ed943f0bc23010c5bf522efda36d08143767dd4a976a8662da04d30e7e7b1bcec94dd022e14d3f6e79bc7b7997b6b7ee723b5e754e850a4b7f788d4424d5fcf0460b7537c1bb2998f330c6711ebc3bb9514f8bb55dd3b41b28103c7cc9032187843ca051b80b2489ffe12fb628df14b28f3dfc4c819250409229bd451a7d40a3526a1492c4161b7b905465b5502bf29a220a9e4ac68eb167541152080631e48aee09504b07084ba750c1a2000000c8100000504b010214001400080808009d52034f4ba750c1a2000000c810000001000000000000000000000000000000000061504b050600000000010001002f000000d10000000000";
	private static String testeNormalComQueixaSono = "504b03041400080808000864034f0000000000000000000000000100000061dd56416ec23010fc92d7bbb663ac48a8b79ecb0d710172400d246ae0d0df13e45e9a9640baabb2ed6964291aed78c6b359aeeb66f3fabc2d095cea4eeba78f2300d8747c6fab12d25bd5b5cda1ab16bb7d553aa474dcb5cd4bb32f0fa7ba5ecde7cb9b1ce6330705cbe630d319864a88d81c3e160a9450f19029e41de9bf6373187eb61c207f0e14d0124043ba22fc8f7459d2e12a05159da1a2831d6998c248f44ee47b42c0e6c042221b4120e5633bc90f38f04af3109bc3593b9d6342c2ee6370464009c223947c99220a78e2157822701712ae066473f89f2463f8d60c0adc8755e08a1f6df35fcb86270125518192f16d702707a1c036f8a3ada1b2b9f89ebac8df041e04fe12be69610b116726f54033b880cb279f21642832c40b5863324006dbc3ea0c504b07088d3be5ab270100001e110000504b010214001400080808000864034f8d3be5ab270100001e11000001000000000000000000000000000000000061504b050600000000010001002f000000560100000000";
	private static String testeLimitrofeComQueixaSono = "504b03041400080808000365034f0000000000000000000000000100000061dd563d4fc3400cfd4b679f7d1f8d22556ccc74abba143254a44d44da817fdf46c7944240b24b0dd39397a7f7ec67dfadb76df7fcfaf8521370359cb60f1f250060757cef9bda556fcdd07787a159edf66379dcf5dd53b7af0fa7b6dd2c97eb6f19c000c3c405815c052516eb90abf0c1cb9db868c009b960a19facc091e53361480a3ae41ca4a083bc424fa342c23229ec1b5848694605271add70f7e8c6ff60b89a6936a122dd54459830f84f5584d92dfb19c7fc8e7cc12177a2de0b826c42459273201a70e273bc4fb6a61c499eaef93fd3af65c3b10515ec159cc853ce1effead5b8fa839ab83c24cfb80f269c20297068e850d81454780f38de602a08d92f5c75015ac0085caa5020164805f208e85c01288017d89c01504b07085dc980e8190100001a110000504b010214001400080808000365034f5dc980e8190100001a11000001000000000000000000000000000000000061504b050600000000010001002f000000480100000000";
	private static String testeAlteradoComQueixaSono = "504b03041400080808009965034f0000000000000000000000000100000061dd95bb4ec34010457f6967e7b1bbb12c4574d4d04569022e229c388a9382bf27d652592202662003d5d5344777deab4d3f3cbddc3fb704dc8ce7cddd7b0800b139bd1eba3634c76e3c0cfbb17bdceea6f0b43d0c0fc3aedd9ffb7ebd5cae7e8100ff914060c048060c44073d458e1e5c24d63372d2f78449ef438a9e411ef61d4336a8465667421615150f15a5081e5c24b9850b73c2b7666b5e0b01834beee286966b99c88c815f76f101615e8bc0b770619e07839e81827a46c97a464a06b9909ec106f520d0336231c845d4538a49f4fb866cb0b3e260e3900cba22f037af8fc30b88595cf4f4fa77fdec849387df180dee3067fddde19ff8d1110a2e4273115ac0245c23a992aae42a6592184215a8122fb27e03504b0708581b5231160100001a110000504b010214001400080808009965034f581b5231160100001a11000001000000000000000000000000000000000061504b050600000000010001002f000000450100000000";
	private static String testeSignificamenteAlteradoComQueixaSono = "504b03041400080808001866034f0000000000000000000000000100000061dd95cb6ec23010457fc9f3f2032b12ea8e35dd2136b459a006121158f4ef4be4ae40442d635a93d59525eb683c733d77b569dab78fc57bc520b13f6d5ebe8f0080f1f8d9d515c443dd77edbeaf5fb7bbba32f1b8edda65bbabf6a7a659cfe7aba7209829d640d6975085fc4b2f2e08c4a07f09899ee17d01dd10e3d42f6163f50c223d03a80497fba06608fa0c7564f0b993023a2ae84a986b2862937328224f38431a8cf9d35e30e8863350cd18efe8cf18c33d751d4edf8ff19cbfc1f8853bfe8ac05e3f130a2e03039fb39f575920ea2a0445ff4f583f13f694616f64f086f3d3f046d0ef2e215bc2d6a01c9b1c26315506d64f9533fcf87b12e92ad5cc437e2b42a0998967e1190c22e96493b8243e4918048d490249f02ceb2f504b070880d9eb531c0100001a110000504b010214001400080808001866034f80d9eb531c0100001a11000001000000000000000000000000000000000061504b050600000000010001002f0000004b0100000000";
	
	
	
	private static String testeSelecionado = testeAlteradoComQueixaSono;
	
	private static String BASE_URL = "/br.com.vixti.focos.webinterface.teste/testManager";
	

	public static void main(String[] args) {

		try {

			
			
			String retorno = logar(usuario, senha);

			System.out.println(retorno);

			String[] retornoArray = retorno.split("@@");

			if (retornoArray[0].contains("error")) {

				exibirMensagemRetorno(retornoArray);

			} else {

								
				
				String idTeste = retornoArray[6].split("=")[1];
				String userId = retornoArray[3].split("=")[1];
				
				String envioIWP = "m=upload&idioma=pt_BR&user_id=" + userId + "&test_id=" + idTeste
						+ "&data=" + testeSelecionado;

				String retornoTeste = consumeRest(envioIWP);

				String[] retornoArrayTeste = retornoTeste.split(";");
				retornoArray = retornoTeste.split("@@");

				String teste = retornoArray[0];
				teste = teste.replace("msgRetorno=", "").replace("&amp;", "&");

				System.out.println(teste);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static String logar(String usuario, String senha) throws Exception {
				String login = "l=" + usuario + "&" + "p=" + senha
				+ "&mac=" + macAdress + "&tipo_teste=curto&dispositivo_teste=mobile";
		String loginZipped = StringConverter.string2zippedHex(login);
		String metodo = "idioma=pt_BR&m=login&data=" + loginZipped;

		// String testelalala =
		// "[blockId=415;subBlockId=1112;type=1;responseTime=546;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=553;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=593;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=304;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=268;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=342;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=329;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=327;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=328;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=260;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=257;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=234;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=283;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=328;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=314;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=235;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=215;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=248;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=188;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=197;tipoSom=null]@@[blockId=415;subBlockId=1112;type=1;responseTime=276;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=415;subBlockId=1112;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=567;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=642;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=799;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=522;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=291;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=260;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=298;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=407;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=386;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=411;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=403;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=294;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=266;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=621;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=445;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=345;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=346;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=349;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=334;tipoSom=null]@@[blockId=416;subBlockId=1113;type=1;responseTime=394;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[blockId=416;subBlockId=1113;type=0;responseTime=0;tipoSom=null]@@[1624:0;1625:0;1626:0;1627:0;1628:0;1629:0;1630:0;1631:0;1632:0;1633:0;]";

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
				resposta.append(output);
			}
			conn.disconnect();

			return resposta.toString();
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);

			return null;
		}
	}

	private static void exibirMensagemRetorno(String[] retornoArray) {

		/* CONVERTER MENSAGEM */
		String teste = retornoArray[0];
		retornoArray = teste.split("=");
		//String teste3 = TranslateUtil.convertUnicodeToJava(retornoArray[1]);

		System.out.println(retornoArray);
	}

}
