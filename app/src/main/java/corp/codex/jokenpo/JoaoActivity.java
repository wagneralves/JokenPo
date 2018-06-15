package corp.codex.jokenpo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class JoaoActivity extends AppCompatActivity {

    ImageButton pedra, papel, tesoura;
    ImageView joao, maria;
    Animation desaparece, aparece;
    int jogada1 = 0;
    int jogada2 = 0;
    MediaPlayer mediaPlayer;
    TextView tvMaria, tvJoao;
    int vMaria = 0;
    int vJoao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joao_activity);
        mediaPlayer = MediaPlayer.create(JoaoActivity.this, R.raw.alex_play);


        pedra = findViewById(R.id.ibPedra);
        papel = findViewById(R.id.ibPapel);
        tesoura = findViewById(R.id.ibTesoura);
        joao = findViewById(R.id.joao);
        maria = findViewById(R.id.maria);

        desaparece = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);
        desaparece.setDuration(1500);
        aparece.setDuration(150);

        tvJoao = findViewById(R.id.tvJoao);
        tvMaria = findViewById(R.id.tvMaria);

        desaparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                maria.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                maria.setVisibility(View.INVISIBLE);
                maria.startAnimation(aparece);            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaJogadaMaria();
                maria.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verificaJogada();
                maria.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void escolha(View view){
        tocaSom();

        joao.setScaleX(-1);
        switch (view.getId()){

            case (R.id.ibPedra):
                joao.setImageResource(R.drawable.pedra);
                jogada1 = 1;
            break;
            case (R.id.ibPapel):
                joao.setImageResource(R.drawable.papel);
                jogada1 = 2;
            break;
            case (R.id.ibTesoura):
                joao.setImageResource(R.drawable.tesoura);
                jogada1 = 3;
            break;
        }
        maria.startAnimation(desaparece);
        maria.setImageResource(R.drawable.maria);


    }

    public void sorteiaJogadaMaria(){

        Random r = new Random();
        int numRandom = r.nextInt(3);
        switch (numRandom){
            case 0:
                maria.setImageResource(R.drawable.pedra);
                jogada2 = 1;
            break;
            case 1:
                maria.setImageResource(R.drawable.papel);
                jogada2 = 2;
            break;
            case 2:
                maria.setImageResource(R.drawable.tesoura);
                jogada2 = 3;
            break;

        }

    }

    public void verificaJogada(){

        if(jogada1==jogada2){
            Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
        }

        if((jogada1==1&&jogada2==3)||(jogada1==2&&jogada2==1)||(jogada1==3&&jogada2==2))
        {
            vJoao++;
            tvJoao.setText(new String(String.valueOf(vJoao)));
            joao.setImageResource(R.drawable.vjoao);


            Toast.makeText(this, "Ganhei!", Toast.LENGTH_SHORT).show();
        }

        if((jogada2==1&&jogada1==3)||(jogada2==2&&jogada1==1)||(jogada2==3&&jogada1==2))
        {
            vMaria++;
            tvMaria.setText(new String(String.valueOf(vMaria)));
            Toast.makeText(this, "Perdi!", Toast.LENGTH_SHORT).show();
            maria.setScaleX(-1);
            maria.setImageResource(R.drawable.vmaria);

        }

    }

    public void tocaSom(){
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
    private Toast toast;
    private long lastBackPressTime = 0;

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o botão voltar novamente para reiniciar o jogo.", 4000);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }
}
