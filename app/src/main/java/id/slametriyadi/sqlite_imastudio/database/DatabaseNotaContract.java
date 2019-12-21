package id.slametriyadi.sqlite_imastudio.database;

import android.provider.BaseColumns;

// This class created for preparing the name
// of database and each column

class DatabaseNotaContract {
    static String TABLE_NOTA = "nota";

    // Using Base Column Implement for  comes with an _id
    // column that autoincrements and can function as a primary key.
    static final class NotaColumn implements BaseColumns {
        static String JUDUL = "judul";
        static String DESKRIPSI = "deskripsi";
        static String TANGGAL = "tanggal";
    }
}
