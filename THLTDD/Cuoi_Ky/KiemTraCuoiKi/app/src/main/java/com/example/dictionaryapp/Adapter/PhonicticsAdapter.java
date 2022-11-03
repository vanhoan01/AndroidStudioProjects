package com.example.dictionaryapp.Adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.Model.Phonetics;
import com.example.dictionaryapp.R;
import com.example.dictionaryapp.ViewHolders.PhonecticViewHolder;

import java.util.List;

public class PhonicticsAdapter extends RecyclerView.Adapter<PhonecticViewHolder> {
    private Context context;
    private List<Phonetics> phoneticsList;

    public PhonicticsAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhonecticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhonecticViewHolder(LayoutInflater.from(context).inflate(R.layout.phonetic_list_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull PhonecticViewHolder holder, int position) {
        holder.textView_phonetic.setText(phoneticsList.get(position).getText());
        holder.image_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player = new MediaPlayer();
                try{
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource("http:"+ phoneticsList.get(position).getAudio());
                    player.prepare();
                    player.start();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, "Couldn't play audio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }
}
