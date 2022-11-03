package com.example.dictionaryapp.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;

public class PhonecticViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_phonetic;
    public ImageButton image_audio;
    public PhonecticViewHolder(@NonNull View itemView) {
        super(itemView);

        AnhXa();

    }
    public void AnhXa(){
        textView_phonetic = itemView.findViewById(R.id.textView_phonetic);
        image_audio = itemView.findViewById(R.id.image_audio);
    }
}
