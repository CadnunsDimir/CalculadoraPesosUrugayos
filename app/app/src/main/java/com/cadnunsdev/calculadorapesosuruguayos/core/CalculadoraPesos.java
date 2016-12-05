package com.cadnunsdev.calculadorapesosuruguayos.core;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Tiago Silva on 05/12/2016.
 */

public class CalculadoraPesos {
    private final EditText _edtReal;
    private final EditText _edtPesos;
    private BigDecimal _taxaCambioBRLparaUYU;
    private final TextWatcher _edtPesosTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
//            String texto = _edtPesos.getText().toString().replace(".","").replace(",",".");
//            BigDecimal pesos = new BigDecimal(texto);
//            BigDecimal result = RoundTo(pesos.divide( _taxaCambioBRLparaUYU),2);
//            String valorFinal = result.toString().replace(",",".");
//            if(valorFinal != _edtReal.toString())
//                _edtReal.setText(valorFinal);

                String texto = _edtPesos.getText().toString().replace(".","").replace(",",".");
                BigDecimal pesos = new BigDecimal(texto.length() > 0 ? texto : "0");
                try {
                    if(podeAtualizarCampos()) {
                        BigDecimal divisao = pesos.divide(_taxaCambioBRLparaUYU, 2, BigDecimal.ROUND_CEILING);
                        BigDecimal result = RoundTo(divisao, 2);
                        String valorFinal = result.toString().replace(".", ",");
                        if (!texto.equals(_valorPesos.replace(",", "."))) {
                            _valorReais = valorFinal;
                            _valorPesos = texto;
                            _edtReal.setText(valorFinal);
                            setDataValoresAtualizados();
                        }
                    }
                }catch (Exception  ex){
                    ex.printStackTrace();
                }

        }
    };
    private String _valorPesos;
    private String _valorReais;
    private TextWatcher _edtRealTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {


                try {
                    if(podeAtualizarCampos()) {
                        String texto = _edtReal.getText().toString().replace(".", "").replace(",", ".");
                        BigDecimal reais = new BigDecimal(texto.length() > 0 ? texto : "0");
                        BigDecimal result = RoundTo(reais.multiply(_taxaCambioBRLparaUYU), 2);
                        String valorFinal = result.toString().replace(".", ",");
                        if (!texto.equals(_valorReais.replace(",", "."))) {
                            _valorPesos = valorFinal;
                            _valorReais = texto;
                            _edtPesos.setText(valorFinal);
                            setDataValoresAtualizados();
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }



        }
    };
    private Locale localeBR = new Locale("pt","br"); //Cria o locale do Brasil;
    private Date _dataAtualizacao;

    public CalculadoraPesos(EditText edtReal, EditText edtPesos){
        _edtReal = edtReal;
        _edtPesos = edtPesos;
        _taxaCambioBRLparaUYU = new BigDecimal("8.4141");//taxa no dia 05/12/2016
        _valorPesos = "0";
        _valorReais = "0";
        _edtReal.addTextChangedListener(_edtRealTextWatcher);
        _edtPesos.addTextChangedListener(_edtPesosTextWatcher);
        setDataValoresAtualizados();
    }

    private BigDecimal RoundTo(BigDecimal valor, int casa){
//        NumberFormat n = NumberFormat.getInstance(localeBR); //Cria um NumberFormat com base no locale
//        n.setMinimumFractionDigits(2); //Seta o número mínimo de casa decimal
//        n.setMaximumFractionDigits(2); //Seta o número máximo de casa decimal
//        return valor.round(new MathContext(casa));

        return valor.setScale(casa, RoundingMode.CEILING);
    }
    private void setDataValoresAtualizados (){
        Calendar cal = Calendar.getInstance();
        _dataAtualizacao = cal.getTime();
    }
    private boolean podeAtualizarCampos(){
        Calendar cal = Calendar.getInstance();
        long seconds = (cal.getTime().getTime() - _dataAtualizacao.getTime()/1000);
        return seconds > 1;
    }

    public void setContacao(String contacao) {
        _taxaCambioBRLparaUYU = new BigDecimal(contacao.replace(",","."));
    }
}
