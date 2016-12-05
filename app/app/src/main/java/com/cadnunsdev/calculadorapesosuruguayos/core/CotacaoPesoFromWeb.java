package com.cadnunsdev.calculadorapesosuruguayos.core;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago Silva on 05/12/2016.
 */

public class CotacaoPesoFromWeb {

    public static final String url = "http://pt.exchange-rates.org/converter/BRL/UYU/1";
    public static void get(CotacaoPesoFromWebFinished callbacck){
        CotacaoPesoFromWebTask task = new CotacaoPesoFromWebTask(callbacck);
        task.execute();
    }

    private static class CotacaoPesoFromWebTask extends AsyncTask<String, Void, CotacaoMoeda>{

        private final CotacaoPesoFromWebFinished _callback;

        public CotacaoPesoFromWebTask(CotacaoPesoFromWebFinished callback){
            _callback = callback;
        }


        @Override
        protected CotacaoMoeda doInBackground(String... strings) {
            try {
                String content = convertStreamToString(new URL(url).openConnection().getInputStream());
                CotacaoMoeda cotacaoMoeda = new CotacaoMoeda();
                cotacaoMoeda.setCotacao(getDataFromSpan("ctl00_M_lblToAmount",content));
                cotacaoMoeda.setMoedaOrigem(getDataFromSpan("ctl00_M_lblFromIsoCode",content));
                cotacaoMoeda.setMoedaDestino(getDataFromSpan("ctl00_M_lblToIsoCode",content));
                cotacaoMoeda.setSitioWeb(url);
                cotacaoMoeda.setHoraDaAtualizacao(Calendar.getInstance().getTime());
                return cotacaoMoeda;
            }catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        private String getDataFromSpan(String id, String htmlContent){
            String tag = String.format("<span id=\"%s\">(\\S+)</span>", id);
            Pattern pattern = Pattern.compile(tag);
            Matcher matcher = pattern.matcher(htmlContent);
            if(matcher.find()){
                return matcher.group(1);
            }else {
                return "";
            }
        }

        private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }


        @Override
        protected void onPostExecute(CotacaoMoeda cotacaoMoeda) {
            if(cotacaoMoeda != null)
                _callback.getResult(cotacaoMoeda);
        }
    }

    public interface CotacaoPesoFromWebFinished{

        public void getResult(CotacaoMoeda cotacaoMoeda);
    }
}
