package id.slametriyadi.sqlite_imastudio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import id.slametriyadi.sqlite_imastudio.adapter.NotaAdapter;
import id.slametriyadi.sqlite_imastudio.database.NotaHelper;
import id.slametriyadi.sqlite_imastudio.model.Nota;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvNota;
    NotaHelper notaHelper;
    NotaAdapter adapter;
    ArrayList<Nota> listNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvNota = findViewById(R.id.rv_nota);
        rvNota.setLayoutManager(new LinearLayoutManager(this));

        notaHelper = new NotaHelper(this);
        notaHelper.open();

        listNota = new ArrayList<>();

        adapter = new NotaAdapter(this);
        rvNota.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUpdateActivity.class);
                startActivityForResult(intent, AddUpdateActivity.REQUEST_ADD);
            }
        });

        new LoadNotaAsynctask().execute();
    }

    private class LoadNotaAsynctask extends AsyncTask<Void, Void, ArrayList<Nota>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (listNota.size() > 0) {
                listNota.clear();
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Nota> notas) {
            super.onPostExecute(notas);
            listNota.addAll(notas);
            adapter.setListNota(listNota);
            adapter.notifyDataSetChanged();

            if (listNota.size() == 0) {
                Toast.makeText(MainActivity.this, "Data Empty", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected ArrayList<Nota> doInBackground(Void... voids) {
            return notaHelper.getAllNota();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddUpdateActivity.REQUEST_ADD){
            if (resultCode == AddUpdateActivity.RESULT_ADD){

                Toast.makeText(this, "Berhasil tambah data", Toast.LENGTH_SHORT).show();
                new LoadNotaAsynctask().execute();
            }
        }

        else if(requestCode == AddUpdateActivity.REQUEST_UPDATE){
            if (resultCode == AddUpdateActivity.RESULT_UPDATE){

                Toast.makeText(this, "Berhasil update data", Toast.LENGTH_SHORT).show();
                new LoadNotaAsynctask().execute();
            }
            else if (resultCode == AddUpdateActivity.RESULT_DELETE){

                Toast.makeText(this, "Berhasil hapus data", Toast.LENGTH_SHORT).show();
                new LoadNotaAsynctask().execute();
            }
        }
    }
}
