package cl.inacap.calculadoranotas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.calculadoranotas.dto.Nota;

public class MainActivity extends AppCompatActivity {

    private EditText notaTxt;
    private EditText porcentajeTxt;
    private Button agregarBtn;
    private Button limpiarBtn;
    private ListView notasLv;
    private List<Nota> notas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.notaTxt = findViewById(R.id.notaTxt);
        this.porcentajeTxt = findViewById(R.id.porcentajeTxt);
        this.agregarBtn = findViewById(R.id.agregarBtn);
        this.limpiarBtn = findViewById(R.id.limpiarBtn);
        this.notasLv = findViewById(R.id.notasLv);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> errores = new ArrayList<>();
                String notaStr = notaTxt.getText().toString().trim();
                String porcStr = porcentajeTxt.getText().toString().trim();
                int porcentaje;
                double nota;
                try {
                    nota = Double.parseDouble(notaStr);
                    if(nota < 1.0 || nota > 7.0){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("-la nota debe ser un numero entre 1.0 y 7.0");
                }

                try{
                    porcentaje = Integer.parseInt(porcStr);
                    if (porcentaje < 1 || porcentaje > 100){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("-El porcentaje debe ser un numero entre 1 y 100");
                }

                if (errores.isEmpty()){
                    //Ingresar la nota
                    //TODO: ingresar nota y mostrarla en el listview
                }else {
                    mostrarErrores(errores);
                }
            }
        });
    }

    private  void mostrarErrores(List<String> errores){
        //1 generar una cadena de texto con los errores
        String mensaje = "";
        for (String e: errores){
            mensaje+= "-" + e + "\n";
        }
        //2 mostrar un mensaje de alerta
        AlertDialog.Builder alertBueilder = new AlertDialog.Builder(MainActivity.this);
        //Chaining (encadenamiento)
        alertBueilder.setTitle("Error de validacion")//define titulo
                .setMessage(mensaje)//define mensaje del dialogo
                .setPositiveButton("aceptar", null)//agrega el boton aceptar
                .create()//Crea el alert
                .show();//lo muestra
    }
}