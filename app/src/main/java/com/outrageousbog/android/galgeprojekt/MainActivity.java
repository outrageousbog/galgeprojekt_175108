package com.outrageousbog.android.galgeprojekt;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Galgelogik spil;

Button q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m;
ImageView mHangMan;
ImageButton mSettings;
TextView mLetters;

private final String TAG = "MainActivity";
MediaPlayer mp, background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");

        Intent intent = getIntent();
        int key = intent.getIntExtra("key",-1);
        spil = new Galgelogik(key);

        createKeyboard();

        mp = MediaPlayer.create(this,R.raw.click1);
        background = MediaPlayer.create(this,R.raw.music);
        mHangMan = findViewById(R.id.hangman);
        mLetters = findViewById(R.id.right_letters);
        mSettings = findViewById(R.id.settings_button);
        mSettings.setOnClickListener(this);

        mLetters.setText(spil.getSynligtOrd());
    }

    /**
     * findViewById + setOnClickListener(this) on all buttons
     */
    public void createKeyboard(){
        //Upper keyboard
        q = findViewById(R.id.q_letter);q.setOnClickListener(this); q.setVisibility(View.VISIBLE);
        w = findViewById(R.id.w_letter);w.setOnClickListener(this); w.setVisibility(View.VISIBLE);
        e = findViewById(R.id.e_letter);e.setOnClickListener(this); e.setVisibility(View.VISIBLE);
        r = findViewById(R.id.r_letter);r.setOnClickListener(this); r.setVisibility(View.VISIBLE);
        t = findViewById(R.id.t_letter);t.setOnClickListener(this); t.setVisibility(View.VISIBLE);
        y = findViewById(R.id.y_letter);y.setOnClickListener(this); y.setVisibility(View.VISIBLE);
        u = findViewById(R.id.u_letter);u.setOnClickListener(this); u.setVisibility(View.VISIBLE);
        i = findViewById(R.id.i_letter);i.setOnClickListener(this); i.setVisibility(View.VISIBLE);
        o = findViewById(R.id.o_letter);o.setOnClickListener(this); o.setVisibility(View.VISIBLE);
        p = findViewById(R.id.p_letter);p.setOnClickListener(this); p.setVisibility(View.VISIBLE);

        //Middle keyboard
        a = findViewById(R.id.a_letter);a.setOnClickListener(this); a.setVisibility(View.VISIBLE);
        s = findViewById(R.id.s_letter);s.setOnClickListener(this); s.setVisibility(View.VISIBLE);
        d = findViewById(R.id.d_letter);d.setOnClickListener(this); d.setVisibility(View.VISIBLE);
        f = findViewById(R.id.f_letter);f.setOnClickListener(this); f.setVisibility(View.VISIBLE);
        g = findViewById(R.id.g_letter);g.setOnClickListener(this); g.setVisibility(View.VISIBLE);
        h = findViewById(R.id.h_letter);h.setOnClickListener(this); h.setVisibility(View.VISIBLE);
        j = findViewById(R.id.j_letter);j.setOnClickListener(this); j.setVisibility(View.VISIBLE);
        k = findViewById(R.id.k_letter);k.setOnClickListener(this); k.setVisibility(View.VISIBLE);
        l = findViewById(R.id.l_letter);l.setOnClickListener(this); l.setVisibility(View.VISIBLE);

        //Lower keyboard
        z = findViewById(R.id.z_letter);z.setOnClickListener(this); z.setVisibility(View.VISIBLE);
        x = findViewById(R.id.x_letter);x.setOnClickListener(this); x.setVisibility(View.VISIBLE);
        c = findViewById(R.id.c_letter);c.setOnClickListener(this); c.setVisibility(View.VISIBLE);
        v = findViewById(R.id.v_letter);v.setOnClickListener(this); v.setVisibility(View.VISIBLE);
        b = findViewById(R.id.b_letter);b.setOnClickListener(this); b.setVisibility(View.VISIBLE);
        n = findViewById(R.id.n_letter);n.setOnClickListener(this); n.setVisibility(View.VISIBLE);
        m = findViewById(R.id.m_letter);m.setOnClickListener(this); m.setVisibility(View.VISIBLE);
    }

    private void updateView(){
        Log.d(TAG, "updateView called");

        mLetters.setText(spil.getSynligtOrd());

        /**
         * If the player has won, he may restart the game
         */
        if (spil.erSpilletVundet()) {
            Log.d(TAG,"gamewon = true");

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.win_title)
                    .setMessage(R.string.win_main)
                    .setCancelable(false)
                    .setPositiveButton(R.string.win_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        }

        /**
         * setImage depends on numbers of wrong characters chosen
         */
        int wrongCount = spil.getAntalForkerteBogstaver();
        switch (wrongCount){
            case (0): mHangMan.setImageResource(R.drawable.galge); break;
            case (1): mHangMan.setImageResource(R.drawable.forkert1); break;
            case (2): mHangMan.setImageResource(R.drawable.forkert2); break;
            case (3): mHangMan.setImageResource(R.drawable.forkert3); break;
            case (4): mHangMan.setImageResource(R.drawable.forkert4); break;
            case (5): mHangMan.setImageResource(R.drawable.forkert5); break;
            case (6): mHangMan.setImageResource(R.drawable.forkert6);
                    Log.d(TAG,"gamelost = true");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setTitle(R.string.lose_title)
                            .setMessage(R.string.lose_main)
                            .setCancelable(false)
                            .setPositiveButton(R.string.win_yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .setNegativeButton("", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .show();
                }
        }


    public void onClick(View view){
        mp.start();
        if (view == mSettings){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(R.string.settings_title)
                    .setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.cancel,null)
                    .show();
        }

        //Upper keyboard
        if (view == q){
            spil.gætBogstav(q.getText().toString());
            q.setVisibility(View.INVISIBLE);
            Log.d(TAG,"q pressed");
        }
        if (view == w){
            spil.gætBogstav(w.getText().toString());
            w.setVisibility(View.INVISIBLE);
            Log.d(TAG,"w pressed");
        }
        if (view == e){
            spil.gætBogstav(e.getText().toString());
            e.setVisibility(View.INVISIBLE);
            Log.d(TAG,"e pressed");
        }
        if (view == r){
            spil.gætBogstav(r.getText().toString());
            r.setVisibility(View.INVISIBLE);
            Log.d(TAG,"r pressed");
        }
        if (view == t){
            spil.gætBogstav(t.getText().toString());
            t.setVisibility(View.INVISIBLE);
            Log.d(TAG,"t pressed");
        }
        if (view == y){
            spil.gætBogstav(y.getText().toString());
            y.setVisibility(View.INVISIBLE);
            Log.d(TAG,"y pressed");
        }
        if (view == u){
            spil.gætBogstav(u.getText().toString());
            u.setVisibility(View.INVISIBLE);
            Log.d(TAG,"u pressed");
        }
        if (view == i){
            spil.gætBogstav(i.getText().toString());
            i.setVisibility(View.INVISIBLE);
            Log.d(TAG,"i pressed");
        }
        if (view == o){
            spil.gætBogstav(o.getText().toString());
            o.setVisibility(View.INVISIBLE);
            Log.d(TAG,"o pressed");
        }
        if (view == p){
            spil.gætBogstav(p.getText().toString());
            p.setVisibility(View.INVISIBLE);
            Log.d(TAG,"p pressed");
        }

        //Middle keyboard
        if (view == a){
            spil.gætBogstav(a.getText().toString());
            a.setVisibility(View.INVISIBLE);
            Log.d(TAG,"a pressed");
        }
        if (view == s){
            spil.gætBogstav(s.getText().toString());
            s.setVisibility(View.INVISIBLE);
            Log.d(TAG,"s pressed");
        }
        if (view == d){
            spil.gætBogstav(d.getText().toString());
            d.setVisibility(View.INVISIBLE);
            Log.d(TAG,"d pressed");
        }
        if (view == f){
            spil.gætBogstav(f.getText().toString());
            f.setVisibility(View.INVISIBLE);
            Log.d(TAG,"f pressed");
        }
        if (view == g){
            spil.gætBogstav(g.getText().toString());
            g.setVisibility(View.INVISIBLE);
            Log.d(TAG,"g pressed");
        }
        if (view == h){
            spil.gætBogstav(h.getText().toString());
            h.setVisibility(View.INVISIBLE);
            Log.d(TAG,"h pressed");
        }
        if (view == j){
            spil.gætBogstav(j.getText().toString());
            j.setVisibility(View.INVISIBLE);
            Log.d(TAG,"j pressed");
        }
        if (view == k){
            spil.gætBogstav(k.getText().toString());
            k.setVisibility(View.INVISIBLE);
            Log.d(TAG,"k pressed");
        }
        if (view == l){
            spil.gætBogstav(l.getText().toString());
            l.setVisibility(View.INVISIBLE);
            Log.d(TAG,"l pressed");
        }

        //Lower keyboard
        if (view == z){
            spil.gætBogstav(z.getText().toString());
            z.setVisibility(View.INVISIBLE);
            Log.d(TAG,"z pressed");
        }
        if (view == x){
            spil.gætBogstav(x.getText().toString());
            x.setVisibility(View.INVISIBLE);
            Log.d(TAG,"x pressed");
        }
        if (view == c){
            spil.gætBogstav(c.getText().toString());
            c.setVisibility(View.INVISIBLE);
            Log.d(TAG,"c pressed");
        }
        if (view == v){
            spil.gætBogstav(v.getText().toString());
            v.setVisibility(View.INVISIBLE);
            Log.d(TAG,"v pressed");
        }
        if (view == b){
            spil.gætBogstav(b.getText().toString());
            b.setVisibility(View.INVISIBLE);
            Log.d(TAG,"b pressed");
        }
        if (view == n){
            spil.gætBogstav(n.getText().toString());
            n.setVisibility(View.INVISIBLE);
            Log.d(TAG,"n pressed");
        }
        if (view == m){
            spil.gætBogstav(m.getText().toString());
            m.setVisibility(View.INVISIBLE);
            Log.d(TAG,"m pressed");
        }

        updateView();
    }

}
