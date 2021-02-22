package com.system.bibliotec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class Main {

    public static void main(String[] args) {

        // System.out.println("testadoo");

        // String ss = "joao pedro";
        //
        // System.out.println(ss.length());

        // Instant s = Instant.now();

        // System.out.println(LocalDateTime.ofInstant(s, ZoneOffset.UTC));

        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	System.out.println(encoder.encode("@pedroleite"));
        // System.out.println();
        // System.out.println();

        // System.out.println(encoder.matches("@joao",
        // "$2a$10$2DRDf6cgsKV43M.b4oDKyuY6FknXu1doaHwyXNJIsvJpQsA6D5qQ."));

        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

        //	if (regex.matcher("Joao pedro%").find()) {

        //		System.out.println("TEM CARACTERE ESPECIAL/......");


        //}
        //    System.out.println("passou do return fvazio.");


        //   System.out.println("quantidade de espaçso abaixo");
        // System.out.println(new Main().contaEspacos("SIMPLE JOB teste testet teste "));


        //	new Main().getClassForName("SIMPLE JOB");


        //	new Main().testIt("https://www.facebook.com");


        int a = 0;

        int b = 1;

        int c = 20;


        if (a == 0 && (b > 1 || c > 2)) {

            System.out.println("deu certo..");
        }


        List<LocalDate> datasDaSemanaDiaX =
                Main.relacaoDatasSemana(LocalDate.of(2020, 12, 16));


        datasDaSemanaDiaX.forEach(i -> System.out.println(i));

    }


    private static List<LocalDate> relacaoDatasSemana(LocalDate date) {
        LocalDate start = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return IntStream.range(0, 7).mapToObj(start::plusDays).collect(Collectors.toList());
    }

    public static int contaEspacos(String texto) {
        return texto.length() - texto.replaceAll(" ", "").length();
    }

    private Class<?> getClassForName(String nomeClass) {


        String camelCaseNameClass = getNameCase(nomeClass);

        String classPackage = "com.system.bibliotec.service.automacao.trabalhos.".concat(camelCaseNameClass);

        Class job = null;
        try {

            job = getClass().forName(classPackage);

            System.out.println(job.toString());
            System.out.println(job.toString());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return job;

    }


    private String getNameCase(String text) {
        return StringUtils.remove(WordUtils.capitalizeFully(text, ' '), " ");
    }


    private void testIt(String https_url) {
        try {
            URL url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            if (con.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                String line;
                String response = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
                System.out.println(response);
            }
            con.getCipherSuite();
            Certificate[] certs = con.getServerCertificates();

            print_https_cert(con);
            print_content(con);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print_https_cert(HttpsURLConnection con) {
        if (con != null) {
            try {
                System.out.println("Codigo de resposta da URL : " + con.getResponseCode());
                System.out.println("Suide de Criptografia : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for (Certificate cert : certs) {
                    System.out.println("Tipo do Certificado........: " + cert.getType());
                    System.out.println("Codigo Hash do Certificado.: " + cert.hashCode());
                    System.out.println("Algoritmo da Chave Publica.: " + cert.getPublicKey().getAlgorithm());
                    System.out.println("Formato do Chave Publica...: " + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }
            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void print_content(HttpsURLConnection con) {
        if (con != null) {
            try {
                System.out.println("****** Conteudo da URL ********");
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String input;
                while ((input = br.readLine()) != null) {
                    System.out.println(input);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
