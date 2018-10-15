package com.ambe.customprogressbar;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CircleProgressBar smileView;
    private SeekBar seekBar;
    private TextView txtView, txtStatus, textTimeCountDown, textDays;
    private MediaPlayer mediaPlayer;
    private ImageView imgPlay;
    private ImageView imgPause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smileView = findViewById(R.id.m_progress);
        seekBar = findViewById(R.id.seek_bar);
        txtView = findViewById(R.id.txtxtxtxtx);
        txtStatus = findViewById(R.id.status);
        imgPlay = findViewById(R.id.img_play);
        imgPause = findViewById(R.id.img_pause);
        textDays = findViewById(R.id.textDays);
        textTimeCountDown = findViewById(R.id.textTimeCountDown);

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smileView.setStart(true);
            }
        });

        imgPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                           smileView.setStart(false);
         //       showDays(4081729);

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                smileView.setProgress(progress);
                txtView.setText(progress + "");
                if (0 <= progress && progress <= 33) {
                    txtStatus.setText("Hit vao");
                } else if (34 <= progress && progress <= 66) {
                    txtStatus.setText("Giu");
                } else {
                    txtStatus.setText("tho ra");
                }
                if (progress == 33) {
                    Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                    Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);

                    if (ringtoneSound != null) {
                        // ringtoneSound.play();
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    CountDownTimer countDownTimer;

    public void showDays(long n) {
        countDownTimer = new CountDownTimer((n * 1000), 1000) {
            public void onTick(long millisUntilFinished) {
                Log.e("AMBE1203", "on tick: " + millisUntilFinished);
                int seconds = (int) millisUntilFinished / 1000;
                int day = seconds / (24 * 3600);
                textDays.setText(day + " days");
                seconds = seconds % (24 * 3600);
                int hour = seconds / 3600;
                seconds %= 3600;
                int minutes = seconds / 60;
                seconds %= 60;
                int lastSeconds = seconds;
                textTimeCountDown.setText(hour + ":" + minutes + ":" + lastSeconds);
            }

            public void onFinish() {
                Log.e("AMBE1203", "on onFinish: ");
                textDays.setText("0");
                textTimeCountDown.setText("0");
            }
        };
        countDownTimer.start();
    }
}
