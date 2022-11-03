package com.nguyenvanhoan.qlsinhvien;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

public class Dao_SinhVien {
    private SQLiteHelper dbHelper;
    private SQLiteDatabase database;

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }
    public Dao_SinhVien(Context context) {
        dbHelper = new SQLiteHelper(context,SQLiteHelper.DATABASE_NAME,null,SQLiteHelper.VERSION);
    }

    public SinhVien getSinhVien(int id) {
        open();
        Cursor cursor = database.query(SQLiteHelper.TABLE_SINHVIEN, new String[]{SQLiteHelper.ID, SQLiteHelper.HO_VA_TEN}, SQLiteHelper.ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        SinhVien obj = null;
        while (!cursor.isAfterLast()) {
            obj = set(cursor);
            cursor.moveToNext();
        }
        close();
        return obj;
    }

    // lấy ra danh sách tất cả sinh viên và sắp xếp theo điều kiện : orderby_status ==0 thì không sắp xếp. và ngược lại thì ta sắp xếp danh sách sinh viên dựa trên trường id giảm dần
    public LinkedList<SinhVien> getAllSinhVien(int orderby_status) {
        open();
        LinkedList<SinhVien> ls = new LinkedList<>();
        Cursor cursor = database.query(SQLiteHelper.TABLE_SINHVIEN, new String[]{SQLiteHelper.ID, SQLiteHelper.HO_VA_TEN}, null, null, null, null, (orderby_status == 0)? null : SQLiteHelper.ID+" DESC " );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SinhVien obj = set(cursor);
            ls.add(obj);
            cursor.moveToNext();
        }
        close();
        return ls;
    }

    // cập nhật lại tên mới cho sinhvien. dựa vào id.
    public boolean updateSinhVien(int id, String new_hovaten)
    {
        open();
        ContentValues contentValue =new ContentValues();
        contentValue.put(SQLiteHelper.HO_VA_TEN,new_hovaten);
        int result =database.update(SQLiteHelper.TABLE_SINHVIEN,contentValue,SQLiteHelper.ID+ " = ?",new String[]{String.valueOf(id)});
        return result !=-1;
    }

    //xóa tất cả danh sách sinhvien
    public void deleteall() {
        open();
        database.delete(SQLiteHelper.TABLE_SINHVIEN, null, null);
        close();
    }

    //xóa 1 sinh viên dựa trên ID của sinh viên đó
    public void deleteSinhVien(int id) {
        open();
        database.delete(SQLiteHelper.TABLE_SINHVIEN, SQLiteHelper.ID +" = ?", new String[]{String.valueOf(id)});
        close();
    }

    // insert sinh viên vào database
    public boolean insert_SinhVien(SinhVien sinhVien) {
        // deleteall();
        long result = 0;
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.HO_VA_TEN, sinhVien.getHovaten());
        result = database.insert(SQLiteHelper.TABLE_SINHVIEN, null, contentValues);
        close();
        return result != -1;


    }

    // lấy dữ liệu từ trong cursor set vào đối tượng SinhVien.  -> hàm này giống như ta convert từ đối tượng Cursor qua đối tượng SinhVien
    private SinhVien set(Cursor cursor) {
    /////////////////////////////////////#
        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.ID));
        @SuppressLint("Range") String hovaten = cursor.getString(cursor.getColumnIndex(SQLiteHelper.HO_VA_TEN));
        SinhVien obj = new SinhVien(id, hovaten);
        return obj;
    }

}
