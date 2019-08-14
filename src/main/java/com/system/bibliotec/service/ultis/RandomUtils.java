package com.system.bibliotec.service.ultis;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

	private static final int DEF_COUNT = 20;

    private RandomUtils() {
    }

    /**
     * Gerando senha aleatoriamente
     *
     * @return Uma Senha aleatoria.
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Gerando chave de ativação de conta.
     *
     * @return Codigo de ativação de conta.
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
     * Gerando uma chave para resetar uma conta.
     *
     * @return Chave para Resetar conta.
     */
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
    
    
    //TODO: Remover ester metodo quando implementar o front-end desta aplicação....
    /**
     * 
     * Gerando numero aleatorio para anexar a API de busca de fotos para os livros....
     *
     * @return Numero aleatorio.
     */
	 public static String randomIntForUrlPic() {
	
		 	Random random = new Random();
		 	return String.valueOf(random.nextInt(999));
	 }
	 
	 	//TODO: Remover ester metodo quando implementar o gerador oficial e leitor de codigo de barras desta aplicação....
	    /**
	     * 
	     * Gerando numero aleatorio para o codigo de barras do Livro
	     *
	     * @return Numero aleatorio.
	     */	
		  public static String randomCodBarras() {
		        return RandomStringUtils.randomNumeric(DEF_COUNT);
		    }
}
