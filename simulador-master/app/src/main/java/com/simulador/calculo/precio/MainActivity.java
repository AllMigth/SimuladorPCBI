package com.simulador.calculo.precio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button botonCalculadorDelPrecio;
    private Spinner caracteristicasDelSueloSpinner, numeroDePisosSpinner;
    private TextView respuesta;
    private EditText    precioMetroCuadrado,valorSuperficieDeConstrucciones,
                        valorSuperficie;
    private RadioButton materialRadioGroup;

    double caracteristicaDelsuelo, numeroDePisos, valorAlumbrado, valorTelefonia, valorSTransporteUrbano, valorRecoleccionDeBasura, valorAseoDeCalles,
    valorInternet, valorAcerasYBordillos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        respuesta = findViewById(R.id.respuesta);
        precioMetroCuadrado = findViewById(R.id.precioMetroCuadrado);
        valorSuperficie = findViewById(R.id.valorSuperficie);
        valorSuperficieDeConstrucciones = findViewById(R.id.valorSuperficieDeConstrucciones);
        caracteristicasDelSueloSpinner = findViewById(R.id.caracteristicasDelSueloSpinner);
        numeroDePisosSpinner = findViewById(R.id.numeroDePisosSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.caracteristicasDelSueloArray,
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterPisos = ArrayAdapter.createFromResource(
                this,R.array.numeroDePisosArray,
                android.R.layout.simple_spinner_dropdown_item);

        numeroDePisosSpinner.setAdapter(adapterPisos);
        numeroDePisosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String valor = String.valueOf(Log.d("mensaje",parent.getItemAtPosition(position).toString()));
                if (valor == "Planta baja"){
                    numeroDePisos = 50;
                }else if (valor == "1"){
                    numeroDePisos =  10;
                }else if (valor == "2"){
                    numeroDePisos = 150;
                }else if (valor == "3"){
                    numeroDePisos =  200;
                }else if (valor == "4"){
                    numeroDePisos =  250;
                }else if (valor == "5"){
                    numeroDePisos = 300;
                }else{
                    numeroDePisos = 25;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                numeroDePisos = 0;
            }
        });

        caracteristicasDelSueloSpinner.setAdapter(adapter);
        caracteristicasDelSueloSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String valor = String.valueOf(Log.d("mensaje",parent.getItemAtPosition(position).toString()));
                if (valor == "Seco"){
                    caracteristicaDelsuelo = 30;
                }else if (valor == "Inundable"){
                    caracteristicaDelsuelo =  -50;
                }else if (valor == "Inestable"){
                    caracteristicaDelsuelo = -40;
                }else if (valor == "Rocoso"){
                    caracteristicaDelsuelo =  -40;
                }else if (valor == "Cenagoso"){
                    caracteristicaDelsuelo =  -45;
                }else if (valor == "Humedo"){
                    caracteristicaDelsuelo = -25;
                }else{
                    caracteristicaDelsuelo = 0;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                caracteristicaDelsuelo = 0;
            }
        });

        botonCalculadorDelPrecio = findViewById(R.id.botonCalculadorDelPrecio);
        botonCalculadorDelPrecio.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                respuesta.setText(
                        producto (
                                Integer.parseInt(precioMetroCuadrado.getText().toString()),Integer.parseInt(valorSuperficie.getText().toString()),
                                valorTelefonia, valorSTransporteUrbano, valorRecoleccionDeBasura, valorAseoDeCalles, valorInternet, valorAlumbrado,
                                valorAcerasYBordillos ) + " = Precio comercial del bien inmueble para el periodo 2020");
            }});
    }

    public double producto(int PMC, int VST, double valorAlumbrado, double valorTelefonia, double valorSTransporteUrbano, double valorRecoleccionDeBasura, double valorAseoDeCalles, double valorInternet, double caracteristicaDelsuelo){
        double precio = ((PMC*VST)  + (valorAlumbrado + valorTelefonia + valorSTransporteUrbano + valorRecoleccionDeBasura + valorAseoDeCalles+valorInternet+caracteristicaDelsuelo));
        return precio;
    }



    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.siAlumbrado:
                if (checked)
                    valorAlumbrado = 50;
                    break;
            case R.id.noAlumbrado:
                if (checked)
                    valorAlumbrado = 0;
                    break;

            case R.id.siTelefonia:
                if (checked)
                    valorTelefonia = 30;
                break;
            case R.id.noTelefonia:
                if (checked)
                    valorAlumbrado = 0;
                break;

            case R.id.siTransporteUrbano:
                if (checked)
                    valorSTransporteUrbano = 40;
                break;
            case R.id.noTransporteUrbano:
                if (checked)
                    valorSTransporteUrbano = 0;
                break;

            case R.id.siRecoleccionDeBasura:
                if (checked)
                    valorRecoleccionDeBasura = 10;
                break;
            case R.id.noRecoleccionDeBasura:
                if (checked)
                    valorRecoleccionDeBasura = 0;
                break;

            case R.id.siAseoDeCalles:
                if (checked)
                    valorAseoDeCalles = 15;
                break;
            case R.id.noAseoDeCalles:
                if (checked)
                    valorAseoDeCalles = 0;
                break;

            case R.id.siInternet:
                if (checked)
                    valorInternet = 40;
                break;
            case R.id.noInternet:
                if (checked)
                    valorInternet = 0;
                break;

            case R.id.siAcerasYBordillos:
                if (checked)
                    valorAcerasYBordillos = 50;
                break;
            case R.id.noAcerasYBordillos:
                if (checked)
                    valorAcerasYBordillos = 0;
                break;
            case R.id.noEdificacion:
                if (checked)
                    ((EditText) findViewById(R.id.valorSuperficieDeConstrucciones)).setEnabled(false);
                    ((Spinner) findViewById(R.id.numeroDePisosSpinner)).setEnabled(false);
                    ((RadioButton) findViewById(R.id.materialCemento)).setEnabled(false);
                ((RadioButton) findViewById(R.id.materialBambu)).setEnabled(false);
                ((RadioButton) findViewById(R.id.materialMadera)).setEnabled(false);
                break;
            case R.id.siEdificacion:
                if (checked)
                    ((EditText) findViewById(R.id.valorSuperficieDeConstrucciones)).setEnabled(true);
                ((Spinner) findViewById(R.id.numeroDePisosSpinner)).setEnabled(true);
                ((RadioButton) findViewById(R.id.materialCemento)).setEnabled(true);
                ((RadioButton) findViewById(R.id.materialBambu)).setEnabled(true);
                ((RadioButton) findViewById(R.id.materialMadera)).setEnabled(true);
                break;
        }
    }
}