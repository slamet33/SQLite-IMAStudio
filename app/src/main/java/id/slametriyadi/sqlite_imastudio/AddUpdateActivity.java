package id.slametriyadi.sqlite_imastudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.slametriyadi.sqlite_imastudio.database.NotaHelper;
import id.slametriyadi.sqlite_imastudio.model.Nota;

public class AddUpdateActivity extends AppCompatActivity {

    EditText edtDeskripsi, edtJudul;
    Button btnSubmit;

    public static int REQUEST_ADD = 100;
    public static int RESULT_ADD = 101;

    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;

    public static int RESULT_DELETE = 301;

    public boolean isUpdate = false;

    public static String EXTRA_POSITION = "extra_position";
    public static String EXTRA_NOTA = "extra_nota";

    Nota nota;
    NotaHelper notaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update);

        edtDeskripsi = findViewById(R.id.edt_deskripsi);
        edtJudul = findViewById(R.id.edt_judul);
        btnSubmit = findViewById(R.id.btn_submit);

        notaHelper = new NotaHelper(this);
        notaHelper.open();

        nota = getIntent().getParcelableExtra(EXTRA_NOTA);

        if (nota != null) {
            isUpdate = true;
        }

        String actionBarTitle, btnTitle;

        if (isUpdate && nota != null) {
            actionBarTitle = "Update Data";
            btnTitle = "Update";

            edtDeskripsi.setText(nota.getDeskripsi());
            edtJudul.setText(nota.getJudul());
        } else {
            actionBarTitle = "Tambah Data";
            btnTitle = "Tambah";
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
        }

        btnSubmit.setText(btnTitle);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judul = edtJudul.getText().toString();
                String deskripsi = edtDeskripsi.getText().toString();

                if (TextUtils.isEmpty(judul) || TextUtils.isEmpty(deskripsi)) {
                    Toast.makeText(AddUpdateActivity.this, "Can't Be Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Nota newNota = new Nota();
                    newNota.setJudul(judul);
                    newNota.setDeskripsi(deskripsi);

                    if (isUpdate) {
                        newNota.setTanggal(nota.getTanggal());
                        newNota.setId(nota.getId());

                        notaHelper.updateNota(newNota);
                        setResult(RESULT_UPDATE);
                        finish();
                    } else {
                        newNota.setTanggal(currentDate());

                        notaHelper.insertNota(newNota);
                        setResult(RESULT_ADD);
                        finish();
                    }
                }
            }
        });
    }

    private String currentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isUpdate){
            getMenuInflater().inflate(R.menu.menu_delete, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_delete){

            notaHelper.deleteNota(nota.getId());
            setResult(RESULT_DELETE);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
