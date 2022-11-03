package com.nguyenvanhoan.bookstore.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenvanhoan.bookstore.Models.Book;
import com.nguyenvanhoan.bookstore.Models.PopularBook;
import com.nguyenvanhoan.bookstore.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<PopularBook> bookList;

    public BookAdapter(Context context, int layout, List<PopularBook> bookList) {
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
        TextView txtSTT, txtTen, txtNguoiDang, txtLuotDoc, txtGia;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            //anh xa view
            holder.txtSTT = (TextView) view.findViewById(R.id.textViewSTT);
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtNguoiDang = (TextView) view.findViewById(R.id.textViewNguoiDang);
            holder.txtLuotDoc = (TextView) view.findViewById(R.id.textViewLuotDoc);
            holder.txtGia = (TextView) view.findViewById(R.id.textViewGia);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            PopularBook book = bookList.get(i);
            try {
                String url = "https://bookstoreandroid.000webhostapp.com/bookstore/image/" + book.getAnh();
                new LoadImageInternet(holder.imgHinh).execute(url);
            }catch (Exception e){
                //holder.imgAnh.setImageResource(R.drawable.th1);
            }
            holder.txtSTT.setText(String.valueOf(book.getStt()));
            String ten = book.getTitle();
            if(ten.length() > 27)
                ten = ten.substring(0, 25) + "...";
            holder.txtTen.setText(ten);
            holder.txtNguoiDang.setText(book.getPublisher());
            holder.txtLuotDoc.setText(String.valueOf(book.getView()) + " lượt đọc");
            int gia = book.getPrice();
            if(gia == 0){
                holder.txtGia.setText("Miễn phí");
            }else{
                holder.txtGia.setText(String.valueOf(gia) + " đ");
            }
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
}
