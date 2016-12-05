package com.cadnunsdev.calculadorapesosuruguayos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cadnunsdev.calculadorapesosuruguayos.core.CalculadoraPesos;
import com.cadnunsdev.calculadorapesosuruguayos.core.CotacaoMoeda;
import com.cadnunsdev.calculadorapesosuruguayos.core.CotacaoPesoFromWeb;

public class MainActivity extends AppCompatActivity {

    private EditText _edtReal;
    private EditText _edtPesos;
    private CalculadoraPesos _calculadora;
    private String _cotacao;
    private TextView _tvDadosSitioWeb;
    private Button _btnAtualizaTaxaCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _edtReal = (EditText)findViewById(R.id.edtReal);
        _edtPesos = (EditText)findViewById(R.id.edtPesos);
        _tvDadosSitioWeb = (TextView)findViewById(R.id.tvDadosSitioWeb);
        _btnAtualizaTaxaCambio = (Button)findViewById(R.id.btnAtualizaTaxaCambio);
        _calculadora = new CalculadoraPesos(_edtReal, _edtPesos);
        _tvDadosSitioWeb.setText("Aguardando leitura site");
        _btnAtualizaTaxaCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AtualizarTaxaCambio();
            }
        });
        AtualizarTaxaCambio();
    }

    private void AtualizarTaxaCambio(){
        CotacaoPesoFromWeb.get(new CotacaoPesoFromWeb.CotacaoPesoFromWebFinished() {
            @Override
            public void getResult(CotacaoMoeda cotacaoMoeda) {
                _calculadora.setContacao(cotacaoMoeda.getCotacao());
                _tvDadosSitioWeb.setText(String.format("Site: %s, \n 1 %s =>> %s %s \n Atualizado em %s",
                        cotacaoMoeda.getSitioWeb(),
                        cotacaoMoeda.getMoedaOrigem(),
                        cotacaoMoeda.getCotacao(),
                        cotacaoMoeda.getMoedaDestino(),
                        cotacaoMoeda.getHoraDaAtualizacao().toString()));
            }
        });
    }
}
