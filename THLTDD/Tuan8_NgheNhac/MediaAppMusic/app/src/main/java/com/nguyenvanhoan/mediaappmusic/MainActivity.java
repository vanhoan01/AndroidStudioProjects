package com.nguyenvanhoan.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Animatable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView txtTitle;
    private ImageButton ibtPrev, ibtPlay, ibtStop, ibtNext;
    private TextView txtTimeNow, txtTimeTotal;
    private SeekBar sbTime;

    private ArrayList<Song> songArray;
    private int position = 0;

    private ImageView imgHinh;
    private MediaPlayer mediaPlayer;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        AnhXa();
        AddSong();

        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);

        khoiTaoMediaPlayer();

        ibtPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    ibtPlay.setImageResource(R.drawable.play);
                }
                else{
                    mediaPlayer.start();
                    ibtPlay.setImageResource(R.drawable.pause);
                }
                SetTimeTotal();
                UpdateTimeSong();
                imgHinh.startAnimation(animation);
            }
        });
        ibtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                ibtPlay.setImageResource(R.drawable.play);
                khoiTaoMediaPlayer();
            }
        });

        ibtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if(position >= songArray.size()){
                    position = 0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoiTaoMediaPlayer();
                mediaPlayer.start();
                ibtPlay.setImageResource(R.drawable.pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        ibtPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(position < 0){
                    position = songArray.size() - 1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoiTaoMediaPlayer();
                mediaPlayer.start();
                ibtPlay.setImageResource(R.drawable.pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });
        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbTime.getProgress());
            }
        });
    }
    private void UpdateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangPhut = new SimpleDateFormat("mm:ss");
                txtTimeNow.setText(dinhDangPhut.format(mediaPlayer.getCurrentPosition()));
                sbTime.setProgress(mediaPlayer.getCurrentPosition());
                //Kiem tra ket thuc bai hat -> next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position >= songArray.size()){
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        khoiTaoMediaPlayer();
                        mediaPlayer.start();
                        ibtPlay.setImageResource(R.drawable.pause);
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }
    private void SetTimeTotal(){
        SimpleDateFormat dinhDangPhut = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangPhut.format(mediaPlayer.getDuration()));
        sbTime.setMax(mediaPlayer.getDuration());
    }
    private void khoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, songArray.get(position).getFile());
        txtTitle.setText(songArray.get(position).getTitle());
    }
    private void AddSong() {
        songArray = new ArrayList<>();
        songArray.add(new Song("Ai chung tình được mãi", R.raw.ai_chung_tinh_duoc_mai));
        songArray.add(new Song("An Thần - LowG", R.raw.an_than_lowg));
        songArray.add(new Song("Bước qua nhau - Vũ", R.raw.buoc_qua_nhau_vu));

    }

    private void AnhXa() {
        txtTitle = (TextView) findViewById(R.id.textViewTitle);
        txtTimeNow = (TextView) findViewById(R.id.textViewTimeNow);
        txtTimeTotal = (TextView) findViewById(R.id.textViewTimeTotal);
        ibtPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        ibtPrev = (ImageButton) findViewById(R.id.imageButtonPrev);
        ibtStop = (ImageButton) findViewById(R.id.imageButtonStop);
        ibtNext = (ImageButton) findViewById(R.id.imageButtonNext);
        sbTime = (SeekBar) findViewById(R.id.seekBarTime);
        imgHinh = (ImageView) findViewById(R.id.imageViewDisc);
    }
}