package id.slametriyadi.sqlite_imastudio.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import id.slametriyadi.sqlite_imastudio.model.Nota;

import static android.provider.BaseColumns._ID;
import static id.slametriyadi.sqlite_imastudio.database.DatabaseNotaContract.NotaColumn.DESKRIPSI;
import static id.slametriyadi.sqlite_imastudio.database.DatabaseNotaContract.NotaColumn.JUDUL;
import static id.slametriyadi.sqlite_imastudio.database.DatabaseNotaContract.NotaColumn.TANGGAL;
import static id.slametriyadi.sqlite_imastudio.database.DatabaseNotaContract.TABLE_NOTA;

public class NotaHelper {

    private static DatabaseNotaHelper databaseNotaHelper;
    private static SQLiteDatabase sqLiteDatabase;
    private static String DATABASE_TABLE = TABLE_NOTA;

    public NotaHelper(Context context) {
        databaseNotaHelper = new DatabaseNotaHelper(context);
    }

    public void open() {
        sqLiteDatabase = databaseNotaHelper.getWritableDatabase();
    }

    public ArrayList<Nota> getAllNota() {
        ArrayList<Nota> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC",
                null
                );
        cursor.moveToFirst();

        Nota nota;
        if (cursor.getCount() > 0) {
            do {
                nota = new Nota();
                nota.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                nota.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(JUDUL)));
                nota.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                nota.setTanggal(cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL)));

                arrayList.add(nota);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public void insertNota(Nota nota) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JUDUL, nota.getJudul());
        contentValues.put(DESKRIPSI, nota.getDeskripsi());
        contentValues.put(TANGGAL, nota.getTanggal());
        sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
    }

    public void updateNota(Nota nota) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JUDUL, nota.getJudul());
        contentValues.put(DESKRIPSI, nota.getDeskripsi());
        contentValues.put(TANGGAL, nota.getTanggal());
        sqLiteDatabase.update(DATABASE_TABLE, contentValues, _ID + "= '" + nota.getId() + "'", null);
    }

    public void deleteNota(int id) {
        sqLiteDatabase.delete(DATABASE_TABLE, _ID + " = '" + id + "'", null);
    }
}
