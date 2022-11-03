package com.nguyenvanhoan.bookstore.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenvanhoan.bookstore.LibraryFragment;
import com.nguyenvanhoan.bookstore.Models.BookLibrary;
import com.nguyenvanhoan.bookstore.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookLibraryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BookLibrary> bookList;

    public BookLibraryAdapter(Context context, int layout, List<BookLibrary> bookList) {
        this.context = context;
        this.layout = layout;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgHinh;
        TextView txtTen, txtTacGia;
        ImageButton ibtAn;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new BookLibraryAdapter.ViewHolder();
            //anh xa view
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtTacGia = (TextView) view.findViewById(R.id.textViewNguoiDang);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            holder.ibtAn = (ImageButton) view.findViewById(R.id.imageButtonAn);
            view.setTag(holder);
        }else {
            holder = (BookLibraryAdapter.ViewHolder) view.getTag();
        }
        try {
            BookLibrary book = bookList.get(i);
            String ten = book.getTen();
            if(ten.length() > 27)
                ten = ten.substring(0, 25) + "...";
            holder.txtTen.setText(ten);
            try {
                String url = "https://bookstoreandroid.000webhostapp.com/bookstore/image/" + book.getHinh();
                new LoadImageInternet(holder.imgHinh).execute(url);
            }catch (Exception e){
                //holder.imgAnh.setImageResource(R.drawable.th1);
            }
            holder.txtTacGia.setText(book.getTacGia());
            holder.ibtAn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        } catch (Exception e){

        }

        return view;
    }
    public Bitmap loadImageInternet(String imageUrl) {
        Bitmap bitmapHinh = null;
        try {
            URL url = new URL(imageUrl);
            InputStream inputStream = url.openConnection().getInputStream();
            bitmapHinh = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmapHinh;
    }

    public class LoadImageInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadImageInternet(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        Bitmap bitmapHinh = null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapHinh = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            bmImage.setImageBitmap(bitmap);
        }
    }
//    private void updateAn(String url, String productID, String userID, int status){
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        String urlGetLibrary = "https://bookstoreandroid.000webhostapp.com/bookstore/getthuvien.php?iduser=US00000001&status=2";
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(context, "Xãy ra lỗi!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("productID", productID);
//                params.put("userID", userID);
//                params.put("status", String.valueOf(status));
//
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }
}
