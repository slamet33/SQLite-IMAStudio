package id.slametriyadi.sqlite_imastudio.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseNotaHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME = "dbnota";
    static int DATABASE_VERSION = 1;

    static final String SQL_CREATE_TABLE = String.
            format("CREATE TABLE %s"
                    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "%s TEXT NOT NULL,"
                    + "%s TEXT NOT NULL,"
                    + "%s TEXT NOT NULL)",
                    DatabaseNotaContract.TABLE_NOTA,
                    DatabaseNotaContract.NotaColumn._ID,
                    DatabaseNotaContract.NotaColumn.JUDUL,
                    DatabaseNotaContract.NotaColumn.DESKRIPSI,
                    DatabaseNotaContract.NotaColumn.TANGGAL);

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseNotaContract.TABLE_NOTA);
        onCreate(sqLiteDatabase);
    }

    public DatabaseNotaHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
